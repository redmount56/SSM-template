package com.redmount.template.model;

import com.redmount.template.base.model.TestStudent;
import com.redmount.template.core.annotation.RelationData;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@RelationData(baseDOTypeName = "TestStudent")
@ApiModel("学生实体")
public class StudentModel extends TestStudent {
    @RelationData(baseDOTypeName = "TestClazz", foreignProperty = "clazzPk")
    ClazzModel clazz;
}
