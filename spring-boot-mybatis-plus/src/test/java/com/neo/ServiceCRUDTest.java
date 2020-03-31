package com.neo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neo.model.User;
import com.neo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @describe：
 * @Date：2020/3/30 22:37
 * @author：wbx
 *
 * 通用 Service CRUD 封装IService接口， CRUD 采用: get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆，
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceCRUDTest {

    @Autowired
    private UserService userService;

    /**
     * // 链式查询 普通
     * QueryChainWrapper<T> query();
     * // 链式查询 lambda 式。注意：不支持 Kotlin
     * LambdaQueryChainWrapper<T> lambdaQuery();
     *
     * // 示例：
     * query().eq("column", value).one();
     * lambdaQuery().eq(Entity::getId, value).list();
     */
    @Test
    public void testQueryChain() {
        User one = userService.query().eq("name", "keep").one();
        System.out.println("one = " + one);


        User keep = userService.lambdaQuery().eq(User::getName, "it").eq(User::getAge, 25).one();
        System.out.println("keep = " + keep);

        List<User> list = userService.lambdaQuery().eq(User::getName, "it").list();
        System.out.println("list = " + list);

        System.out.println(userService.lambdaQuery().like(User::getName, "l").list());


    }


    @Test
    public void testSelect() {
        List<User> list = userService.list(new QueryWrapper<User>().lambda().eq(User::getName,"it").select(User::getName, User::getEmail));
        System.out.println("list = " + list);


    }



    /**
     * // 链式更改 普通
     * UpdateChainWrapper<T> update();
     * // 链式更改 lambda 式。注意：不支持 Kotlin
     * LambdaUpdateChainWrapper<T> lambdaUpdate();
     *
     * // 示例：
     * update().eq("column", value).remove();
     * lambdaUpdate().eq(Entity::getId, value).update(entity);
     */
    @Test
    public void testUpdateChain() {

        boolean remove = userService.update().eq("name", "keep").remove();
        System.out.println("remove = " + remove);
        System.out.println(userService.query().list());

        System.out.println("------------->"+userService.query().select("name").list());

        User user = new User();
        user.setName("wang");
        user.setAge(18);
        user.setEmail("123@qq.com");
        boolean update = userService.lambdaUpdate().eq(User::getId, 6).update(user);
        System.out.println("update = " + update);
        System.out.println(userService.lambdaQuery().eq(User::getId, 6).one());

    }


    /**
     * // 查询总记录数
     * int count();
     * // 根据 Wrapper 条件，查询总记录数
     * int count(Wrapper<T> queryWrapper);
     */
    @Test
    public void testCount() {
        int count = userService.count();
        System.out.println("count = " + count);


        int count1 = userService.count(new QueryWrapper<User>());
        System.out.println("count1 = " + count1);

    }


}
