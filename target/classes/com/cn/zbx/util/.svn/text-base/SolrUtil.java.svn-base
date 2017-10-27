package com.cn.zbx.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.zbx.commons.Constants;

/**
 * solr
 * @author zyw
 * 动态字段,类型,属性
 * <dynamicField name="*_i"  type="int"    indexed="true"  stored="true"/>
 * <dynamicField name="*_is" type="int"    indexed="true"  stored="true"  multiValued="true"/>
 * <dynamicField name="*_s"  type="string"  indexed="true"  stored="true" />
 * <dynamicField name="*_ss" type="string"  indexed="true"  stored="true" multiValued="true"/>
 * <dynamicField name="*_l"  type="long"   indexed="true"  stored="true"/>
 * <dynamicField name="*_ls" type="long"   indexed="true"  stored="true"  multiValued="true"/>
 * <dynamicField name="*_t"  type="text_general"    indexed="true"  stored="true"/>
 * <dynamicField name="*_txt" type="text_general"   indexed="true"  stored="true" multiValued="true"/>
 * <dynamicField name="*_en"  type="text_en"    indexed="true"  stored="true" multiValued="true"/>
 * <dynamicField name="*_b"  type="boolean" indexed="true" stored="true"/>
 * <dynamicField name="*_bs" type="boolean" indexed="true" stored="true"  multiValued="true"/>
 * <dynamicField name="*_f"  type="float"  indexed="true"  stored="true"/>
 * <dynamicField name="*_fs" type="float"  indexed="true"  stored="true"  multiValued="true"/>
 * <dynamicField name="*_d"  type="double" indexed="true"  stored="true"/>
 * <dynamicField name="*_ds" type="double" indexed="true"  stored="true"  multiValued="true"/>
 * <dynamicField name="*_coordinate"  type="tdouble" indexed="true"  stored="false" />
 * <dynamicField name="*_dt"  type="date"    indexed="true"  stored="true"/>
 * <dynamicField name="*_dts" type="date"    indexed="true"  stored="true" multiValued="true"/>
 * <dynamicField name="*_p"  type="location" indexed="true" stored="true"/>
 * <dynamicField name="*_ti" type="tint"    indexed="true"  stored="true"/>
 * <dynamicField name="*_tl" type="tlong"   indexed="true"  stored="true"/>
 * <dynamicField name="*_tf" type="tfloat"  indexed="true"  stored="true"/>
 * <dynamicField name="*_td" type="tdouble" indexed="true"  stored="true"/>
 * <dynamicField name="*_tdt" type="tdate"  indexed="true"  stored="true"/>
 * <dynamicField name="*_c"   type="currency" indexed="true"  stored="true"/>
 * <dynamicField name="ignored_*" type="ignored" multiValued="true"/>
 * <dynamicField name="attr_*" type="text_general" indexed="true" stored="true" multiValued="true"/>
 * <dynamicField name="random_*" type="random" />
 */
public class SolrUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SolrUtil.class);
	
	private static HttpSolrClient httpSolrClient = new HttpSolrClient(Constants.SOLR_URL);
	
	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();
	private static Lock delLock = new ReentrantLock();
	
	/**
	 * 删除索引根据uuid
	 * @param uuidId
	 * @return
	 */
	public static boolean delSolrIndex(String id){
		delLock.lock();
		try {
			httpSolrClient.deleteById(id);
			UpdateResponse deleteById = httpSolrClient.deleteById(id);
			if(deleteById.getStatus() == 0){
				httpSolrClient.commit();
				return true;
			}else{
				return false;
			}
		} catch (SolrServerException | IOException e) {
			LOGGER.error("Exception createSolrIndex =>:{}",e);
			return false;
		}finally{
			delLock.unlock();
		}
	}
	
	
	
	/**
	 * 根据条件查询solr,获取数据
	 * @param solrQuery	查询条件
	 * @param solrPGoodsVO (详情请看vo内部注释)
	 * @param sortName	需要排序的字段名称	 null为不排序
	 * @param sortType	asc/desc ,传null时默认为asc
	 * @param pageNum	页码默认为1,传0时默认为1 
	 * @param pageSize	每页条说,传0时默认为10 
	 * @return	查询的数据
	 */
