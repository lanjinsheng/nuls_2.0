/*-
 * ⁣⁣
 * MIT License
 * ⁣⁣
 * Copyright (C) 2017 - 2018 nuls.io
 * ⁣⁣
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ⁣⁣
 */
package io.nuls.ledger.model.po;

import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.basic.NulsOutputStreamBuffer;
import io.nuls.base.data.BaseNulsData;
import io.nuls.tools.exception.NulsException;
import io.nuls.tools.parse.SerializeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cody on 2019/01/09.
 */
@ToString
@AllArgsConstructor
public class AccountStatesSnapshotKeys extends BaseNulsData {
    /**
     *  snapshotKeys
     */
    @Setter
    @Getter
    private List<String> snapshotKeys = new ArrayList<String>();

    public AccountStatesSnapshotKeys() {
        super();
    }
    public void addSnapshotKey(String key){
        snapshotKeys.add(key);
    }
    @Override
    protected void serializeToStream(NulsOutputStreamBuffer stream) throws IOException {
        stream.writeUint16(snapshotKeys.size());
        for (String snapshotKey : snapshotKeys) {
            stream.writeString(snapshotKey);
        }
    }

    @Override
    public void parse(NulsByteBuffer byteBuffer) throws NulsException {
        int snapshotKeysCount = byteBuffer.readUint16();
        for (int i = 0; i < snapshotKeysCount; i++) {
            try {
                this.snapshotKeys.add(byteBuffer.readString());
            } catch (Exception e) {
                throw new NulsException(e);
            }
        }
    }

    @Override
    public int size() {
        int size = 0;
        size += SerializeUtils.sizeOfUint16();
        for (String snapshotKey : snapshotKeys) {
            size += SerializeUtils.sizeOfString(snapshotKey);
        }
        return size;
    }
}
