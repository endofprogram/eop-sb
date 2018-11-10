package org.eop.sb.accesscontrol.service;

import org.eop.sb.accesscontrol.bean.User;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public interface UserService {

	User selectUserByUsername(String username);
	
}
