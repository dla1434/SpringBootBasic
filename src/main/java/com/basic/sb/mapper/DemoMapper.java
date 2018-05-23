package com.basic.sb.mapper;

import org.apache.ibatis.annotations.Param;

import com.basic.sb.domain.DemoDTO;

public interface DemoMapper {
	public void set(@Param("testColumn") String testColumn) throws Exception;
	
	public DemoDTO get(@Param("testColumn") String testColumn) throws Exception;
}
