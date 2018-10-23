package jyx.com.aw.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 作者：-zy- on 16/6/29
 * 邮箱：zhouyong@cdzmd.com
 * 功能：app核心加密类
 */
public class AppCoreEncryptionUtil {

    private final static String OPEN_KEY = "5YvE3ASQNWxw2ZZQhMZU7PbI0ngVk5conZiZDGT8UomSo2aDFXxp5fG9LymwERAt";

    public static String getSign(String paramJsonStr) {
        if (TextUtils.isEmpty(paramJsonStr)) return "";

        try {
            JSONObject jsonObj = new JSONObject(paramJsonStr);
            Iterator<String> keyList = jsonObj.keys();
            Map<String, String> paramMap = new TreeMap<>();
            String curKey;
            while (keyList.hasNext()) {
                curKey = keyList.next();
                paramMap.put(curKey, jsonObj.optString(curKey, ""));
            }

            StringBuffer signStr = new StringBuffer();
            signStr.append(OPEN_KEY);
            for (Map.Entry<String, String> pm : paramMap.entrySet()) {
                if (pm.getValue().startsWith("[")) {
                } else {
                    signStr.append(pm.getKey()).append(pm.getValue());
                }
            }
            signStr.append(OPEN_KEY);
            return md5(signStr.toString()).toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 对字符串进行md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    /**
     * 转16进制再转大写
     *
     * @param bts
     * @return
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            //整数转成十六进制表示
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des.toUpperCase();
    }


}
