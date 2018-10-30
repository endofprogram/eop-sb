package org.eop.sb.security.logout.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eop.common.jackson.Jackson;
import org.eop.common.result.RestResult;
import org.eop.common.result.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author lixinjie
 * @since 2018-10-18
 */
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		writeJsonToResponse(response);
	}

	/**
	 * write logout success json to client
	 */
	protected void writeJsonToResponse(HttpServletResponse response) throws IOException {
		RestResult result = new RestResult(ResultCode.Success.getCode(), ResultCode.Success.getDesc());
		String json = Jackson.getObjectMapper().writeValueAsString(result);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(json);
	}
}
