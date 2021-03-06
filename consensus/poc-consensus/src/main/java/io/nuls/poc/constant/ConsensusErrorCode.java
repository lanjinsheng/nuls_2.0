package io.nuls.poc.constant;

import io.nuls.tools.constant.ErrorCode;

/**
 * @author tag
 * 2018/11/12
 * */
public interface ConsensusErrorCode {
    ErrorCode SUCCESS = ErrorCode.init("10000");
    ErrorCode FAILED = ErrorCode.init("10001");
    ErrorCode SYS_UNKOWN_EXCEPTION = ErrorCode.init("10002");
    ErrorCode DATA_PARSE_ERROR = ErrorCode.init("10003");
    ErrorCode PARAM_NUMBER_ERROR = ErrorCode.init("70000");
    ErrorCode DATA_ERROR = ErrorCode.init("70001");
    ErrorCode TX_NOT_EXIST = ErrorCode.init("70002");
    ErrorCode ACCOUNT_NOT_EXIST = ErrorCode.init("70003");
    ErrorCode ACCOUNT_IS_ALREADY_ENCRYPTED = ErrorCode.init("70004");
    ErrorCode AGENT_NOT_EXIST = ErrorCode.init("70005");
    ErrorCode DATA_NOT_EXIST = ErrorCode.init("70006");
    ErrorCode ADDRESS_ERROR = ErrorCode.init("70007");
    ErrorCode ACCOUNT_INFO_ERROR = ErrorCode.init("70008");
    ErrorCode PARAM_ERROR = ErrorCode.init("70009");
    ErrorCode AGENTADDR_AND_PACKING_SAME = ErrorCode.init("70010");
    ErrorCode REWARDADDR_AND_PACKING_SAME = ErrorCode.init("70011");
    ErrorCode COMMISSION_RATE_OUT_OF_RANGE = ErrorCode.init("70012");
    ErrorCode DEPOSIT_OUT_OF_RANGE = ErrorCode.init("70013");
    ErrorCode DEPOSIT_ERROR = ErrorCode.init("70014");
    ErrorCode TX_DATA_VALIDATION_ERROR = ErrorCode.init("70015");
    ErrorCode AGENT_EXIST = ErrorCode.init("70016");
    ErrorCode AGENT_PACKING_EXIST = ErrorCode.init("70017");
    ErrorCode LACK_OF_CREDIT = ErrorCode.init("70018");
    ErrorCode TRANSACTION_REPEATED = ErrorCode.init("70019");
    ErrorCode MARGIN_LOCK_TIME_ERROR = ErrorCode.init("70020");
    ErrorCode DEPOSIT_OVER_COUNT = ErrorCode.init("70021");
    ErrorCode DEPOSIT_OVER_AMOUNT= ErrorCode.init("70022");
    ErrorCode DEPOSIT_NOT_ENOUGH= ErrorCode.init("70023");
    ErrorCode SAVE_FAILED= ErrorCode.init("70024");
    ErrorCode ROLLBACK_FAILED= ErrorCode.init("70025");
    ErrorCode DEPOSIT_WAS_CANCELED= ErrorCode.init("70026");
    ErrorCode DEPOSIT_NEVER_CANCELED= ErrorCode.init("70027");
    ErrorCode LOCK_TIME_NOT_REACHED= ErrorCode.init("70028");
    ErrorCode MERKEL_HASH_ERROR= ErrorCode.init("70029");
    ErrorCode BLOCK_ROUND_VALIDATE_ERROR= ErrorCode.init("70030");
    ErrorCode BANANCE_NOT_ENNOUGH= ErrorCode.init("70031");
    ErrorCode CHAIN_NOT_EXIST= ErrorCode.init("70032");
    ErrorCode BLOCK_TX_VALID_ERROR = ErrorCode.init("70033");
    ErrorCode BLOCK_PUNISH_VALID_ERROR = ErrorCode.init("70034");
    ErrorCode BLOCK_SIGNATURE_ERROR = ErrorCode.init("70035");
    ErrorCode BLOCK_RED_PUNISH_ERROR = ErrorCode.init("70036");
    ErrorCode BLOCK_COINBASE_VALID_ERROR = ErrorCode.init("70037");
    ErrorCode TRANSACTION_LIST_IS_NULL = ErrorCode.init("70038");
    ErrorCode INTERFACE_CALL_FAILED = ErrorCode.init("70039");
    ErrorCode ACCOUNT_VALID_ERROR = ErrorCode.init("70040");
    ErrorCode TX_SIGNTURE_ERROR = ErrorCode.init("70041");
    ErrorCode AGENT_INVALID = ErrorCode.init("70042");
    ErrorCode COIN_DATA_VALID_ERROR = ErrorCode.init("70043");
}
