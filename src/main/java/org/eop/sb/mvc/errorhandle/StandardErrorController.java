package org.eop.sb.mvc.errorhandle;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lixinjie
 * @since 2018-09-05
 */
@Controller
public class StandardErrorController extends BasicErrorController {

	private ErrorAttributes errorAttributes;
	private ServerProperties serverProperties;
	
	public StandardErrorController(ErrorAttributes errorAttributes,
			ServerProperties serverProperties,
			ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
		super(errorAttributes, serverProperties.getError(),
				errorViewResolversProvider.getIfAvailable());
		this.errorAttributes = errorAttributes;
		this.serverProperties = serverProperties;
	}

	@Override
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = super.errorHtml(request, response);
		System.out.println(getError(request));
		System.out.println(modelAndView.getModel());
		return modelAndView;
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		return super.error(request);
	}
	
	protected Throwable getError(HttpServletRequest request) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getError(requestAttributes);
	}

	protected ErrorAttributes getErrorAttributes() {
		return errorAttributes;
	}

	protected ServerProperties getServerProperties() {
		return serverProperties;
	}
	
}
