//
//  AliPayConst.java
//  SpringRainDoctor
//
//  Created by Eden He on 2012-2-24
//  Copyright (c) 2012 Chunyu.mobi 
//  All rights reserved
//

package com.example.androidtest;

public class AliPayConst {

    // 合作商户ID。
    public static final String PARTNER_ID = "2088701531699317";

    // 商户收款的支付宝账号
    public static final String SELLER_ID = "2088701531699317";

    // 商户(RSA)私钥
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKp1rhpeatyJxApE"
            + "lbc7HmdZFogTrc6v2CWuQqWJ3w2cpSN/HcykpYhqTqe6UF86YTQNLEEhDePA6R7W"
            + "Nd4SaohuUvnc6rU0s1qtEurVyTNXwgVh+0ghi7krkOKiJMdeZ9FNpSSj/mTQcB4V"
            + "kdFXMS3OMUpjydIK0PrErZlPj/FnAgMBAAECgYBjg1gk5MIWBgeAxxFwav8Q7p2M"
            + "y5BIfbf+J4GZCqhBVhXCZ0+KTQSmYwmQOeD59ycQMDCJxTZKpDB0ZDIGd1HhaDnQ"
            + "Us6EREp3xHl91p2pvdkrJT7scjH7NYe6knI9QLG1EuZuVJZ/uxBanCz6c2sHha9W"
            + "KeuiWsDpLQaxc7RLYQJBANN1oQWnelJdGlDI+4m7w5xEwjPT08bSi9lZvTHV17rB"
            + "81c/R29Bg8TlIJyiEXyMz8Kjx5LcWy9Zeznu4QSpHT0CQQDOXT/rURmfyF+uhuNi"
            + "4r6+YxtrUsZFsnoBBRHVLWcKq6LjxfdceidYJEH8PqICYIbvpTLIWduOH39y30aA"
            + "cftzAkB8Gaa8qMOTHuzYyHtHJBjy1y+aNxEsuFg24QG2A6guBLGJXKvR0YNBxK7U"
            + "/hCBJiqwMmpn+EbTjRJSt6z7jTyJAkAdHsuGSUt0YLIfzqkHqzd/ZjzYSDXyX90X"
            + "h7+cdWu09cNmoOjnA9XJelRVZAE4lKZ16rnfy85Pn+I8M4WfWnZBAkEAyD2KGrUi"
            + "d0frNb0SUi9/pd+2QoMSfcKe846PnitbrjFsGOj5rvdqCCxSOoORozcEjCxUiYFh"
            + "0AxyrYraJY9O5g==";

    // 支付宝(RSA)公钥
    public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUBulnmLb+xc+a3Jun1UDOtOW8VULAPShEwc+e"
            + "V91pGBt01KEDpkv+EfW9CZgtxyhZxhGqhbEmg5ZThvPvniw0dqqV106218hFUsw5gwmi/+4I9xbn"
            + "Qr5tsNpyJOK9P1d4UFCWjp5o6QzIfDrPU30VP/1Bo58L4tvNkz42Pd0ObQIDAQAB";

    // 商户(MD5) KEY
    public static final String MD5_KEY = "zcj8lxk6ip023x2c0v4zr0i6y3lzibgm";

    // 支付方式列表
    public static final int WAP_TYPE_DEFAULT = 0;
    public static final int WAP_TYPE_DEBITCARD = 1;
    public static final int WAP_TYPE_CREDITCARD = 2;
    public static final int WAP_TYPE_CREDITCARD_CMB = 20;// 招行
    public static final int WAP_TYPE_CREDITCARD_CCB = 21;// 建行
    public static final int WAP_TYPE_CREDITCARD_ICBC = 22;// 工行
    public static final int WAP_TYPE_CREDITCARD_BOC = 23;// 中行
    public static final int WAP_TYPE_CREDITCARD_ABC = 24;// 农行
    public static final int WAP_TYPE_CREDITCARD_GDB = 25;// 广发
    public static final int WAP_TYPE_CREDITCARD_CIB = 26;// 兴业
    public static final int WAP_TYPE_CREDITCARD_CMBC = 27;// 民生
    
    //  回调URL
    public static final String CALLBACK_URL = "chunyu://paymentfinish";
}
