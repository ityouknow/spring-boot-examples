package com.abc.controller;

import com.abc.data.Person;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author fangpc
 */

@RestController
@RequestMapping(value = "/sofatracer")
public class SampleRestController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/springmvc")
    public Map<String, Object> springmvc(@RequestParam(value = "name", defaultValue = "SampleRestController ") String name) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", true);
        resultMap.put("id", counter.incrementAndGet());
        resultMap.put("content", name);

        return resultMap;
    }

    @RequestMapping("/create")
    public Map<String, Object> create() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Person person = new Person();
        person.setName("fangpc");
        person.setAge(24);

        String personString = JSON.toJSONString(person);
        resultMap.put("person", personString);

        return resultMap;
    }
}
