package com.wf.oauth.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: wf
 * @Date: 2020-05-07 11:27
 * @describe
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(@Param("username") String username);
}
