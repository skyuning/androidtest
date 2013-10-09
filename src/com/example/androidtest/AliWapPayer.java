package com.example.androidtest;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.androidtest.Common.RSASignature;

import android.content.Context;
import android.util.Log;

public class AliWapPayer {
    
    public static final boolean signByMd5 = false; // 采用RSA解码之后会出现乱码?
    
    private static final String URL = "http://wappaygw.alipay.com/service/rest.htm";
    private static final String SERVICE_DIRECT_TRADE_CREATE = "alipay.wap.trade.create.direct";
    private static final String SEC_ID = "0001";
    private static final String FORMAT = "xml";
    private static final String V = "2.0";

    private String mSubject;
    private String mOutTradeNo;
    private String mTotalFee;
    private String mSellerAccountName;
    private String mPartner;
    private String mReqId;
    private String mRsaPrivate;
    
    private String mNotifyUrl;
    private String mOutUser;
    private String mMerchantUrl;
    private String mPayExpire;
    
    public AliWapPayer(Context context, String subject, String outTradeNo,
            String totalFee, String sellerAccountName, String partner,
            String reqId, String rsaPrivate) {
        mSubject = subject;
        mOutTradeNo = outTradeNo;
        mTotalFee = totalFee;
        mSellerAccountName = sellerAccountName;
        mPartner = partner;
        mReqId = reqId;
        mRsaPrivate = rsaPrivate;
    }
    
    public Object createDirectTrade() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(URL);
            List<NameValuePair> postData = buildPostData();
            post.setEntity(new UrlEncodedFormEntity(postData, "utf-8"));
            HttpResponse response = client.execute(post);
            
            String content = EntityUtils.toString(response.getEntity());
            URI uri = new URI("http://a.com?" + content);
            List<NameValuePair> result = URLEncodedUtils.parse(uri, "utf-8");
            Map<String, String> map = new HashMap<String, String>();
            for (NameValuePair r : result)
                map.put(r.getName(), r.getValue());
            String resData2 = map.get("res_data");
            String decodedResData = RSASignature.decrypt(resData2, AliPayConst.RSA_PRIVATE);
            map.put("decoded_res_data", decodedResData);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private List<NameValuePair> buildPostData() {
        String queryString = "format=" + FORMAT;
        queryString += "&partner=" + mPartner;
        queryString += "&req_data=" + buildReqData();
        queryString += "&req_id=" + mReqId;
        queryString += "&sec_id=" + SEC_ID;
        queryString += "&service=" + SERVICE_DIRECT_TRADE_CREATE;
        queryString += "&v=" + V;
        String sign = RSASignature.sign(queryString, mRsaPrivate, "utf-8");
        
        List<NameValuePair> postData = new ArrayList<NameValuePair>();
        postData.add(new BasicNameValuePair("format", FORMAT));
        postData.add(new BasicNameValuePair("partner", mPartner));
        postData.add(new BasicNameValuePair("req_data", buildReqData()));
        postData.add(new BasicNameValuePair("req_id", mReqId));
        postData.add(new BasicNameValuePair("sec_id", SEC_ID));
        postData.add(new BasicNameValuePair("service", SERVICE_DIRECT_TRADE_CREATE));
        postData.add(new BasicNameValuePair("v", V));
        postData.add(new BasicNameValuePair("sign", sign));
        
        return postData;
    }
    
    private String buildReqData() {
        String reqData = "<direct_trade_create_req>"
                + formatOneReqData("subject", mSubject)
                + formatOneReqData("out_trade_no", mOutTradeNo)
                + formatOneReqData("total_fee", mTotalFee)
                + formatOneReqData("seller_account_name", mSellerAccountName);
        if (mNotifyUrl != null)
            reqData += formatOneReqData("notify_url", mNotifyUrl);
        if (mOutUser != null)
            reqData += formatOneReqData("out_user", mOutUser);
        if (mMerchantUrl != null)
            reqData += formatOneReqData("merchant_url", mMerchantUrl);
        if (mPayExpire != null)
            reqData += formatOneReqData("pay_expire", mPayExpire);
        reqData += "</direct_trade_create_req>";
        return reqData;
    }
    
    private String formatOneReqData(String key, String value) {
        String format = "<%s>%s</%s>";
        String formatedData = String.format(format, key, value, key);
        return formatedData;
    }
}
