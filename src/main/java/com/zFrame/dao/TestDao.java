package com.zFrame.dao;

import java.util.List;

import com.zFrame.entity.CommonData;
import com.zFrame.entity.Student;

//@Mapper
public interface TestDao {

    // @Select("select * from student")
    List<Student> selectA();

    void insertCommonData(CommonData cd);

    CommonData selectCommonDataById(String id);

    int updateCommonDataById(CommonData commonData);

    CommonData selectCommonDataByIdPressimisticLock(String id);

}
