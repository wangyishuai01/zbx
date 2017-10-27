package com.cn.zbx.pojo;


import java.util.List;

import com.cn.zbx.pojo.Tclassify;

/**
 * @Class Name ITClassifyService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface ITClassifyService {

	List<Tclassify> selectBySelectParam(Tclassify record);

	
}
