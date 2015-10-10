package com.asu.cse545.group12.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.asu.cse545.group12.dao.UserDao;
import com.asu.cse545.group12.dao.RoleDao;
import com.asu.cse545.group12.domain.Users;
import com.asu.cse545.group12.services.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SbsUserDetailsService")
@Transactional
public class SbsUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	UserService service;
	@Autowired
	MessageSource messages;
	@Autowired
	RoleDao roleDao;
	private static final Logger logger = Logger.getLogger(SbsUserDetailsService.class);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		if(logger.isDebugEnabled()){
			logger.debug("User username: "+username);

		}
		
		try {
			Users user = userDao.getUserByUserName(username);
			if (user == null) {
				return new org.springframework.security.core.userdetails.User(" ", " ", enabled, true, true, true, getAuthorities("individual"));
			}
			if(logger.isDebugEnabled()){
				logger.debug("User Enabled: "+service.isUserEnabled(user));
				logger.debug("User accountNonExpired: "+accountNonExpired);
				logger.debug("User credentialsNonExpired: "+credentialsNonExpired);
				logger.debug("User accountNonLocked: "+accountNonLocked);
				logger.debug("User authorities: "+getAuthorities(roleDao.getRowById(user.getRoleId()).getRoleDescription()));
			}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), service.isUserEnabled(user), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(roleDao.getRowById(user.getRoleId()).getRoleDescription()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	public List<String> getRoles(String role) {
		List<String> roles = new ArrayList<String>();
		if ("individual".equals(role)) {
			roles.add("ROLE_INDIVIDUAL");
		} else if ("merchant".equals(role)) {
			roles.add("ROLE_MERCHANT");
		} else if ("regular".equals(role)) {
			roles.add("ROLE_REGULAR");
		} else if ("manager".equals(role)) {
			roles.add("ROLE_MANAGER");
		} else if ("admin".equals(role)) {
			roles.add("ROLE_ADMIN");
		}
		return roles;
	}

	private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
