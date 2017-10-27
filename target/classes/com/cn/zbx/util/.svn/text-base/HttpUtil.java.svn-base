package com.cn.zbx.util;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpUtil {
	   
    /**
     * 使用HttpClient文件上传
     * @param url
     * @param file
     */
	@SuppressWarnings("resource")
	public static String httpUpload(String url, File file) throws ClientProtocolException, IOException{
		HttpPost httppost = new HttpPost(url);
		HttpClient httpclient = new DefaultHttpClient();
		MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("file", new FileBody(file));//file1为请求后台的File upload;属性 
        httppost.setEntity(reqEntity);  
        HttpResponse res = httpclient.execute(httppost);
		if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
			HttpEntity resEntity = res.getEntity(); 
			String string = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);  
			return string; //返回jsonp格式数据  
		}else{
			return null;
		}
	}
}
