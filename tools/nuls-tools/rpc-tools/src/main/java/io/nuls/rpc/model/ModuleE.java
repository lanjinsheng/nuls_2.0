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
package io.nuls.rpc.model;

/**
 * Module information
 *
 * @author tangyi
 */
public enum ModuleE {
    /**
     * prefix + name
     */
    KE("ke", "Kernel", "nuls.io"),
    CM("cm", "Chain", "nuls.io"),
    AC("ac", "Account", "nuls.io"),
    NW("nw", "Network", "nuls.io"),
    CS("cs", "Consensus", "nuls.io"),
    BL("bl", "Block", "nuls.io"),
    LG("lg", "Ledger", "nuls.io"),
    TX("tx", "Transaction", "nuls.io"),
    EB("eb", "EventBus", "nuls.io");

    public final String abbr;
    public final String name;
    public final String domain;

    ModuleE(String abbr, String name, String domain) {
        this.abbr = abbr;
        this.name = name;
        this.domain = domain;
    }
}
