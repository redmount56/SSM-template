package com.redmount.template.base.repo;

import com.redmount.template.base.model.TestClazzInfo;
import com.redmount.template.core.Mapper;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace
public interface TestClazzInfoMapper extends Mapper<TestClazzInfo> {
}