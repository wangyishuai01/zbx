package com.cn.zbx.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cn.zbx.commons.Constants;
import com.cn.zbx.util.CookiesUtil;
import com.cn.zbx.util.DateUtils;

@Controller
@RequestMapping(value="/upload")
public class UploadController {
	
	/**
	 * 上传视频
	 * @param request
	 * @param response
	 * @param video
	 * @return
	 * @throws FileUploadException 
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/uploadVideo", method = { RequestMethod.GET, RequestMethod.POST })
	public String uploadVideo(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("videoFile") MultipartFile file) throws FileUploadException, IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Date currentDate = new Date();
		if(file.isEmpty()==false){
			String basePath = Constants.VIDEO_UPLOAD_URL;
			String datePath = DateUtils.format(currentDate, "yyyyMMdd")+"/";
	        String realName = file.getOriginalFilename();
	        
	        /*获取上传文件的后缀名*/
	        String suffix =  realName.substring(realName.lastIndexOf("."));
	        if(Constants.VIDEO_SUFFIX.indexOf(suffix.toUpperCase()) >= 0){
	        	/* 使用时间戳生成文件名称 */
		        String videoName = DateUtils.format(currentDate, "yyyyMMddHHmmss") + suffix;// 构建文件名称

		        File targetFile = new File(basePath+datePath, videoName);
		        if(!targetFile.exists()){
		            targetFile.mkdirs();
		        }
		        try {
		            //保存文件到本地
		            file.transferTo(targetFile);
		            resultMap.put("path", basePath+datePath+videoName);
		            resultMap.put("msg", "上传完成！");
		            resultMap.put("success", true);
		        } catch (Exception e) {
		        	resultMap.put("errorMsg", "远程保存视频失败！");
		        	resultMap.put("success", false);
		            e.printStackTrace();
		        }
	        } else {
	        	resultMap.put("errorMsg", "上传文件不是视频，请上传视频文件！");
	        	resultMap.put("success", false);
	        }
		} else {
			resultMap.put("errorMsg", "上传的视频已损坏，请重新上传！");
        	resultMap.put("success", false);
		}
		return JSONObject.toJSONString(resultMap);
	}
}
