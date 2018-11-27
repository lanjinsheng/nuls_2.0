package io.nuls.tools.data;

import java.math.BigInteger;

/**
 * @author tag
 * 2018/11/27
 * */
public class BigIntegerUtils {
    public static final String ZERO = String.valueOf(0);
    /**
     * String 转 BigInteger
     * @param str  数字字符串
     * */
    public static BigInteger stringToBigInteger(String str){
        BigInteger bigInteger = new BigInteger("0");
        if(!StringUtils.isBlank(str)){
            bigInteger = new BigInteger(str);
        }
        return bigInteger;
    }

    /**
     * BigInteger 转 String
     * @param bigInteger  数字
     * */
    public static String bigIntegerToString(BigInteger bigInteger){
        return  String.valueOf(bigInteger);
    }

    /**
     * 两个数字字符串相加
     * @param str1
     * @param str2
     * */
    public static String addToString(String str1,String str2){
        return  bigIntegerToString(stringToBigInteger(str1).add(stringToBigInteger(str2)));
    }

    public static BigInteger addToBigInteger(String str1,String str2){
        return stringToBigInteger(str1).add(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串相减
     * @param str1
     * @param str2
     * */
    public static String subToString(String str1,String str2){
        return  bigIntegerToString(stringToBigInteger(str1).subtract(stringToBigInteger(str2)));
    }

    public static BigInteger subToBigInteger(String str1,String str2){
        return  stringToBigInteger(str1).subtract(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串相乘
     * @param str1
     * @param str2
     * */
    public static String mulToString(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return "0";
        }
        return  bigIntegerToString(stringToBigInteger(str1).multiply(stringToBigInteger(str2)));
    }

    public static BigInteger mulToInteger(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return new BigInteger("0");
        }
        return  stringToBigInteger(str1).multiply(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串相除
     * @param str1
     * @param str2
     * */
    public static String divToString(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return "0";
        }
        return  bigIntegerToString(stringToBigInteger(str1).divide(stringToBigInteger(str2)));
    }

    public static BigInteger divToBigInteger(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return new BigInteger("0");
        }
        return stringToBigInteger(str1).divide(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串取模
     * @param str1
     * @param str2
     * */
    public static String modToString(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return "0";
        }
        return  bigIntegerToString(stringToBigInteger(str1).mod(stringToBigInteger(str2)));
    }

    public static BigInteger modToBigIntegr(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return new BigInteger("0");
        }
        return  stringToBigInteger(str1).mod(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串求余
     * @param str1
     * @param str2
     * */
    public static String remToString(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return "0";
        }
        return  bigIntegerToString(stringToBigInteger(str1).remainder(stringToBigInteger(str2)));
    }

    public static BigInteger remToBigInteger(String str1,String str2){
        if(StringUtils.isBlank(str1) || StringUtils.isBlank(str2)){
            return new BigInteger("0");
        }
        return stringToBigInteger(str1).remainder(stringToBigInteger(str2));
    }

    /**
     * 两个数字字符串比较大小
     * */
    public static int compare(String str1,String str2){
        return stringToBigInteger(str1).compareTo(stringToBigInteger(str2));
    }
}