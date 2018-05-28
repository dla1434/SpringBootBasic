package com.basic.sb.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.basic.sb.domain.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private Map<String, UserInfo> userInfoMap;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		//user 확인
		if (!userInfoMap.containsKey(userId)) {
			throw new UsernameNotFoundException(userId);
		}
		
		if(userInfoMap.isEmpty()){
			UserInfo defaultUser = new UserInfo();
			defaultUser.setUserId("admin");
			defaultUser.setPassword("admin");
			
			userInfoMap.put(defaultUser.getUserId(), defaultUser);
		}
		
		UserInfo userInfo = userInfoMap.get(userId);
		
		log.info("user : {}", userInfo);
		
		return new User(userInfo.getUserId(), userInfo.getPassword(), AuthorityUtils.createAuthorityList("USER"));
	}

}
