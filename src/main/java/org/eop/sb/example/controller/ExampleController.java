package org.eop.sb.example.controller;

import java.util.Date;

import org.eop.sb.example.bean.Example;
import org.eop.sb.example.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixinjie
 * @since 2018-09-03
 */
@RequestMapping(path = "/example")
@RestController
public class ExampleController {

	@Autowired
	private IExampleService exampleService;
	
	@GetMapping(path = "/new")
	public Example addExample() {
		Example example = new Example();
		example.setId(System.currentTimeMillis());
		example.setName("spring-boot");
		example.setAge(8);
		example.setBirthday(new Date());
		exampleService.insertExample(example);
		return example;
	}
	
	@GetMapping(path = "/test")
	public String test() {
		return "test " + new Date();
	}
	
	@GetMapping(path = "/args/{args}")
	public String badArgs(@PathVariable("args")Integer args) {
		return "参数 " + args;
	}
	
	@GetMapping(path = "/exception")
	public int exception() {
		return 1 / 0;
	}
}
