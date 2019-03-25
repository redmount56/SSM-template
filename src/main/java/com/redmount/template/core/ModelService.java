package com.redmount.template.core;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.util.SortedMap;

public interface ModelService<T> {
    /**
     * 取单个实体
     *
     * @param pk        单个实体pk
     * @param relations 关系数据
     * @return 带关系数据的单个实体
     */
    T getAutomatic(String pk, String relations);

    /**
     * 给单个实体挂关系
     *
     * @param model     原始实体
     * @param relations 关系数据
     * @return
     */
    T getAutomaticWithModel(T model, String relations);

    /**
     * 取带关系的列表
     *
     * @param pageInfo  带分页的主表数据
     * @param relations 关系数据
     * @return
     */
    PageInfo listAutomaticWithRelations(PageInfo pageInfo, String relations);

    /**
     * 取符合条件的实体列表
     *
     * @param keywords  关键字
     * @param relations 关系数据
     * @param orderBy   排序
     * @return 带关系数据的排序的实体列表
     */
    PageInfo listAutomaticWithoutRelations(String keywords, String condition, String relations, String orderBy, int page, int size);

    /**
     * 自动保存
     *
     * @param model 需要保存的数据,目前的限制是只保存表现层中的两层,带关系数据的,保存关系数据.再往下就不管了.
     * @return 保存之后的结果
     */
    T saveAutomatic(T model, boolean forceSaveNull);

    /**
     * 真实删除单条数据
     *
     * @param pk
     * @return
     */
    int delAutomaticByPk(String pk);

    /**
     * 按条件删除
     *
     * @param condition
     * @return
     */
    int delByConditionAudomatic(String condition);

    /**
     * 加载一对一关系数据
     *
     * @param model 主实体对象
     * @param field 要取的属性
     * @return 增加了要取的属性的主实体对象
     */
    T loadOneToOneRelation(T model, Field field);

    /**
     * 按条件加载一对一关系
     * 主供后台使用
     *
     * @param model           主实体对象
     * @param field           要加载的属性
     * @param conditionString 子属性的条件(小驼峰字符串形式)
     * @return 按条件加载的一对多关系之后的主实体对象
     */
    T loadOneToOneRelation(T model, Field field, String conditionString);

    /**
     * 按条件加载一对一关系
     * 主供后台使用
     *
     * @param model     主实体对象
     * @param field     要加载的属性
     * @param condition 子属性的条件(小驼峰形式)
     * @return 按条件加载的一对多关系之后的主实体对象
     */
    T loadOneToOneRelation(T model, Field field, Condition condition);

    /**
     * 加载一对多关系
     *
     * @param model 主实体对象
     * @param field 要取的属性
     * @return 增加了要取的属性的主实体对象
     */
    T loadOneToManyRelation(T model, Field field);

    /**
     * 按条件加载一对多关系数据
     * 主供后台使用
     *
     * @param model           主实体对象
     * @param field           需要加载的属性
     * @param conditionString 针对子实体的条件(小驼峰字符串形式)
     * @return 增加了子实体列表的主实体对象
     */
    T loadOneToManyRelation(T model, Field field, String conditionString);

    /**
     * 按条件加载一对多关系数据
     * 主供后台使用
     *
     * @param model     主实体对象
     * @param field     需要加载的属性
     * @param condition 针对子实体的条件
     * @return 增加了子实体列表的主实体对象
     */
    T loadOneToManyRelation(T model, Field field, Condition condition);

    /**
     * 加载多对多关系
     *
     * @param model 主实体对象
     * @param field 要加载的属性
     * @return 增加了要加载的属性的主实体对象
     */
    T loadManyToManyRelation(T model, Field field);

    /**
     * 按条件加载多对多关系
     * 主供后台使用
     *
     * @param model           主实体对象
     * @param field           要加载的属性
     * @param conditionString 子属性的条件(小驼峰字符串形式)
     * @return 按条件加载的多对多关系之后的主实体对象
     */
    T loadManyToManyRelation(T model, Field field, String conditionString);

    /**
     * 按条件加载多对多关系
     * 主供后台使用
     *
     * @param model     主实体对象
     * @param field     要加载的属性
     * @param condition 子属性的条件(小驼峰形式)
     * @return 按条件加载的多对多关系之后的主实体对象
     */
    T loadManyToManyRelation(T model, Field field, Condition condition);

    /**
     * 保存实体中的一对一关系
     *
     * @param model 主实体
     * @param field 需要保存的字段
     * @return 保存后的主实体
     */
    T saveOneToOneRelation(T model, Field field);

    /**
     * 保存实体中的一对多关系(子实体中记录了主实体的pk)
     *
     * @param model 主实体
     * @param field 需要保存的字段
     * @return 保存后的主实体
     */
    T saveOneToManyRelation(T model, Field field);

    /**
     * 保存实体中的多对多关系(通过中间表连接的,并且关联数据记录在中间表的)
     *
     * @param model 主实体
     * @param field 需要保存的字段
     * @return 保存后的主实体
     */
    T saveManyToManyRelation(T model, Field field);

    /**
     * 取实体注释模型
     *
     * @return 带注释的实体模型
     */
    SortedMap getSchema();
}
