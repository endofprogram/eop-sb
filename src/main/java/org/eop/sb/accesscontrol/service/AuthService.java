package org.eop.sb.accesscontrol.service;

import java.util.List;

import org.eop.sb.accesscontrol.bean.Auth;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
public interface AuthService {

	int insertAuth(Auth auth);
	
	int updateAuth(Auth auth);
	
	int deleteAuth(Long id);
	
	Auth selectAuth(Long id);
	
	List<Auth> selectAllMenus();
	
	List<Auth> selectAllAuths();
	
	List<Auth> selectMenus(Long[] roleIds);
	
	List<Auth> selectAuths(Long[] roleIds);
}
