package demo.seckill.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.seckill.dto.Exposer;
import demo.seckill.dto.SeckillExecution;
import demo.seckill.entity.Seckill;
import demo.seckill.exception.RepeatKillException;
import demo.seckill.exception.SeckillCloseException;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                      "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		 List<Seckill> seckills=seckillService.getSeckillList();
	     logger.info("list={}", seckills);
	}

	@Test
	public void testGetById() {
		long seckillId=1000;
        Seckill seckill=seckillService.getById(seckillId);
        logger.info("seckill={}", seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		long seckillId=1000;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed())
        {

            System.out.println(exposer);
            logger.info("exposer={}",exposer);

            long userPhone=13476191876L;
            String md5=exposer.getMd5();

            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                logger.info("seckillExecution={}",seckillExecution);
            }catch (RepeatKillException e)
            {
                logger.error(e.getMessage());
            }catch (SeckillCloseException e1)
            {
            	logger.error(e1.getMessage());
            }
        }else {
            //秒杀未开启
            logger.info("exposer={}",exposer);
        }
	}

	@Test
	public void testExecuteSeckill() {
		long seckillId=1000;
		long phone = 13476191876L;
		String md5="bf204e2683e7452aa7db1a50b5713bae";
		SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
		logger.info("execution={}",execution);
	}

}
