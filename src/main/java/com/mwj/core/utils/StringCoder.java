package com.mwj.core.utils;

/**
 *
 */

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gonglei java二进制,字节数组,字符,十六进制,BCD编码转换
 */
public class StringCoder {

    private static final String HEX_STRING = "0123456789ABCDEF";

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int modLen = (hex.length() % 2);
        int len = (hex.length() / 2);
        if(modLen > 0){
            hex = "0" + hex;
            len = len + 1;
        }
        hex = hex.toUpperCase();
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;


//        String upperHex = hex.toUpperCase();
//		int len = (upperHex.length() / 2);
//		byte[] result = new byte[len];
//		char[] achar = upperHex.toCharArray();
//		for (int i = 0; i < len; i++) {
//			int pos = i * 2;
//			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
//		}
//		return result;
    }

    private static byte toByte(char c) {
        return (byte) HEX_STRING.indexOf(c);
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte... bArray) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray, int length, String intervalStr) {
        StringBuilder sb = new StringBuilder(length);
        String sTemp;
        for (int i = 0; i < length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            sb.append(intervalStr);
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray, String intervalStr) {
        StringBuilder sb = new StringBuilder(bArray.length);
        String sTemp;
        for (byte aBArray : bArray) {
            sTemp = Integer.toHexString(0xFF & aBArray);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            sb.append(intervalStr);
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换为对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

    /**
     * 把可序列化对象转换成字节数组
     *
     * @param s
     * @return
     * @throws IOException
     */
    public static byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    public static String objectToHexString(Serializable s) throws IOException {
        return bytesToHexString(objectToBytes(s));
    }

    public static Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

    /**
     * BCD码转为10进制串(阿拉伯数据)
     *
     * @param bytes BCD码
     * @return 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        for (byte aByte : bytes) {
            sb.append((byte) ((aByte & 0xf0) >>> 4));
            sb.append((byte) (aByte & 0x0f));
        }
        // return sb.toString().substring(0, 1).equalsIgnoreCase("0") ?
        // sb.toString().substring(1) : sb
        // .toString();
        return sb.toString();
    }

    /**
     * 10进制串转为BCD码
     *
     * @param asc 10进制串
     * @return BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }

        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }


    /** */
    /**
     * MD5加密字符串，返回加密后的16进制字符串
     *
     * @param origin
     * @return
     */
    public static String MD5EncodeToHex(String origin) {
        return bytesToHexString(MD5Encode(origin));
    }

    /** */
    /**
     * MD5加密字符串，返回加密后的字节数组
     *
     * @param origin
     * @return
     */
    public static byte[] MD5Encode(String origin) {
        return MD5Encode(origin.getBytes());
    }

    /** */
    /**
     * MD5加密字节数组，返回加密后的字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] MD5Encode(byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * UID左边补0
     *
     * @param str
     * @param length
     * @return
     */
    public static String fillZeroLeft(String str, int length) {
        if (str.length() < length) {
            StringBuilder sb = new StringBuilder(length);
            int len = length - str.length();
            for (int i = 0; i < len; i++) {
                sb.append('0');
            }
            sb.append(str);
            return sb.toString();
        } else {
            return str;
        }
    }

    /**
     * UID右边补0
     *
     * @param str
     * @param length
     * @return
     */
    public static String fillZeroRight(String str, int length) {
        if (str.length() < length) {
            StringBuilder sb = new StringBuilder(length);
            sb.append(str);
            int len = length - str.length();
            for (int i = 0; i < len; i++) {
                sb.append('0');
            }
            return sb.toString();
        } else {
            return str;
        }
    }

    /**
     * 在当前字符串右边填充指定字符，如果当前字符不够长度的话
     *
     * @param str
     * @param length
     * @return
     */
    public static String fillZeroRight(String str, int length, String fill) {
        if (str.length() < length) {
            StringBuilder sb = new StringBuilder(length);
            sb.append(str);
            int len = length - str.length();
            for (int i = 0; i < len; i++) {
                sb.append(fill);
            }
            return sb.toString();
        } else {
            return str;
        }
    }

    public static String regIpAddress(String url) {
        Pattern p = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    /**
     * 将指定字符串中的符合指定正则的部分替换为指定内容：
     * @param source
     * @param regex
     * @param target
     * @return
     */
    public static String replaceAll(String source, String regex, String target){
        if(LogicUtils.isNullOrEmpty(source) || LogicUtils.isNull(regex) || LogicUtils.isNull(target)){
            return null;
        }
        return source.replaceAll(regex, target);
    }

    /**
     * 将指定字符串中的指定索引后面的所有字符替换为指定内容：
     * @param source
     * @param position
     * @param target
     * @return
     */
    public static String replaceAfter(String source, int position, String target){
        if(LogicUtils.isNullOrEmpty(source) || position < 0 || position > source.length() || LogicUtils.isNull(target)){
            return source;
        }
        return source.substring(0, position) + target;
    }
}