package com.redmount.template.base.model;

import com.redmount.template.core.BaseDO;
import com.redmount.template.core.annotation.RelationData;
import com.redmount.template.core.annotation.Validate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Mybatis Generator
 */
@Table(name = "r_test_teacher_t_test_clazz")
@ApiModel("RTestTeacherTTestClazz（）")
@Data
@Accessors(chain = true)
@RelationData(baseDOTypeName = "RTestTeacherTTestClazz")
public class RTestTeacherTTestClazz extends BaseDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "")
    @Validate(nullable = false, stringMaxLength = 36)
    private String pk;

    @Column(name = "teacher_pk")
    @ApiModelProperty(value = "")
    private String teacherPk;

    @Column(name = "clazz_pk")
    @ApiModelProperty(value = "")
    private String clazzPk;

    @ApiModelProperty(value = "")
    private String course;

    @ApiModelProperty(value = "")
    private Integer count;

    @ApiModelProperty(value = "")
    private Date created;

    @ApiModelProperty(value = "")
    private Date updated;

    private static final long serialVersionUID = 1L;
}