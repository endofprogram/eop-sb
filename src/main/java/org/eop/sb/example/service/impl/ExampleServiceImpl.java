package org.eop.sb.example.service.impl;

import org.eop.sb.example.bean.Example;
import org.eop.sb.example.mapper.ExampleMapper;
import org.eop.sb.example.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@Service
public class ExampleServiceImpl implements IExampleService {

	@Autowired
	private ExampleMapper exampleMapper;
	
	@Transactional
	@Override
	public int insertExample(Example example) {
		return exampleMapper.insertExample(example);
	}

	@Transactional
	@Override
	public int updateExample(Example example) {
		return exampleMapper.updateExample(example);
	}

	@Transactional
	@Override
	public int deleteExample(long id) {
		return exampleMapper.deleteExample(id);
	}

	@Override
	public Example selectExample(long id) {
		return exampleMapper.selectExample(id);
	}

}