//	@SuppressWarnings("unchecked")
//	public static List<SolrPGoodsVO> getSolrDocumentListBySolrPGoodsVO(SolrPGoodsVO solrPGoodsVO,String sortName,String sortType,int pageNum,int pageSize){
//		lock1.lock();
//		try{
//			List<SolrPGoodsVO> solrPGoodsVOList = new ArrayList<SolrPGoodsVO>();
//			SolrQuery solrQuery = new SolrQuery();
//			
//			String solrSql = "*:* AND ";
//			if(solrPGoodsVO == null){}else{
//				if(solrPGoodsVO.getId() == null || solrPGoodsVO.getId().equals("") || solrPGoodsVO.getId() == ""){}else{
//					solrQuery.addFilterQuery("id:"+solrPGoodsVO.getId());
//				}
//				if(solrPGoodsVO.getPhone_s() == null || solrPGoodsVO.getPhone_s().equals("") || solrPGoodsVO.getPhone_s() == ""){}else{
//					solrQuery.addFilterQuery("phone_s:"+solrPGoodsVO.getPhone_s());
//				}
//				if(solrPGoodsVO.getIp_s() == null || solrPGoodsVO.getIp_s().equals("") || solrPGoodsVO.getIp_s() == ""){}else{
//					solrQuery.addFilterQuery("ip_s:"+solrPGoodsVO.getIp_s());
//				}
//				if(solrPGoodsVO.getType_i() == null || solrPGoodsVO.getType_i() == 0){}else{
//					solrQuery.addFilterQuery("type_i:"+solrPGoodsVO.getType_i());
//				}
////				if(solrPGoodsVO.getName() == null || solrPGoodsVO.getName().equals("") || solrPGoodsVO.getName() == ""){}else{
////					solrSql += "name:"+solrPGoodsVO.getName() +"AND ";
////				}
//			}
//			
//			//把拼接的solrSql去除最后的AND
//			solrQuery.setQuery(solrSql.substring(0, solrSql.length()-4));
//			
//			//添加排序
//			if(sortName == null || sortName.equals("") || sortName == ""){}else{
//				solrQuery.addSort(sortName, ORDER.asc);	//根据指定字段排序
//				if(sortType != null && sortType.equals("desc")){
//					solrQuery.addSort(sortName, ORDER.desc);	//根据指定字段排序
//				}
//			}
//			
//			if(pageNum == 0){
//				pageNum = 1;
//			}
//			if(pageSize == 0){
//				pageSize = 10;
//			}
//			//分页
//			solrQuery.setStart((pageNum - 1)*pageSize);
//			solrQuery.setRows(pageSize);
//			
//			solrQuery.setHighlight(true);                				//开启高亮  
//			solrQuery.setHighlightFragsize(10);          				//返回的字符个数  
//			solrQuery.setHighlightRequireFieldMatch(true);  
//			solrQuery.setHighlightSimplePre("<span style='color:red'>");    	//后缀  
//			solrQuery.setHighlightSimplePost("</span>");    			//前缀  
//			solrQuery.setParam("hl.fl");     	   						//高亮字段  
//			solrQuery.addHighlightField("phone_s");
//			solrQuery.addHighlightField("ip_s");
//			try {
//				QueryResponse response = httpSolrClient.query(solrQuery);
//				System.out.println(response);
//				Map<String, Map<String, List<String>>> map=response.getHighlighting();  
//				System.out.println(map);
//				SolrDocumentList docs = response.getResults();
//				System.out.println(docs.size());
//				for (SolrDocument solrDocument : docs) {
//					SolrPGoodsVO sgVO = new SolrPGoodsVO();
//					
//					if(map.get(solrDocument.getFieldValue("id")).get("phone_s") == null || map.get(solrDocument.getFieldValue("id")).get("phone_s").equals("null")){
//						sgVO.setPhone_s((String)solrDocument.get("phone_s"));
//					}else{
//						solrDocument.setField("phone_s", map.get(solrDocument.getFieldValue("id")).get("phone_s")); //设置高亮
//						ArrayList<String> nameList = (ArrayList<String>)solrDocument.get("phone_s");
//						if(nameList != null && nameList.size() > 0){
//							sgVO.setPhone_s(nameList.get(0));;
//						}
//					}
//					if(map.get(solrDocument.getFieldValue("id")).get("ip_s") == null || map.get(solrDocument.getFieldValue("id")).get("ip_s").equals("null")){
//						sgVO.setIp_s((String)solrDocument.get("ip_s"));
//					}else{
//						solrDocument.setField("address", map.get(solrDocument.getFieldValue("id")).get("ip_s"));  //设置高亮
//						ArrayList<String> addressList = (ArrayList<String>)solrDocument.get("ip_s");
//						if(addressList != null && addressList.size() > 0){
//							sgVO.setIp_s(addressList.get(0));;
//						}
//					}
//					sgVO.setId((String)solrDocument.get("id"));
//					sgVO.setTime_dt((Date)solrDocument.get("time_dt"));
//					sgVO.setType_i((Integer)solrDocument.get("type_i"));
//					solrPGoodsVOList.add(sgVO);
//				}
//				return solrPGoodsVOList;
//			} catch (IOException | SolrServerException e) {
//				LOGGER.error("Exception getSolrDocumentListBySolrPGoodsVO =>:{}",e);
//				return null;
//			}
//		}finally{
//			lock1.unlock();
//		}
//	}
	
	/**
	 * 创建索引
	 * @param solrPGoodsVO
	 * @return
	 */
