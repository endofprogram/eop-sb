package org.eop.sb.security.access.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eop.common.jackson.Jackson;
import org.eop.common.result.RestResult;
import org.eop.common.result.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author lixinjie
 * @since 2018-10-18
 */
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		writeJsonToResponse(response);
	}

	/**
	 * write access denied json to client
	 */
	protected void writeJsonToResponse(HttpServletResponse response) throws IOException {
		RestResult result = new RestResult(ResultCode.Denial.getCode(), ResultCode.Denial.getDesc());
		String json = Jackson.getObjectMapper().writeValueAsString(result);
		response.getWriter().write(json);
	}
}
