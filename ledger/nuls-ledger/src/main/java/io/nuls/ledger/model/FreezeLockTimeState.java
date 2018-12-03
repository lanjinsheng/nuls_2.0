package io.nuls.ledger.model;

import io.nuls.ledger.utils.ByteUtil;
import io.nuls.ledger.utils.RLP;
import io.nuls.ledger.utils.RLPList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * Created by wangkun23 on 2018/11/28.
 */
@ToString
@NoArgsConstructor
public class FreezeLockTimeState {
    /**
     * 交易的hash值
     */
    @Setter
    @Getter
    private String txHash;

    /**
     * 锁定金额
     */
    @Setter
    @Getter
    private long amount;

    /**
     * 锁定时间
     */
    @Setter
    @Getter
    private long lockTime;

    @Setter
    @Getter
    private long createTime;
}