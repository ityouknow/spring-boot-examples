package com.neo.repository;

import com.neo.domain.User;
import com.neo.repository.test1.UserTest1Repository;
import com.neo.repository.test2.UserTest2Repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	@Resource
    private UserTest1Repository userTest1Repository;
	@Resource
	private UserTest2Repository userTest2Repository;

	@Test
	@Transactional
	public void testSave() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		userTest1Repository.save(new User("aa", "aa123456","aa@126.com", "aa",  formattedDate));
		userTest1Repository.save(new User("bb", "bb123456","bb@126.com", "bb",  formattedDate));
		userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));

	}


	@Test
	public void testBaseQuery() {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);
		User user=new User("ff", "ff123456","ff@126.com", "ff",  formattedDate);
		userTest1Repository.findAll();
		userTest2Repository.findOne(3l);
		userTest2Repository.save(user);
		user.setId(2l);
		userTest1Repository.delete(user);
		userTest1Repository.count();
		userTest2Repository.exists(3l);
	}


}