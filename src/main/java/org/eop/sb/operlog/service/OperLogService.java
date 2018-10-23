package org.eop.sb.operlog.service;

import org.eop.sb.operlog.bean.OperLog;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public interface OperLogService {

	int insertOperLog(OperLog operLog);
}
