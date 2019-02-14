package com.redmount.template.base.model;

import com.redmount.template.core.BaseDO;
import com.redmount.template.core.annotation.RelationData;
import com.redmount.template.core.annotation.Tombstoned;
import com.redmount.template.core.annotation.Validate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @author Mybatis Generator
 */
@Table(name = "test_clazz_info")
@ApiModel("TestClazzInfo（）")
@Data
@Accessors(chain = true)
@Tombstoned
@RelationData(baseDOTypeName = "TestClazzInfo")
public class TestClazzInfo extends BaseDO implements Serializable {
    @ApiModelProperty(value = "")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String detail;

    @Column(name = "clazz_pk")
    @ApiModelProperty(value = "")
    @ColumnType(jdbcType = JdbcType.CHAR)
    private String clazzPk;

    @ApiModelProperty(value = "")
    @ColumnType(jdbcType = JdbcType.BIT)
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public enum FieldEnum {
        PK("pk","pk"),
		DETAIL("detail","detail"),
		CLAZZ_PK("clazzPk","clazz_pk"),
		CREATED("created","created"),
		UPDATED("updated","updated"),
		DELETED("deleted","deleted");

        private String javaFieldName;

        private String dbFieldName;

        FieldEnum(String javaFieldName, String dbFieldName) {
            this.javaFieldName = javaFieldName;
            this.dbFieldName = dbFieldName;
        }

        public String javaFieldName() {
            return javaFieldName;
        }

        public String dbFieldName() {
            return dbFieldName;
        }
    }
}