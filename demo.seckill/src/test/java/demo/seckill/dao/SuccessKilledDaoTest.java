package demo.seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.seckill.entity.SuccessKilled;

//配置spring和junit整合，让junit启动时加载springIoC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit读取spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	@Resource
	SuccessKilledDao dao;
	
	@Test
	public void testInsertSuccessKilled() {
		int num = dao.insertSuccessKilled(1000L, 17798550593L);
		System.out.println(num);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled successKilled = dao.queryByIdWithSeckill(1000L, 17798550593L);
		System.out.println(successKilled.toString());
	}

}
