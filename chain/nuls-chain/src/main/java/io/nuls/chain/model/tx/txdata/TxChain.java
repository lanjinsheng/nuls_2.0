package io.nuls.chain.model.tx.txdata;

import io.nuls.base.basic.NulsByteBuffer;
import io.nuls.base.basic.NulsOutputStreamBuffer;
import io.nuls.base.basic.TransactionLogicData;
import io.nuls.tools.exception.NulsException;
import io.nuls.tools.parse.SerializeUtils;
import lombok.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Set;

/**
 * @author tangyi
 * @date 2018/11/7
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TxChain extends TransactionLogicData {

    private int chainId;
    private String name;
    private String addressType;
    private long magicNumber;
    private boolean supportInflowAsset;
    private int minAvailableNodeNum;
    private int singleNodeMinConnectionNum;
    private byte[] address;

    /**
     * 下面这些是创建链的时候，必须携带的资产信息
     */
    private int assetId;
    private String symbol;
    private String assetName;
    private int depositNuls;
    private BigInteger initNumber;
    private short decimalPlaces;


    @Override
    protected void serializeToStream(NulsOutputStreamBuffer stream) throws IOException {
        stream.writeUint16(chainId);
        stream.writeString(name);
        stream.writeString(addressType);
        stream.writeUint32(magicNumber);
        stream.writeBoolean(supportInflowAsset);
        stream.writeUint32(minAvailableNodeNum);
        stream.writeUint32(singleNodeMinConnectionNum);
        stream.writeBytesWithLength(address);
        stream.writeUint16(assetId);
        stream.writeString(symbol);
        stream.writeString(assetName);
        stream.writeUint16(depositNuls);
        stream.writeBigInteger(initNumber);
        stream.writeShort(decimalPlaces);
    }

    @Override
    public void parse(NulsByteBuffer byteBuffer) throws NulsException {
        this.chainId = byteBuffer.readUint16();
        this.name = byteBuffer.readString();
        this.addressType = byteBuffer.readString();
        this.magicNumber = byteBuffer.readUint32();
        this.supportInflowAsset = byteBuffer.readBoolean();
        this.minAvailableNodeNum = byteBuffer.readInt32();
        this.singleNodeMinConnectionNum = byteBuffer.readInt32();
        this.address = byteBuffer.readByLengthByte();
        this.assetId = byteBuffer.readUint16();
        this.symbol = byteBuffer.readString();
        this.assetName = byteBuffer.readString();
        this.depositNuls = byteBuffer.readUint16();
        this.initNumber = byteBuffer.readBigInteger();
        this.decimalPlaces = byteBuffer.readShort();
    }

    @Override
    public int size() {
        int size = 0;
        // chainId;
        size += SerializeUtils.sizeOfUint16();
        size += SerializeUtils.sizeOfString(name);
        size += SerializeUtils.sizeOfString(addressType);
        // magicNumber;
        size += SerializeUtils.sizeOfUint32();
        // supportInflowAsset;
        size += SerializeUtils.sizeOfBoolean();
        // minAvailableNodeNum;
        size += SerializeUtils.sizeOfInt32();
        // singleNodeMinConnectionNum;
        size += SerializeUtils.sizeOfInt32();
        size += SerializeUtils.sizeOfBytes(address);
        //assetId
        size += SerializeUtils.sizeOfUint16();
        size += SerializeUtils.sizeOfString(symbol);
        size += SerializeUtils.sizeOfString(assetName);
        //depositNuls
        size += SerializeUtils.sizeOfUint16();
        //initNumber
        size += SerializeUtils.sizeOfBigInteger();
        //decimalPlaces
        size += SerializeUtils.sizeOfInt16();
        return size;
    }


    @Override
    public Set<byte[]> getAddresses() {
        return null;
    }
}
