package com.example.manegement.service;

import com.example.manegement.entity.User;
import com.example.manegement.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int save(User user) {
        if (user.getId() == null) {  // user没有id，则表示是新增
            System.out.println(user.getName());
            return userMapper.insert(user);
        } else { // 否则为更新
            System.out.println(user.getName());
            System.out.println(user);
            return userMapper.update(user);
        }
    }

}