package com.neo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.neo.entity.User;
import com.neo.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testBaseQuery() throws Exception {
		//User user=new User();
		userRepository.findAll();
		userRepository.findOne(1l);
		//userRepository.save(user);
		//userRepository.delete(user);
		userRepository.count();
		userRepository.exists(1l);
	}
	
	@Test
	public void testPageQuery() throws Exception {
		int page=1,size=10;
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size, sort);
	    Page<User> p = userRepository.findALL(pageable);
	    userRepository.findByUserName("testName", pageable);
	}

}