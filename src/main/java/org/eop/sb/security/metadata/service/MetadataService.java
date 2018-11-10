package org.eop.sb.security.metadata.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author lixinjie
 * @since 2018-10-31
 */
public interface MetadataService {

	/**
	 * 最后一次修改时间
	 */
	Date getLastModified();
	
	/**
	 * 计算出所有url和角色的对应关系
	 */
	LinkedHashMap<String, List<String>> getAllUrlRolesMappings();
}
