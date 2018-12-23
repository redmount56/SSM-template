package com.redmount.template.base.dao.impl;

import com.redmount.template.base.repo.TestTeacherMapper;
import com.redmount.template.base.model.TestTeacher;
import com.redmount.template.base.dao.TestTeacherBaseDao;
import com.redmount.template.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/24.
 * @author CodeGenerator
 * @date 2018/12/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestTeacherBaseDaoImpl extends AbstractService<TestTeacher> implements TestTeacherBaseDao {
    @Resource
    private TestTeacherMapper testTeacherMapper;

}
