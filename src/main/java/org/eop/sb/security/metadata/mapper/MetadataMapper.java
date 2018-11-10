package org.eop.sb.security.metadata.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixinjie
 * @since 2018-10-31
 */
@Mapper
public interface MetadataMapper {

	/**
	 * 获取最后一次的修改时间
	 */
	Date getLastModified();
	
	/**
	 * 获取Url和角色关联表的所有记录
	 */
	List<Map<String, Object>> getAllUrlRoleMappings();
}
