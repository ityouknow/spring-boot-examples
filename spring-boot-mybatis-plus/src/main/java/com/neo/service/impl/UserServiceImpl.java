package com.neo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @Date：2020/3/30 22:21
 * @author：wbx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
