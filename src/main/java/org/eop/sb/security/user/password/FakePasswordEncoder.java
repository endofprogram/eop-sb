package org.eop.sb.security.user.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>用于密码加密和比对
 * @author lixinjie
 * @since 2018-10-18
 */
public class FakePasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
