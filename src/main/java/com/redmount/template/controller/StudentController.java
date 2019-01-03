package com.redmount.template.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.redmount.template.core.Result;
import com.redmount.template.core.ResultGenerator;
import com.redmount.template.model.ClazzModel;
import com.redmount.template.model.StudentModel;
import com.redmount.template.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping("/{pk}")
    public Result getByPk(@PathVariable(value = "pk") String pk,
                          @RequestParam(value = "relations", defaultValue = "") String relations) {
        return ResultGenerator.genSuccessResult(service.getAutomatic(pk, relations));
    }

    @GetMapping
    public Result getList(@RequestParam(value = "keywords", defaultValue = "") String keywords,
                          @RequestParam(value = "condition", defaultValue = "") String condition,
                          @RequestParam(value = "relations", defaultValue = "") String relations,
                          @RequestParam(value = "orderBy", defaultValue = "updated desc") String orderBy,
                          @RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<StudentModel> list = service.list(keywords, condition, relations, orderBy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
