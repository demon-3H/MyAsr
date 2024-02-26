package org.example.utils;



import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

@UtilityClass
@Slf4j
public class BaiduAsrUtil {

    //API_KEY和SECRET_KEY用于获取百度接口的访问权限
    public static  String API_KEY = "";
    public static  String SECRET_KEY = "";

    //用于建立httpl请求的对象
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();


    public void init(String key,String secret){
        API_KEY=key;
        SECRET_KEY=secret;

    }
    /**
     *调用接口，实现语音转文本
     *
     * @throws IOException
     */
    public String run(byte[] fileName) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        //构造请求所需要的参数
        MediaType mediaType = MediaType.parse("audio/m4a;rate=16000");
        RequestBody body = RequestBody.create(mediaType, fileName);
        Request request = new Request.Builder()
                .url("http://vop.baidu.com/server_api?dev_pid=1537&cuid=123456PHP&" +
                        "token="+getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "audio/m4a;rate=16000")
                .addHeader("Authorization", "Bearer "+getAccessToken())
                .addHeader("Cookie", "BAIDUID=111191DD119028E6EA6ADF2303AE4F79:FG=1")
                .build();

        //发送请求
        Response response = client.newCall(request).execute();

        //解析结果
        JSONObject jsonObject = new JSONObject(response.body().string());
        log.info(jsonObject.toString());
        JSONArray result1 = jsonObject.getJSONArray("result");
        log.info("解析为："+(String)result1.get(0));
        return (String) result1.get(0);
    }

    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {

        //构造获取access_token请求所需要的参数
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }
}

