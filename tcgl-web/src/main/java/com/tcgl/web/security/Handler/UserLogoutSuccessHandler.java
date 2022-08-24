package com.tcgl.web.security.Handler;

import com.tcgl.web.security.config.JWTConfig;
import com.tcgl.web.security.utils.JWTTokenUtils;
import com.tcgl.web.security.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出成功处理类
 * 
 * @author CL
 *
 */
@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		// 添加到黑名单
		String token = request.getHeader(JWTConfig.tokenHeader);
		JWTTokenUtils.addBlackList(token);

		log.info("用户{}登出成功，Token信息已保存到Redis的黑名单中", JWTTokenUtils.getUserNameByToken(token));

		SecurityContextHolder.clearContext();
		ResponseUtils.responseJson(response, ResponseUtils.response(200, "登出成功", null));
	}
}
