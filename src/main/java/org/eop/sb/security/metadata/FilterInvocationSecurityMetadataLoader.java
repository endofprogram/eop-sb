package org.eop.sb.security.metadata;

import java.util.Date;

/**
 * <p>安全元数据加载器
 * @author lixinjie
 * @since 2018-10-31
 */
public interface FilterInvocationSecurityMetadataLoader {

	/**
	 * 获取最后一次的修改时间
	 */
	Date getLastModified();
	
	/**
	 * 获取最后一次的加载时间
	 */
	Date getLastLoaded();
	
	/**
	 * 加载元数据
	 */
	void load();
	
	/**
	 * 重新加载元数据
	 */
	void reload();
}
