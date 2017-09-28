package com.easyrong.wireless.finance.utils;

import com.easyrong.wireless.finance.base.BaseErrorInterfaceInfo;
import com.easyrong.wireless.finance.base.BaseRspEntity;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class OkHttpUtil {
    private Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    public static final MediaType mediaType
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String run(String url, Map<String, String> querys, String appcode) throws IOException {
        Request request = new Request.Builder()
                .url(createUrl(url, querys))
                .addHeader("Authorization", "APPCODE " + appcode)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String run(String url, Map<String, String> param) throws IOException {
        Request request = new Request.Builder()
                .url(createUrl(url,param))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            logger.info("[roc]" + res);
            return res;
        }
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String createUrl(String url, Map<String, String> params) {
        if (params != null && params.size() > 0) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(url);
                if (url.indexOf('&') > 0 || url.indexOf('?') > 0) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }
                for (Map.Entry<String, String> urlParams : params.entrySet()) {
                    //对参数进行 utf-8 编码,防止头信息传中文
                    String urlValue = URLEncoder.encode(urlParams.getValue(), "UTF-8");
                    sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                new BaseRspEntity(BaseErrorInterfaceInfo.BAD_REQUEST);
            }
        }
        return url;
    }
}
