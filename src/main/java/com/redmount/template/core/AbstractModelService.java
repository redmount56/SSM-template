package com.redmount.template.core;

import com.google.common.base.CaseFormat;
import com.redmount.template.util.ReflectUtil;
import com.redmount.template.util.StringUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractModelService<T, TBase> implements ModelService<T, TBase> {

    @Autowired
    private Mapper<TBase> mapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    private String modelClassShortName;

    protected AbstractModelService(Class<T> tc,Class<TBase> tbc){

    }

    public AbstractModelService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
        modelClassShortName = modelClass.getTypeName().split("\\.")[modelClass.getTypeName().split("\\.").length - 1];
    }

    /**
     * 取单个实体
     *
     * @param pk        单个实体pk
     * @param relations 关系数据
     * @return 带关系数据的单个实体
     */
    @Override
    public T getByPk(String pk, String relations) {
        TBase baseResult = mapper.selectByPrimaryKey(pk);
        T model = ReflectUtil.cloneObj(baseResult, modelClass);
        List<String> relationList = ReflectUtil.getFieldList(modelClass, relations);
        Field field;
        String className;
        String methodName;
        String[] fullClassNamePath;
        Mapper mapper;
        Object result;
        String mainPk;
        String sqlFieldName;
        for (String relation : relationList) {
            try {
                field = modelClass.getDeclaredField(relation);
                fullClassNamePath = field.getGenericType().getTypeName().split("\\.|<|>");
                className = fullClassNamePath[fullClassNamePath.length - 1];

                if (field.getGenericType().getTypeName().startsWith("java.util.List")) {
                    // 是数组
                    // 先判断子表中是否有关联到主表的外键
                    // 取子表类型

                    mapper = (Mapper) sqlSession.getMapper(Class.forName(ProjectConstant.MAPPER_PACKAGE + "." + className + "Mapper"));
                    fullClassNamePath = field.getGenericType().getTypeName().split("<|>");
                    className = fullClassNamePath[1];
                    mainPk = ReflectUtil.getFieldValue(model, "pk").toString();
                    if (ReflectUtil.containsProperty(Class.forName(className), modelClassShortName.replaceAll("Model", "") + "Pk")) {
                        // 如果有外键,则load进来
                        System.out.println("找到单个对应属性:" + relation + "Pk");
                        sqlFieldName = modelClassShortName.replaceAll("Model", "") + "Pk";
                        sqlFieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sqlFieldName);
                        Condition condition = new Condition(Class.forName(className));
                        condition.createCriteria().andCondition(sqlFieldName + "='" + mainPk + "'");
                        result = mapper.selectByCondition(condition);
                        ReflectUtil.setFieldValue(model, relation, result);
                    } else {
                        className = className.split("\\.")[className.split("\\.").length - 1];
                        String relationClassName = "R" + modelClassShortName.replace("Model", "") + "T" + className;
                        try {
                            mapper = (Mapper) sqlSession.getMapper(Class.forName(ProjectConstant.MAPPER_PACKAGE + "." + relationClassName + "Mapper"));
                        } catch (ClassNotFoundException ex) {
                            relationClassName = "R" + className + "T" + modelClassShortName.replace("Model", "");
                            mapper = (Mapper) sqlSession.getMapper(Class.forName(ProjectConstant.MAPPER_PACKAGE + "." + relationClassName + "Mapper"));
                        }

                        sqlFieldName = modelClassShortName.replaceAll("Model", "") + "Pk";
                        sqlFieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sqlFieldName);
                        Condition condition = new Condition(Class.forName(ProjectConstant.MODEL_PACKAGE + "." + relationClassName));
                        condition.createCriteria().andCondition(sqlFieldName + "='" + mainPk + "'");
                        result = (List) mapper.selectByCondition(condition);
                        String targetSqlFieldName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, className);
                        String strIn = "(";
                        for (int i = 0; i < ((List) result).size(); i++) {
                            strIn += "'" + ReflectUtil.getFieldValue(((List) result).get(i), className.substring(0, 1).toLowerCase() + className.substring(1) + "Pk") + "'";
                            if (i < ((List) result).size() - 1) {
                                strIn += ",";
                            }
                        }
                        strIn += ")";
                        mapper = (Mapper) sqlSession.getMapper(Class.forName(ProjectConstant.MAPPER_PACKAGE + "." + className + "Mapper"));
                        condition = new Condition(Class.forName(ProjectConstant.MODEL_PACKAGE + "." + className));
                        condition.createCriteria().andCondition("pk in " + strIn);
                        result = mapper.selectByCondition(condition);
                        ReflectUtil.setFieldValue(model, relation, result);
                    }

                    // 如果没有外键,则查找关系表


                } else {
                    mapper = (Mapper) sqlSession.getMapper(Class.forName(ProjectConstant.MAPPER_PACKAGE + "." + className + "Mapper"));
                    result = mapper.selectByPrimaryKey(ReflectUtil.getFieldValue(model, relation + "Pk"));
                    ReflectUtil.setFieldValue(model, relation, result);
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return model;
    }

    /**
     * 取符合条件的实体列表
     *
     * @param keywords  关键字
     * @param relations 关系数据
     * @param orderBy   排序
     * @return 带关系数据的排序的实体列表
     */
    @Override
    public List<T> list(String keywords, String relations, String orderBy) {
        return null;
    }
}
