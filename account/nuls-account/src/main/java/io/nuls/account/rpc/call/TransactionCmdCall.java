package io.nuls.account.rpc.call;

import io.nuls.account.constant.AccountConstant;
import io.nuls.account.constant.RpcConstant;
import io.nuls.account.model.bo.tx.TxRegisterDetail;
import io.nuls.account.util.annotation.ResisterTx;
import io.nuls.account.util.annotation.TxMethodType;
import io.nuls.rpc.client.CmdDispatcher;
import io.nuls.rpc.info.Constants;
import io.nuls.rpc.model.ModuleE;
import io.nuls.rpc.model.message.Response;
import io.nuls.tools.core.ioc.ScanUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qinyifeng
 * @description: 交易模块接口调用
 * @date: 2018/11/27
 */
public class TransactionCmdCall {

    /**
     * 向交易模块注册交易
     * Register transactions with the transaction module
     */
    public static void registerTx(int chainId) {
        try {
            List<Class> classList = ScanUtil.scan(AccountConstant.RPC_PATH);
            if (classList == null || classList.size() == 0) {
                return;
            }
            List<TxRegisterDetail> txRegisterDetailList = new ArrayList<>();
            Map<Integer, TxRegisterDetail> registerDetailMap = new HashMap<>(16);
            for (Class clz : classList) {
                Method[] methods = clz.getMethods();
                for (Method method : methods) {
                    ResisterTx annotation = getRegisterAnnotation(method);
                    if (annotation != null) {
                        if (!registerDetailMap.containsKey(annotation.txType())) {
                            TxRegisterDetail txRegisterDetail = new TxRegisterDetail(annotation.txType());
                            registerDetailMap.put(annotation.txType(), txRegisterDetail);
                            txRegisterDetailList.add(txRegisterDetail);
                        }
                        if (annotation.methodType().equals(TxMethodType.COMMIT)) {
                            registerDetailMap.get(annotation.txType()).setCommitCmd(annotation.methodName());
                        } else if (annotation.methodType().equals(TxMethodType.VALID)) {
                            registerDetailMap.get(annotation.txType()).setValidateCmd(annotation.methodName());
                        } else if (annotation.methodType().equals(TxMethodType.ROLLBACK)) {
                            registerDetailMap.get(annotation.txType()).setRollbackCmd(annotation.methodName());
                        }
                    }
                }
            }
            //向交易管理模块注册交易
            Map<String, Object> params = new HashMap<>();
            params.put(Constants.VERSION_KEY_STR, RpcConstant.TX_REGISTER_VERSION);
            params.put(RpcConstant.TX_CHAIN_ID, chainId);
            params.put(RpcConstant.TX_MODULE_CODE, ModuleE.AC.abbr);
            params.put(RpcConstant.TX_MODULE_VALIDATE_CMD, "ac_accountTxValidate");
            params.put("list", txRegisterDetailList);
            Response cmdResp = CmdDispatcher.requestAndResponse(ModuleE.TX.abbr, RpcConstant.TX_REGISTER_CMD, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描需要注册到交易模块的交易
     *
     * @param method
     * @return
     */
    private static ResisterTx getRegisterAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (ResisterTx.class.equals(annotation.annotationType())) {
                return (ResisterTx) annotation;
            }
        }
        return null;
    }

    /**
     * 注册交易
     */
    @Deprecated
    public static void register(int chainId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(Constants.VERSION_KEY_STR, RpcConstant.TX_REGISTER_VERSION);
            params.put(RpcConstant.TX_CHAIN_ID, chainId);
            params.put(RpcConstant.TX_MODULE_CODE, ModuleE.AC.abbr);
            params.put(RpcConstant.TX_MODULE_VALIDATE_CMD, "ac_accountTxValidate");
            params.put(RpcConstant.TX_TYPE, AccountConstant.TX_TYPE_ACCOUNT_ALIAS);
            params.put(RpcConstant.TX_VALIDATE_CMD, "ac_aliasTxValidate");
            params.put(RpcConstant.TX_COMMIT_CMD, "ac_aliasTxCommit");
            params.put(RpcConstant.TX_ROLLBACK_CMD, "ac_rollbackAlias");
            params.put(RpcConstant.TX_IS_SYSTEM_CMD, false);
            params.put(RpcConstant.TX_UNLOCK_CMD, false);
            params.put(RpcConstant.TX_VERIFY_SIGNATURE_CMD, true);
            Response cmdResp = CmdDispatcher.requestAndResponse(ModuleE.AC.abbr, RpcConstant.TX_REGISTER_CMD, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起新交易
     */
    public static void newTx(int chainId, String txHex) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(Constants.VERSION_KEY_STR, RpcConstant.TX_NEW_VERSION);
            params.put(RpcConstant.TX_CHAIN_ID, chainId);
            params.put(RpcConstant.TX_DATA_HEX, txHex);
            Response cmdResp = CmdDispatcher.requestAndResponse(ModuleE.TX.abbr, RpcConstant.TX_NEW_CMD, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
