/*
 * MIT License
 * Copyright (c) 2017-2019 nuls.io
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.nuls.block.thread;

import io.nuls.base.data.NulsDigestData;
import io.nuls.block.model.Node;
import lombok.Data;

/**
 * 一个区块下载线程的下载结果
 *
 * @author captain
 * @version 1.0
 * @date 18-11-9 下午4:17
 */
@Data
public class BlockDownLoadResult {
    /**
     * 下载区块起始高度
     */
    private long startHeight;
    /**
     * 预计下载区块个数
     */
    private int size;
    /**
     * 区块来源节点
     */
    private Node node;
    /**
     * 标志从node节点批量下载区块是否成功,要全部下载完成才算成功
     */
    private boolean success;
    /**
     * 下载耗时(不精确)
     */
    private long duration;
    /**
     * 对应的请求hash
     */
    private NulsDigestData messageHash;

    public BlockDownLoadResult(NulsDigestData messageHash, long startHeight, int size, Node node, boolean b, long duration) {
        this.messageHash = messageHash;
        this.startHeight = startHeight;
        this.size = size;
        this.node = node;
        this.success = b;
        this.duration = duration;
    }

}
