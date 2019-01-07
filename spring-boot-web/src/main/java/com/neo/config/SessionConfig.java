package com.neo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * 分布式系统中，sessiong共享有很多的解决方案，其中托管到缓存中应该是最常用的方案之一
 * @author MeSweet
 * maxInactiveIntervalInSeconds: 设置Session失效时间
 * 使用Redis Session之后，原Boot的server.session.timeout属性不再生效
 * 如何在两台或者多台中共享session
 * 其实就是按照上面的步骤在另一个项目中再次配置一次，启动后自动就进行了session共享
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
	
}