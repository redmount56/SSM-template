![Licence](https://img.shields.io/badge/licence-none-green.svg)


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

* [0. 简介](#0-简介)
* [1. 表设计规则](#1-表设计规则)
	* [1.1. 表名规则](#11-表名规则)
	* [1.2. 字段名规则](#12-字段名规则)

<!-- /code_chunk_output -->



# 0. 简介

本项目继承自 https://github.com/lihengming/spring-boot-api-project-seed

并在其基础上增加了以下功能:
1. 整合Swagger2
1. 业务异常的表级维护
1. 结合表设计规则,可以提供一对一/一对多/多对多/多对多并且带关系数据的数据关联查询
1. 可以提供一对一/一对多/多对多/多对多且带关系数据的数据保存/更新
1. 条件查询/排序

# 1. 表设计规则
> 本规范中,表分为两种,一种是实体表,一种是关系表.
> 实体表表现在最终的返回结果上,是会把主键(pk)返回的,也就是具有业务意义的表.
> 例如: 班级/学生/老师等以及各种字典表.
> 关系表的作用是记录两实体之间有关系,也可以描述两者之间是什么关系.
> 关系表中的数据的主键/外键都不会返回给调用方,但描述的关系数据会包含在子实体的relation属性中,作为对象返回.
## 1.1. 表名规则
表名全部采用小写+下划线的形式
```关系表```采用```r_表1_t_表2```的格式,其中```表1```,```表2```分别为两实体表的表名.
```实体表```不建议采用```r_foo_t_bar```的格式.
## 1.2. 字段名规则
字段名也采用小写字母+下划线的形式
```关系表```强制采用```表全名_pk```的形式.

	test_clazz_pk
# 2. 实体创建规则
建议采用```Model```作为结尾.

	ClazzModel
# 3. 最佳实践
## 3.1. 准备阶段
	1.

## 3.2. 生成阶段

## 3.3. 编制Model阶段

## 3.4. 创建通用Service.
