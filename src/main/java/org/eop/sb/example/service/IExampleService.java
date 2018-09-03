package org.eop.sb.example.service;

import org.eop.sb.example.bean.Example;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
public interface IExampleService {

	int insertExample(Example example);
	
	int updateExample(Example example);
	
	int deleteExample(long id);
	
	Example selectExample(long id);
}
