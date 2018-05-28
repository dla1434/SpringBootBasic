package com.basic.sb;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.basic.sb.domain.UserInfo;

@SpringBootApplication
@MapperScan(value={"com.basic.sb.mapper"})
public class SpringBootTestApplication {
	@Value("${login.user.list}")
	private String userLoginInfo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		
		return sqlSessionFactoryBean.getObject();
	}
	
//	@Bean
//	public Map<String, UserInfo> getLoginUserInfoMap() {
//		
//		String[] userInfoArr = userLoginInfo.split(",");
//		
//		Map<String, UserInfo> userInfoMap = new HashMap<String, UserInfo>();
//		
//		for (String userStr : userInfoArr) {
//			String[] userStrArr = userStr.split(":");
//			String userId = userStrArr[0];
//			String userPassword = userStrArr[1];
//
//			UserInfo userInfo = new UserInfo();
//			userInfo.setUserId(userId);
//			userInfo.setPassword(userPassword);
//			
//			userInfoMap.put(userId, userInfo);
//		}
//		
//		return userInfoMap;
//	}
}
