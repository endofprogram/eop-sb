package org.eop.sb.example.dubbo.controller;

import org.eop.sb.example.dubbo.IExampleDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@RequestMapping(path = "/dubbo")
@RestController
public class ExampleDubboController {

	@Autowired
	private IExampleDubbo exampleDubboConsumer;
	
	@GetMapping(path = "/test")
	public String test() {
		return exampleDubboConsumer.sayHello(String.valueOf(System.currentTimeMillis()));
	}
}
