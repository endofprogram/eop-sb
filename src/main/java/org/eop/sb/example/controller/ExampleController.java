package org.eop.sb.example.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eop.common.http.invoker.IHttpInvoker;
import org.eop.common.idgene.IdGenerator;
import org.eop.common.map.MapBuilder;
import org.eop.sb.example.bean.Example;
import org.eop.sb.example.service.IExampleService;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
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
	
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private IdGenerator antherOne;
	
	@Autowired
	private IHttpInvoker httpcomponentsHttpInvoker;
	@Autowired
	private IHttpInvoker okhttp3HttpInvoker;
	
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
	public int exception() throws Exception {
		throw new Exception();
	}
	
	@GetMapping(path = "/runtimeexception")
	public String runtimeException() {
		throw new RuntimeException();
	}
	
	@GetMapping(path = "/idgene")
	public String idgene() {
		return "id " + idGenerator.nextId() + "，" + idGenerator + "<br />"
				+ "id " + antherOne.nextId() + "，" + antherOne + "<br />";
	}
	
	@GetMapping(path = "/httpinvoker")
	public String httpinvoker() {
		return new Date() + "<br />"
				+ httpcomponentsHttpInvoker + "<br />"
				+ okhttp3HttpInvoker + "<br />";
	}
	
	@GetMapping(path = "/pathes")
	public Map<String, Object> pathes(HttpServletRequest request) {
		MapBuilder mb = new MapBuilder();
		mb.kv("Scheme", request.getScheme())
			.kv("ServerName", request.getServerName())
			.kv("ServerPort", request.getServerPort())
			.kv("ContextPath", request.getContextPath())
			.kv("ServletPath", request.getServletPath())
			.kv("PathInfo", request.getPathInfo())
			.kv("QueryString", request.getQueryString())
			.kv("RequestURI", request.getRequestURI())
			.kv("RequestURL", request.getRequestURL());
		return mb.toMap();
	}
	
	@GetMapping(path = "/proxyTargetClass")
	public String proxyTargetClass() {
		System.out.println("info about inject exampleService:");
		System.out.println("isJdkDynamicProxy => " + AopUtils.isJdkDynamicProxy(exampleService));
		System.out.println("isCglibProxy => " + AopUtils.isCglibProxy(exampleService));
		System.out.println("proxyClass => " + exampleService.getClass());
		System.out.println("parentClass => " + exampleService.getClass().getSuperclass());
		System.out.println("parentClass's interfaces => " + Arrays.asList(exampleService.getClass().getSuperclass().getInterfaces()));
		System.out.println("proxyClass's interfaces => " + Arrays.asList(exampleService.getClass().getInterfaces()));
		System.out.println("proxy => " + exampleService);
		System.out.println("target => " + AopProxyUtils.getSingletonTarget(exampleService));
		System.out.println("proxy == target => " + (exampleService == AopProxyUtils.getSingletonTarget(exampleService)));
		System.out.println("targetClass => " + AopProxyUtils.getSingletonTarget(exampleService).getClass());
		System.out.println("targetClass's interfaces => " + Arrays.asList(AopProxyUtils.getSingletonTarget(exampleService).getClass().getInterfaces()));
		
		System.out.println("----------------------------------------------------");
		
		Target target = new Target();
		ProxyByCGLIB proxy = new ProxyByCGLIB(target);
		proxy.doNeedTx();
		System.out.println("-------");
		proxy.doNotneedTx();
		System.out.println("-------");
		
		return "success";
	}
	
	static class Target {
		
		//@Transactional
		public void doNeedTx() {
			System.out.println("execute doNeedTx in Target");
		}
		
		//no annotation here
		public void doNotneedTx() {
			this.doNeedTx();
		}
	}
	
	static class ProxyByCGLIB extends Target {
		
		private Target target;
		
		public ProxyByCGLIB(Target target) {
			this.target = target;
		}
		
		@Override
		public void doNeedTx() {
			System.out.println("-> create Tx in Proxy");
			target.doNeedTx();
			System.out.println("<- commit Tx in Proxy");
		}
		
		@Override
		public void doNotneedTx() {
			target.doNotneedTx();
		}
	}
	
	
	@GetMapping(path = "/proxyInterface")
	public String proxyInterface() {
		System.out.println("info about inject exampleService:");
		System.out.println("isJdkDynamicProxy => " + AopUtils.isJdkDynamicProxy(exampleService));
		System.out.println("isCglibProxy => " + AopUtils.isCglibProxy(exampleService));
		System.out.println("proxyClass => " + exampleService.getClass());
		System.out.println("parentClass => " + exampleService.getClass().getSuperclass());
		System.out.println("parentClass's interfaces => " + Arrays.asList(exampleService.getClass().getSuperclass().getInterfaces()));
		System.out.println("proxyClass's interfaces => " + Arrays.asList(exampleService.getClass().getInterfaces()));
		System.out.println("proxy => " + exampleService);
		System.out.println("target => " + AopProxyUtils.getSingletonTarget(exampleService));
		System.out.println("proxy == target => " + (exampleService == AopProxyUtils.getSingletonTarget(exampleService)));
		System.out.println("targetClass => " + AopProxyUtils.getSingletonTarget(exampleService).getClass());
		System.out.println("targetClass's interfaces => " + Arrays.asList(AopProxyUtils.getSingletonTarget(exampleService).getClass().getInterfaces()));
		
		System.out.println("----------------------------------------------------");
		
		Service target = new ServiceImpl();
		ProxyByJdkDynamic proxy = new ProxyByJdkDynamic(target);
		proxy.doNeedTx();
		System.out.println("-------");
		proxy.doNotneedTx();
		System.out.println("-------");
		
		return "success";
	}
	
	static interface Service {
		void doNeedTx();
		
		void doNotneedTx();
	}
	
	static class ServiceImpl implements Service {
		
		//@Transactional
		@Override
		public void doNeedTx() {
			System.out.println("execute doNeedTx in ServiceImpl");
		}
		
		//no annotation here
		@Override
		public void doNotneedTx() {
			this.doNeedTx();
		}
	}
	
	static class ProxyByJdkDynamic implements Service {
		
		private Service target;
		
		public ProxyByJdkDynamic(Service target) {
			this.target = target;
		}
		
		@Override
		public void doNeedTx() {
			System.out.println("-> create Tx in Proxy");
			target.doNeedTx();
			System.out.println("<- commit Tx in Proxy");
		}
		
		@Override
		public void doNotneedTx() {
			target.doNotneedTx();
		}
	}
}
