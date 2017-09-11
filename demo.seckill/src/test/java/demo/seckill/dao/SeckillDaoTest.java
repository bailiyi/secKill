package demo.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.seckill.entity.Seckill;

//配置spring和junit整合，让junit启动时加载springIoC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit读取spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	//通过注解自动注入依赖
	@Resource
	private SeckillDao dao;

	@Test
	public void testReduceNumber() {
		int num = dao.reduceNumber(1000L, new Date());
		System.out.println(num);
	}

	@Test
	public void testQueryById() {
		long id = 1000L;
		Seckill seckill = dao.queryById(id);
		System.out.println(seckill.getName());
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills = dao.queryAll(0, 100);
		for (Seckill seckill : seckills) {
			System.out.println(seckill.toString());
		}
	}

}
