package org.eop.sb.operlog.aspect;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.eop.common.idgene.IdGenerator;
import org.eop.sb.accesscontrol.bean.Role;
import org.eop.sb.accesscontrol.bean.User;
import org.eop.sb.operlog.annotation.ModuleLog;
import org.eop.sb.operlog.annotation.OperateLog;
import org.eop.sb.operlog.bean.OperLog;
import org.eop.sb.operlog.enums.Entity;
import org.eop.sb.operlog.enums.Module;
import org.eop.sb.operlog.enums.Operate;
import org.eop.sb.operlog.service.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.alibaba.fastjson.JSON;


/**
 * @author lixinjie
 * @since 2018-01-08
 */
@Component
@Aspect
public class OperLogAspect {

	private static final Logger log = LoggerFactory.getLogger(OperLogAspect.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	private OperLogService operLogService;
	
	/**
	 * <p>定义一个切点
	 */
	@Pointcut(value = "execution(* com.cmos.ha.cmms.manage.*.controller.*.*(..)) && @target(moduleLog) && @annotation(operateLog)", argNames="moduleLog,operateLog")
	private void pointcut(ModuleLog moduleLog, OperateLog operateLog) { }
	
	/**
	 * <p>在被拦截的方法调用前执行（用于删除类型的操作）
	 */
	@Before("pointcut(moduleLog, operateLog)")
	public void beforeMethodInvoke(JoinPoint jp, ModuleLog moduleLog, OperateLog operateLog) {
		try {
			if (operateLog.operate().toString().endsWith("Delete")) {
				if (operateLog.serviceClass() == Object.class) {
					throw new IllegalArgumentException("the serviceClass of OperateLog must be set when operate is delete");
				}
				if (operateLog.methodName().isEmpty()) {
					throw new IllegalArgumentException("the methodName of OperateLog must be set when operate is delete");
				}
				User user = getCurrentUser();
				Object service = applicationContext.getBean(operateLog.serviceClass());
				String methodName = operateLog.methodName();
				Object argument = jp.getArgs()[0];
				Object[] args = new Object[0];
				if (argument instanceof Object[]) {
					args = (Object[])argument;
				} else {
					args = new Object[] {argument};
				}
				for (Object arg : args) {
					Object bean = getBean(service, methodName, arg);
					String id = getBeanId(bean, operateLog.idField());
					String title = getBeanTitle(bean, operateLog.titleFields());
					
					OperLog operLog = new OperLog();
					operLog.setId(idGenerator.nextId());
					
					operLog.setUserId(0L);
					operLog.setUsername("");
					operLog.setUserno("");
					operLog.setName("");
					operLog.setRoles("");
					
					Module module = moduleLog.module();
					operLog.setModuleCode(module.getCode());
					operLog.setModuleName(module.getName());
					Entity entity = operateLog.entity();
					operLog.setEntityCode(entity.getCode());
					operLog.setEntityName(entity.getName());
					Operate operate = operateLog.operate();
					operLog.setOperCode(operate.getCode());
					operLog.setOperName(operate.getName());
					
					operLog.setEntityId(id);
					operLog.setEntityTitle(title);
					operLog.setEntityJson(JSON.toJSONString(bean));
					operLog.setOperTime(new Date());
					operLogService.insertOperLog(operLog);
				}
			} else {
				log.info("the current operate is not delete, skip @Before advice");
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	/**
	 * <p>在被拦截的方法成功调用后调用执行（用于非删除类型的操作）
	 */
	@AfterReturning("pointcut(moduleLog, operateLog)")
	public void afterMethodInvokeSuccess(JoinPoint jp, ModuleLog moduleLog, OperateLog operateLog) {
		try {
			if (operateLog.operate().toString().endsWith("Delete")) {
				log.info("the current operate is delete, skip @AfterReturning advice");
			} else {
				User user = getCurrentUser();
				Object bean = jp.getArgs()[0];
				String id = getBeanId(bean, operateLog.idField());
				String title = getBeanTitle(bean, operateLog.titleFields());
				
				OperLog operLog = new OperLog();
				operLog.setId(idGenerator.nextId());
				
				operLog.setUserId(0L);
				operLog.setUsername("");
				operLog.setUserno("");
				operLog.setName("");
				operLog.setRoles("");
				
				Module module = moduleLog.module();
				operLog.setModuleCode(module.getCode());
				operLog.setModuleName(module.getName());
				Entity entity = operateLog.entity();
				operLog.setEntityCode(entity.getCode());
				operLog.setEntityName(entity.getName());
				Operate operate = operateLog.operate();
				operLog.setOperCode(operate.getCode());
				operLog.setOperName(operate.getName());
				
				operLog.setEntityId(id);
				operLog.setEntityTitle(title);
				operLog.setEntityJson(JSON.toJSONString(bean));
				operLog.setOperTime(new Date());
				operLogService.insertOperLog(operLog);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private User getCurrentUser() {
		return (User)RequestContextHolder.getRequestAttributes().getAttribute("session_user", RequestAttributes.SCOPE_SESSION);
	}
	
	@SuppressWarnings("unchecked")
	private String getRoles() {
		List<Role> roles = (List<Role>)RequestContextHolder.getRequestAttributes().getAttribute("session_roles", RequestAttributes.SCOPE_SESSION);
		if (roles.size() < 2) {
			return roles.get(0).toString();
		}
		return ",,";
	}
	
	private Object getBean(Object service, String methodName, Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		return MethodUtils.invokeMethod(service, methodName, arg);
	}
	
	private String getBeanId(Object bean, String idField) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object id = PropertyUtils.getProperty(bean, idField);
		return String.valueOf(id);
	}
	
	private String getBeanTitle(Object bean, String[] titleFields) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StringBuilder sb = new StringBuilder();
		for (String titleField : titleFields) {
			sb.append(PropertyUtils.getProperty(bean, titleField)).append("|");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
}
