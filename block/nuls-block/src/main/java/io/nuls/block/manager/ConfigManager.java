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

package io.nuls.block.manager;

import io.nuls.tools.parse.config.ConfigItem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置管理器,维护所有本节点上运行的链的配置信息
 *
 * @author captain
 * @version 1.0
 * @date 18-11-8 下午1:37
 */
public class ConfigManager {

    /**
     * 链配置信息
     */
    private static Map<Integer, Map<String, ConfigItem>> configItemsMap = new ConcurrentHashMap<>();

    /**
     * 获取某条链的参数
     *
     * @param chainId
     * @param name
     * @return
     */
    public static String getValue(int chainId, String name) {
        Map<String, ConfigItem> itemMap = configItemsMap.get(chainId);
        return itemMap.get(name).getValue();
    }

    /**
     * 设置某条链的参数
     *
     * @param chainId
     * @param name
     * @param value
     * @return
     */
    public static boolean setValue(int chainId, String name, String value) {
        Map<String, ConfigItem> itemMap = configItemsMap.get(chainId);
        ConfigItem item = itemMap.get(name);
        if (item.isReadOnly()) {
            return false;
        }
        item.setValue(value);
        return true;
    }

    /**
     * 新增一条链的参数
     *
     * @param chainId
     * @param map
     */
    public static void add(int chainId, Map<String, ConfigItem> map) {
        configItemsMap.put(chainId, map);
    }

}
