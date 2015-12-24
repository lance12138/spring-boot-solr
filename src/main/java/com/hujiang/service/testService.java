package com.hujiang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hujiang.mapper.testMapper;
import com.hujiang.model.Demo;

@Service
@Transactional
public class testService {

	@Autowired
	private testMapper teMapper;
	
	public Demo findById(String id){
		return teMapper.findById(id);
	}
}
