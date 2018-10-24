package org.eop.sb.accesscontrol.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eop.sb.accesscontrol.bean.Auth;
import org.eop.sb.accesscontrol.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author lixinjie
 * @since 2018-10-21
 */
@Service
public class AuthServiceImpl implements AuthService {

	private List<Auth> buildTree(List<Auth> flatAuths) {
		List<Auth> levelOneAuths = new ArrayList<>();
		Map<Long, Auth> authMaps = new HashMap<>(flatAuths.size());
		for (Auth auth : flatAuths) {
			if (auth.getAuthLevel() == 1) {
				levelOneAuths.add(auth);
				authMaps.put(auth.getId(), auth);
			} else {
				authMaps.get(auth.getParentId()).addChild(auth);
			}
		}
		return levelOneAuths;
	}
}
