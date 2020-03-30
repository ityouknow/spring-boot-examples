package com.neo;

import com.neo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @describe：
 * @Date：2020/3/30 23:38
 * @author：wbx
 *
 * 通用 CRUD 封装BaseMapper接口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperCRUDTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void name() {


    }
}
