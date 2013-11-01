package com.example.androidtest.Common;

import java.security.MessageDigest;

public class ShortLinkUtils {

    static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String shortUrl(String url) throws Exception {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(url.getBytes());
        String result = bufferToHex(messagedigest.digest());
        String resUrl = new String("");
        for (int i = 0; i < 8; i++) {
            String tmpString = result.substring(i * 4, i * 4 + 4);
            long hexLong = 0x3FFFFFFF & Long.parseLong(tmpString, 16);
            resUrl += hexDigits[Integer.valueOf((hexLong % 16) + "")] + "";
        }
        return resUrl;
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
    
    public static void main(String[] args) {
        String sLongUrl = "<a href='http://weibo.com/taobaotianshui?wvr=5&wvr=5&lf=reg'>"
                + "http://weibo.com/taobaotianshui?wvr=5&wvr=5&lf=reg"
                + "</a>";
        String shortUrl;
        try {
            shortUrl = shortUrl(sLongUrl);
            System.out.println(sLongUrl + " ===> " + shortUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}