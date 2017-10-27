package com.cn.zbx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * JSON转换工具类 
 *  
 * @author zyw 
 * @date 2014-04-04 
 */  
public class JsonUtil {
	public static void main(String[] args) {
		String json = "[TApplys [id=10006, imgUrl=http://apply.moko.cn:8080/files/year2016/month6/day24/hour15/minute56/dba0223d1d574c8aa83831209ac06b98.jpg, videoUrl=http://moko.ufile.ucloud.com.cn/23A04646-F227-4CCD-A95C-4B3D1D830B17.MOV, flowingUrl=null, remark=大家好，我是悠悠，来自浙江的美女一枚，目前在传媒大学读书，毕业后希望能去美国好莱坞继续深造，喜欢美食和环游世界，我希望成立自己的演艺工作室，朋友们来关注我吧，么么哒。, auditType=1, supportCount=4, usersId=432, fundsId=5, reportsId=2, earningsId=2, genrsId=4, auditRemark=null, roomId=null, pushUrl=null, pullUrl=null, playName=null, accid=null, cid=null, isWatch=null, onlineCount=0, UserId=432, UserName=null, UserNike=悠悠, UserPhone=13855555555, UserSex=2, UserImgUrl=null, UserBirthday=null, UserType=2, UserAuthType=1, UserImei=0F295A69-32FF-40D9-8791-F6BB3CF0A039], TApplys [id=10001, imgUrl=http://apply.moko.cn:8080/files/year2016/month6/day24/hour13/minute0/864c84df782c46838e50cbca8c30d3f1.jpg, videoUrl=http://moko.ufile.ucloud.com.cn/5946B002-7416-4588-ADD8-30DBE37A42B1.MOV, flowingUrl=null, remark=要200万开演唱会, auditType=2, supportCount=1, usersId=436, fundsId=5, reportsId=2, earningsId=4, genrsId=6, auditRemark=null, roomId=null, pushUrl=null, pullUrl=null, playName=null, accid=null, cid=null, isWatch=null, onlineCount=0, UserId=436, UserName=null, UserNike=green day, UserPhone=13154545454, UserSex=3, UserImgUrl=null, UserBirthday=null, UserType=2, UserAuthType=1, UserImei=7FF267EF-84EB-4EDD-9FFD-E0C7064B5A57], TApplys [id=10000, imgUrl=http://apply.moko.cn:8080/files/year2016/month6/day24/hour10/minute21/e1bc8100d9554bcd966413104a35379b.jpg, videoUrl=null, flowingUrl=null, remark=我, auditType=0, supportCount=0, usersId=435, fundsId=1, reportsId=2, earningsId=2, genrsId=911, auditRemark=null, roomId=null, pushUrl=null, pullUrl=null, playName=null, accid=null, cid=null, isWatch=null, onlineCount=0, UserId=435, UserName=null, UserNike=我, UserPhone=18370660621, UserSex=1, UserImgUrl=null, UserBirthday=null, UserType=2, UserAuthType=1, UserImei=868904026597843], TApplys [id=273, imgUrl=http://apply.moko.cn:8080/files/year2016/month6/day15/hour15/minute_10_17/366f47289ef14bd9857a8845d99181fa.jpg, videoUrl=http://moko.ufile.ucloud.com.cn/870AF3F3-0300-48DA-965A-2519786784F6.MOV, flowingUrl=null, remark=萨, auditType=1, supportCount=0, usersId=412, fundsId=1, reportsId=2, earningsId=1, genrsId=911, auditRemark=null, roomId=null, pushUrl=null, pullUrl=null, playName=null, accid=null, cid=null, isWatch=null, onlineCount=0, UserId=412, UserName=null, UserNike=看上课上课的, UserPhone=18618359592, UserSex=1, UserImgUrl=null, UserBirthday=null, UserType=2, UserAuthType=1, UserImei=7FF267EF-84EB-4EDD-9FFD-E0C7064B5A57]]";
	
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id","123");
		mapList.add(map);
		System.out.println(mapList);
		
		
	
	
	
	}
	    /** 
	     * 对象转换成JSON字符串 
	     *  
	     * @param obj 
	     *            需要转换的对象 
	     * @return 对象的string字符 
	     */  
	    public static String toJson(Object obj) {  
	        JSONObject jSONObject = JSONObject.fromObject(obj);  
	        return jSONObject.toString();  
	    }  
	    
	    public static String listToJson(Object obj) {  
	    	JSONArray json = JSONArray.fromObject(obj);
	        return json.toString();  
	    }  
	  
	    /** 
	     * JSON字符串转换成对象 
	     *  
	     * @param jsonString 
	     *            需要转换的字符串 
	     * @param type 
	     *            需要转换的对象类型 
	     * @return 对象 
	     */  
	    @SuppressWarnings("unchecked")
		public static <T> T fromJson(String jsonString, Class<T> type) {
	        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
	        return (T) JSONObject.toBean(jsonObject, type);  
	    }  
	  
	    /** 
	     * 将JSONArray对象转换成list集合 
	     *  
	     * @param jsonArr 
	     * @return 
	     */  
	    public static List<Object> jsonToList(JSONArray jsonArr) {  
	        List<Object> list = new ArrayList<Object>();  
	        for (Object obj : jsonArr) {  
	            if (obj instanceof JSONArray) {  
	                list.add(jsonToList((JSONArray) obj));  
	            } else if (obj instanceof JSONObject) {  
	                list.add(jsonToMap((JSONObject) obj));  
	            } else {  
	                list.add(obj);  
	            }  
	        }  
	        return list;  
	    }  
	  
	    /** 
	     * 将json字符串转换成map对象 
	     *  
	     * @param json 
	     * @return 
	     */  
	    public static Map<String, Object> jsonToMap(String json) {  
	        JSONObject obj = JSONObject.fromObject(json);  
	        return jsonToMap(obj);  
	    }  
	  
	    /** 
	     * 将JSONObject转换成map对象 
	     *  
	     * @param json 
	     * @return 
	     */  
	    public static Map<String, Object> jsonToMap(JSONObject obj) {  
	        Set<?> set = obj.keySet();  
	        Map<String, Object> map = new HashMap<String, Object>(set.size());  
	        for (Object key : obj.keySet()) {  
	            Object value = obj.get(key);  
	            if (value instanceof JSONArray) {  
	                map.put(key.toString(), jsonToList((JSONArray) value));  
	            } else if (value instanceof JSONObject) {  
	                map.put(key.toString(), jsonToMap((JSONObject) value));  
	            } else {  
	                map.put(key.toString(), obj.get(key));  
	            }  
	  
	        }  
	        return map;  
	    }  
}
