package com.example.manegement.mapper;


import com.example.manegement.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from list where del_flg=0")
    List<User> findAll();

    @Insert("INSERT into list(name, Birth,email,tel,address,ocupation) VALUES (#{name}, #{Birth}," +
            "#{email}, #{tel},#{address}, #{ocupation})")
    int insert(User user);

    @Update("update list set name = #{name}, email=#{email},tel=#{tel},address=#{address},ocupation=#{ocupation} where id = #{id}")
    int update(User user);

    @Delete("update list set del_flg=1 where id = #{id}")
    Integer deleteById(Integer id);

    @Select("select * from list where name like #{name} and del_flg=0 limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize ,String name);

    @Select("select count(*) from list where name like concat('%', #{name}, '%') and del_flg=0")
    Integer selectTotal(String name);
}