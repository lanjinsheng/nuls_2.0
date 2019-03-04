package io.nuls.api.model.po.db;

import lombok.Data;
import org.bson.Document;

import java.math.BigInteger;
import java.util.List;

@Data
public class TransactionInfo {

    private String hash;

    private Integer type;

    private Long height;

    private Integer size;

    private BigInteger fee;

    private Long createTime;

    private String remark;

    private String txDataHex;

    private TxDataInfo txData;

    private List<TxDataInfo> txDataList;

    private List<CoinFromInfo> coinFroms;

    private List<CoinToInfo> coinTos;

    private BigInteger value;


//    public void calcValue() {
//        long value = 0;
//        if (type == TransactionConstant.TX_TYPE_COINBASE ||
//                type == TransactionConstant.TX_TYPE_STOP_AGENT ||
//                type == TransactionConstant.TX_TYPE_CANCEL_DEPOSIT) {
//            if (tos != null) {
//                for (Output output : tos) {
//                    value += output.getValue();
//                }
//            }
//        } else if (type == TransactionConstant.TX_TYPE_TRANSFER ||
//                type == TransactionConstant.TX_TYPE_ALIAS ||
//                type == TransactionConstant.TX_TYPE_CONTRACT_TRANSFER) {
//            Set<String> addressSet = new HashSet<>();
//            for (Input input : froms) {
//                addressSet.add(input.getAddress());
//            }
//            for (Output output : tos) {
//                if (!addressSet.contains(output.getAddress())) {
//                    value += output.getValue();
//                }
//            }
//        } else if (type == TransactionConstant.TX_TYPE_REGISTER_AGENT || type == TransactionConstant.TX_TYPE_JOIN_CONSENSUS) {
//            for (Output output : tos) {
//                if (output.getLockTime() == -1) {
//                    value += output.getValue();
//                }
//            }
//        } else {
//            value = this.fee;
//        }
//        this.value = value;
//    }

    public Document toDocument() {
        Document document = new Document();
        document.append("_id", hash).append("height", height).append("createTime", createTime).append("type", type).append("value", value).append("fee", fee);
        return document;
    }

    public static TransactionInfo fromDocument(Document document) {
        TransactionInfo info = new TransactionInfo();
        info.setHash(document.getString("_id"));
        info.setHeight(document.getLong("height"));
        info.setCreateTime(document.getLong("createTime"));
        info.setType(document.getInteger("type"));
        info.setFee(new BigInteger(document.getString("fee")));

        info.setValue(new BigInteger(document.getString("value")));
        return info;
    }
}