package io.nuls.transaction.message;

import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.basic.NulsOutputStreamBuffer;
import io.nuls.base.data.NulsDigestData;
import io.nuls.tools.exception.NulsException;
import io.nuls.tools.parse.SerializeUtils;
import io.nuls.transaction.message.base.BaseMessage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * 获取完整交易数据
 *
 * @author: qinyifeng
 * @date: 2018/12/26
 */
public class GetTxMessage extends BaseMessage {
    /**
     * 交易hash
     */
    @Getter
    @Setter
    private NulsDigestData requestHash;

    @Override
    public int size() {
        int size = 0;
        size += SerializeUtils.sizeOfNulsData(requestHash);
        return size;
    }

    @Override
    public void serializeToStream(NulsOutputStreamBuffer stream) throws IOException {
        stream.writeNulsData(requestHash);
    }

    @Override
    public void parse(NulsByteBuffer byteBuffer) throws NulsException {
        this.requestHash = byteBuffer.readHash();
    }
}
