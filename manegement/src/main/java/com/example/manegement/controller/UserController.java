package com.example.manegement.controller;

import com.example.manegement.entity.User;
import com.example.manegement.mapper.UserMapper;
import com.example.manegement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    // 新增和修改
    @PostMapping("/add")
    public Integer save(@RequestBody User user) {
        // 新增或者更新
        return userService.save(user);
    }

    @GetMapping("/del/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }
    // 查询所有数据
    @GetMapping
    public List<User> index() {
        List<User> all = userMapper.findAll();
        return all;
    }

    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
//    limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name) {
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<User> data = userMapper.selectPage(pageNum, pageSize,name);
        Integer total = userMapper.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}