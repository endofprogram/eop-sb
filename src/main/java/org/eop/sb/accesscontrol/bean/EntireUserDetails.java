package org.eop.sb.accesscontrol.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eop.common.account.AccountStatus;
import org.eop.common.password.PasswordStatus;
import org.eop.sb.security.access.authority.RoleGrantedAuthority;
import org.eop.sb.security.access.authority.UriGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lixinjie
 * @since 2018-10-23
 */
public class EntireUserDetails implements UserDetails {

	private static final long serialVersionUID = 850473871933079622L;

	private Long id;
	private String userno;
	private String name;
	private String username;
	private String password;
	private AccountStatus accountStatus;
	private PasswordStatus passwordStatus;
	private List<Role> directRoles;
	private List<Role> reachRoles;
	private List<Auth> auths;
	private Set<GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities != null) {
			return authorities;
		}
		
		authorities = new HashSet<>(directRoles.size() + reachRoles.size() + auths.size());
		for (Role role : directRoles) {
			authorities.add(new RoleGrantedAuthority(role.toString()));
		}
		for (Role role : reachRoles) {
			authorities.add(new RoleGrantedAuthority(role.toString()));
		}
		for (Auth auth : auths) {
			authorities.add(new UriGrantedAuthority(auth.toString()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountStatus != AccountStatus.Expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountStatus != AccountStatus.Locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return passwordStatus != PasswordStatus.Expired;
	}

	@Override
	public boolean isEnabled() {
		return accountStatus != AccountStatus.Disabled;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = AccountStatus.valueOf(accountStatus);
	}

	public PasswordStatus getPasswordStatus() {
		return passwordStatus;
	}

	public void setPasswordStatus(Integer passwordStatus) {
		this.passwordStatus = PasswordStatus.valueOf(passwordStatus);
	}

	public List<Role> getDirectRoles() {
		return directRoles;
	}

	public void setDirectRoles(List<Role> directRoles) {
		this.directRoles = directRoles;
	}

	public List<Role> getReachRoles() {
		return reachRoles;
	}

	public void setReachRoles(List<Role> reachRoles) {
		this.reachRoles = reachRoles;
	}

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
