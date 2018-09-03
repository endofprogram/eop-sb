package org.eop.sb.example.dubbo.provider;

import org.eop.sb.example.dubbo.IExampleDubbo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@Service(timeout = 60, retries = -1)
public class ExampleDubboProvider implements IExampleDubbo {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

}
