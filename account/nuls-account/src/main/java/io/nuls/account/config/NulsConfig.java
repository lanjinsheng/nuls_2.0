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

package io.nuls.account.config;


import io.nuls.tools.parse.config.IniEntity;

/**
 * 用来管理配置项
 * <p>
 * Used to manage system configuration items.
 *
 * @author: Niels Wang
 */
public class NulsConfig {

    /**
     * 模块配置文件中加载的所有配置项
     * All the configuration items that are loaded in the module configuration file.
     */
    public static IniEntity MODULES_CONFIG;

    /**
     * 模块配置文件名称
     * Module configuration file name.
     */
    public final static String MODULES_CONFIG_FILE = "modules.ini";

    /**
     * 系统使用的编码方式
     * The encoding used by the nuls system.
     */
    public static String DEFAULT_ENCODING = "UTF-8";

    /**
     * 导出keystore备份文件目录
     */
    public static String ACCOUNTKEYSTORE_FOLDER_NAME = "keystore/backup";

    /**
     * 内核模块地址
     * Kernel module address
     */
    public static String KERNEL_MODULE_URL;

    /**
     * config file path
     */
    public final static String CONFIG_FILE_PATH = "account-config.json";

    /**
     * 主网链ID（卫星链ID）
     */
    public final static int MAIN_CHAIN_ID = 12345;

    /**
     * 主网链资产ID（卫星链资产ID，NULS资产）
     */
    public final static int MAIN_ASSETS_ID = 1;

    /**
     * 当前链ID
     */
    public static int CURRENT_CHAIN_ID = 12345;

    /**
     * 当前链主资产ID
     */
    public static int CURRENT_MAIN_ASSETS_ID = 1;


}
