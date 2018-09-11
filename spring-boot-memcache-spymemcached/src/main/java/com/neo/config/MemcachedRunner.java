package com.neo.config;

import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;

@Component
public class MemcachedRunner implements CommandLineRunner {
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Resource
    private  MemcacheSource memcacheSource;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            client = new MemcachedClient(new InetSocketAddress(memcacheSource.getIp(),memcacheSource.getPort()));
        } catch (IOException e) {
            logger.error("inint MemcachedClient failed ",e);
        }
    }

    public MemcachedClient getClient() {
        return client;
    }

}