package com.hujiang.mapper;

import org.springframework.stereotype.Component;
import com.hujiang.model.Demo;
@Component
public interface testMapper {

	public Demo findById(String id);
}