//	public static boolean createSolrIndex(SolrPGoodsVO solrPGoodsVO){
//		lock2.lock();
//		try{
//			SolrInputDocument solrInputDocument = new SolrInputDocument();
//			solrInputDocument.addField("id", solrPGoodsVO.getId());
//			solrInputDocument.addField("phone_s", solrPGoodsVO.getPhone_s());
//			solrInputDocument.addField("time_dt", solrPGoodsVO.getTime_dt());
//			solrInputDocument.addField("ip_s", solrPGoodsVO.getIp_s());
//			solrInputDocument.addField("type_i", solrPGoodsVO.getType_i());
//			try {
//				UpdateResponse add = httpSolrClient.add(solrInputDocument);
//				if(add.getStatus() == 0){
//					httpSolrClient.commit();
//					return true;
//				}else{
//					return false;
//				}
//			} catch (SolrServerException | IOException e) {
//				LOGGER.error("Exception createSolrIndex =>:{}",e);
//				return false;
//			}
//		}finally{
//			lock2.unlock();
//		}
//	}
	
	
	/**
	 * 查找B登录信息
	 * @param bLoginMsgVO
	 * @return
	 */
//	public static List<BLoginMsgVO> getListByBLoginMsgVO(BLoginMsgVO bLoginMsgVO){
//		List<BLoginMsgVO> solrPGoodsVOList = new ArrayList<BLoginMsgVO>();
//		SolrQuery solrQuery = new SolrQuery();
//		String solrSql = "*:* AND ";
//		if(bLoginMsgVO == null){}else{
//			solrQuery.addFilterQuery("type_i:"+Constants.type_2);
//			if(bLoginMsgVO.getImei() == null || bLoginMsgVO.getImei().equals("") || bLoginMsgVO.getImei() == ""){}else{
//				solrQuery.addFilterQuery("id:"+bLoginMsgVO.getImei());
//			}
//			if(bLoginMsgVO.getName() == null || bLoginMsgVO.getName().equals("") || bLoginMsgVO.getName() == ""){}else{
//				solrQuery.addFilterQuery("name_s:"+bLoginMsgVO.getName());
//			}
//			if(null == bLoginMsgVO.getAuditType()){}else{
//				solrQuery.addFilterQuery("auditType_i:"+bLoginMsgVO.getAuditType());
//			}
//		}
//		//把拼接的solrSql去除最后的AND
//		solrQuery.setQuery(solrSql.substring(0, solrSql.length()-4));
//		solrQuery.setStart(0);
//		solrQuery.setRows(1000);
//		try {
//			QueryResponse response = httpSolrClient.query(solrQuery);
//			SolrDocumentList docs = response.getResults();
//			for (SolrDocument solrDocument : docs) {
//				BLoginMsgVO sgVO = new BLoginMsgVO();
//				sgVO.setImei((String)solrDocument.get("id"));
//				sgVO.setTime((String)solrDocument.get("time_s"));
//				sgVO.setType((Integer)solrDocument.get("type_i"));
//				sgVO.setAuditType((Integer)solrDocument.get("auditType_i"));
//				sgVO.setName((String)solrDocument.get("name_s"));
//				solrPGoodsVOList.add(sgVO);
//			}
//			return solrPGoodsVOList;
//		} catch (IOException | SolrServerException e) {
//			LOGGER.error("Exception getSolrDocumentListBySolrPGoodsVO =>:{}",e);
//			return null;
//		}
//	}
	
	/**
	 * 创建索引
	 * @param solrPGoodsVO
	 * @return
	 */
