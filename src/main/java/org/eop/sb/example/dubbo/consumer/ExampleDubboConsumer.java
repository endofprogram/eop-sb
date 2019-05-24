package org.eop.sb.example.dubbo.consumer;

import org.eop.sb.example.dubbo.IExampleDubbo;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@Component
public class ExampleDubboConsumer implements IExampleDubbo {

	@Reference(timeout = 60000, retries = -1, check = false)
	private IExampleDubbo exampleDubbo;
	
	@Override
	public String sayHello(String name) {
		return exampleDubbo.sayHello(name);
	}

}
