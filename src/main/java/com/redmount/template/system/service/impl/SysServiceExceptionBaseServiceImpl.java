package com.redmount.template.system.service.impl;

import com.redmount.template.core.AbstractBaseService;
import com.redmount.template.system.repo.SysServiceExceptionMapper;
import com.redmount.template.system.model.SysServiceException;
import com.redmount.template.system.service.SysServiceExceptionBaseBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 * @author CodeGenerator
 * @date 2018/11/13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysServiceExceptionBaseServiceImpl extends AbstractBaseService<SysServiceException> implements SysServiceExceptionBaseBaseService {
    @Resource
    private SysServiceExceptionMapper sysServiceExceptionMapper;

}
