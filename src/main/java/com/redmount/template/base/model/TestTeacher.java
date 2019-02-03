package com.redmount.template.base.model;

import com.redmount.template.core.BaseDOTombstoned;
import com.redmount.template.core.annotation.RelationData;
import com.redmount.template.core.annotation.Tombstoned;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mybatis Generator
 */
@Table(name = "test_teacher")
@ApiModel("TestTeacher（）")
@Data
@Accessors(chain = true)
@Tombstoned
@RelationData(baseDOTypeName = "TestTeacher")
public class TestTeacher extends BaseDOTombstoned implements Serializable {
    /**
     * 教师名称
     */
    @ApiModelProperty(value = "教师名称")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String name;

    private static final long serialVersionUID = 1L;

    public enum FieldEnum {
        PK("pk","pk"),
		NAME("name","name"),
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
