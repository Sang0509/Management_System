package com.example.manegement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT count(*) from login where username = #{username} and password =#{password}")
    String login(String username,String password);
}
