package com.cgg.pub.common.base.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Description:
 * 测试基类
 * BaseTestTemplate.java 
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BaseTestTemplate {
	private final Log log = LogFactory.getLog(BaseTestTemplate.class);

	
	@Before
	public void setUp() throws Exception {
		log.debug("测试开始~");
	}

	@After
	public void tearDown() throws Exception {
		log.debug("测试结束~");
	}

	@BeforeTransaction
	public void beforeTransaction() {
		log.debug("事务开始~");
	}

	@AfterTransaction
	public void afterTransaction() {
		log.debug("事务结束~");
	}
}
