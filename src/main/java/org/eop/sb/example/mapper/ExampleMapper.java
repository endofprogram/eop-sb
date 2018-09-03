package org.eop.sb.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.eop.sb.example.bean.Example;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@Mapper
public interface ExampleMapper {

	int insertExample(Example example);
	
	int updateExample(Example example);
	
	int deleteExample(long id);
	
	Example selectExample(long id);
}