//	public static boolean createSolrIndex(BLoginMsgVO bLoginMsgVO){
//		lock2.lock();
//		try{
//			SolrInputDocument solrInputDocument = new SolrInputDocument();
//			solrInputDocument.addField("id", bLoginMsgVO.getImei());
//			solrInputDocument.addField("time_s", bLoginMsgVO.getTime());
//			solrInputDocument.addField("type_i", bLoginMsgVO.getType());
//			solrInputDocument.addField("auditType_i", bLoginMsgVO.getAuditType());
//			solrInputDocument.addField("name_s", bLoginMsgVO.getName());
//			try {
//				UpdateResponse add = httpSolrClient.add(solrInputDocument);
//				if(add.getStatus() == 0){
//					httpSolrClient.commit();
//					return true;
//				}else{
//					return false;
//				}
//			} catch (SolrServerException | IOException e) {
//				LOGGER.error("Exception createSolrIndex =>:{}",e);
//				return false;
//			}
//		}finally{
//			lock2.unlock();
//		}
//	}
	
	
	public static void main(String[] args){
//		System.out.println(createSolrIndex(new SolrPGoodsVO("15811019845", "127.0.0.1",Constants.type_1)));
//		SolrPGoodsVO solrPGoodsVO2 = new SolrPGoodsVO();
//		solrPGoodsVO2.setType_i(Constants.type_1);
//		List<SolrPGoodsVO> solrDocumentListBySolrPGoodsVO = getSolrDocumentListBySolrPGoodsVO(solrPGoodsVO2, null, null, 0, 100000);
//		for (SolrPGoodsVO solrPGoodsVO : solrDocumentListBySolrPGoodsVO) {
//			System.out.println("遍历索引:" + solrPGoodsVO);
//			System.out.println("删除索引:" + delSolrIndex(solrPGoodsVO.getId()) + "------------" + solrPGoodsVO.getId());
//		}
		
		System.out.println(System.getProperty("java.home"));
//		List<BLoginMsgVO> listByBLoginMsgVO = getListByBLoginMsgVO(new BLoginMsgVO("864895022768525",1));
//		for (BLoginMsgVO bLoginMsgVO : listByBLoginMsgVO) {
//			System.out.println(bLoginMsgVO);
//			System.out.println("删除索引:" + delSolrIndex(bLoginMsgVO.getImei()) + "------------" + bLoginMsgVO.getImei());
//		}
		
	}
}
