/*
 * MIT License
 *
 * Copyright (c) 2017-2018 nuls.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.nuls.network.manager.threads;

import io.nuls.network.manager.ConnectionManager;
import io.nuls.network.manager.MessageFactory;
import io.nuls.network.manager.MessageManager;
import io.nuls.network.model.Node;
import io.nuls.network.model.NodeGroupConnector;
import io.nuls.network.model.dto.NetTimeUrl;
import io.nuls.network.model.message.GetTimeMessage;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static io.nuls.network.utils.LoggerUtil.Log;

/**
 * 时间服务类：用于同步网络标准时间
 * Time service class:Used to synchronize network standard time.
 *
 * @author vivi & lan
 */
public class TimeService implements Runnable {
    private static final int MAX_REQ_PEER_NUMBER = 8;
    private static Map<String, Long> peerTimesMap = new ConcurrentHashMap<> ();
    private static long currentRequestId = System.currentTimeMillis();
    private static TimeService instance = new TimeService();

    /**
     * 网站url集合，用于同步网络时间
     */
    private List<String> urlList = new ArrayList<>();

    private List<NetTimeUrl> netTimeUrls = new ArrayList<>();
    /**
     * 时间偏移差距触发点，超过该值会导致本地时间重设，单位毫秒
     * Time migration gap trigger point, which can cause local time reset, unit milliseconds.
     **/
    private static final long TIME_OFFSET_BOUNDARY = 3000L;
    /**
     *  等待对等节点回复时间
     *
     **/
    private static final long TIME_WAIT_PEER_RESPONSE = 2000L;
    /**
     * 重新同步时间间隔
     * Resynchronize the interval.
     * 2 minutes;
     */
    private static final long NET_REFRESH_TIME = 2 * 60 * 1000L;

    /**
     * 网络时间偏移值
     */
    private static long netTimeOffset;


    /**
     * 上次同步时间点
     * The last synchronization point.
     */
    private static long lastSyncTime;
    public static TimeService getInstance() {
        return instance;
    }


    private TimeService() {
        urlList.add("sgp.ntp.org.cn");
        urlList.add("cn.ntp.org.cn");
        urlList.add("time1.apple.com");
        urlList.add("ntp3.aliyun.com");
        urlList.add("ntp5.aliyun.com");
        urlList.add("us.ntp.org.cn");
        urlList.add("kr.ntp.org.cn");
        urlList.add("de.ntp.org.cn");
        urlList.add("jp.ntp.org.cn");
        urlList.add("ntp7.aliyun.com");
    }
    public static void addPeerTime(String nodeId,long requestId,long time){
        if(currentRequestId == requestId){
            if(MAX_REQ_PEER_NUMBER > peerTimesMap.size()){
                long localBeforeTime = currentRequestId;
                long localEndTime = System.currentTimeMillis();
                long value = (time + (localEndTime - localBeforeTime) / 2) - localEndTime;
                peerTimesMap.put(nodeId,value);
            }
        }
    }

    public List<NetTimeUrl> getNetTimeUrls() {
        return netTimeUrls;
    }
    private void sendGetTimeMessage(Node node,long magicNumber){
        GetTimeMessage getTimeMessage = MessageFactory.getInstance().buildTimeRequestMessage(magicNumber,currentRequestId);
        MessageManager.getInstance().sendToNode(getTimeMessage,node,true);
    }
    private synchronized  void syncPeerTime(){
      //设置请求id
      currentRequestId = System.currentTimeMillis();
      long beginTime = currentRequestId;
      // peerTimesMap 清空
      peerTimesMap.clear();
      //随机发出请求
       List<Node> list = ConnectionManager.getInstance().getCacheAllNodeList();
       if(list.size()  == 0){
           return;
       }
       int count = 0;
       for(Node node : list){
           NodeGroupConnector nodeGroupConnector = node.getFirstNodeGroupConnector();
           if(null != nodeGroupConnector){
               sendGetTimeMessage(node,nodeGroupConnector.getMagicNumber());
               count++;
           }
           if(count >= MAX_REQ_PEER_NUMBER){
               break;
           }
       }
       if(count == 0){
           return;
       }
      //数量或时间满足要求
      long intervalTime = 0;
      while(peerTimesMap.size()< MAX_REQ_PEER_NUMBER &&  intervalTime < TIME_WAIT_PEER_RESPONSE){
          try {
              Thread.sleep(500L);
          } catch (InterruptedException e) {
                e.printStackTrace();
          }
          intervalTime = System.currentTimeMillis()-beginTime;
      }
      int size = peerTimesMap.size();
      if(size>0){
          long sum = 0L;
         Set set = peerTimesMap.keySet();
          //计算
          for (Object aSet : set) {
              sum += peerTimesMap.get(aSet.toString());
          }
          netTimeOffset = sum / size;
      }
  }
    /**
     * 按相应时间排序
     */
    public  void initWebTimeServer() {
        for (String anUrlList : urlList) {
            long begin = System.currentTimeMillis();
            long netTime = getWebTime(anUrlList);
            Log.info(anUrlList + "netTime:===" + netTime);
            Log.info("localtime:===" + System.currentTimeMillis());
            if (netTime == 0) {
                continue;
            }
            long end = System.currentTimeMillis();
            NetTimeUrl netTimeUrl = new NetTimeUrl(anUrlList, (end - begin));
            netTimeUrls.add(netTimeUrl);
        }
        Collections.sort(netTimeUrls);

    }



    /**
     * 同步网络时间
     */
    private void syncWebTime() {

        int count = 0;
        long sum = 0L;

        for (int i = 0; i < netTimeUrls.size(); i++) {
            long localBeforeTime = System.currentTimeMillis();
            long netTime = getWebTime(netTimeUrls.get(i).getUrl());
            Log.info(urlList.get(i)+"netTime:==="+netTime);
            Log.info("localtime:==="+System.currentTimeMillis());
            if (netTime == 0) {
                continue;
            }
            long localEndTime = System.currentTimeMillis();
            long value = (netTime + (localEndTime - localBeforeTime) / 2) - localEndTime;
            count++;
            sum += value;
            /*
             * 有3个网络时间返回就可以退出了
             */
            if(count >= 3){
                break;
            }

        }
        if (count > 0) {
            netTimeOffset = sum / count;
        }else{
            //从对等网络去获取时间
            syncPeerTime();
        }
        lastSyncTime = currentTimeMillis();
    }

    /**
     * 获取网络时间
     *
     * @return long
     */
    private long getWebTime(String address) {
        try {
            NTPUDPClient client = new NTPUDPClient();
            client.open();
            client.setDefaultTimeout(500);
            client.setSoTimeout(500);
            InetAddress inetAddress = InetAddress.getByName(address);
            //Log.debug("start ask time....");
            TimeInfo timeInfo = client.getTime(inetAddress);
            //Log.debug("done!");
            return timeInfo.getMessage().getTransmitTimeStamp().getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }



    /**
     * 循环调用同步网络时间方法
     * Loop call synchronous network time method.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        syncWebTime();
        while (true) {
            long newTime = System.currentTimeMillis();
            if (Math.abs(newTime - lastTime) > TIME_OFFSET_BOUNDARY) {
                Log.debug("local time changed ：{}", newTime - lastTime);
                syncWebTime();
            } else if (currentTimeMillis() - lastSyncTime > NET_REFRESH_TIME) {
                //每隔一段时间更新网络时间
                syncWebTime();
            }
            lastTime = newTime;
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取当前网络时间毫秒数
     * Gets the current network time in milliseconds.
     *
     * @return long
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis() + netTimeOffset;
    }



}
