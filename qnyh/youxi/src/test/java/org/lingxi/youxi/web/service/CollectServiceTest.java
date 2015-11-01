/**
 * 
 */
package org.lingxi.youxi.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lingxi.youxi.web.model.GangRegDTO;
import org.lingxi.youxi.web.model.MemberRegDTO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Administrator
 * 2015年10月31日
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class CollectServiceTest {

	@Resource  
	private CollectService userService;  
	/**
	 * Test method for {@link org.lingxi.youxi.web.service.CollectService#getGang(java.lang.Integer)}.
	 */
	@Test
	public void testGetGang() {
		Short arg = 1;
		GangRegDTO gang = userService.getGang(arg);
		System.out.println(gang.getName());
		System.out.println(gang.getLevel());
	}
	@Test
	public void testInsertGang() {
		GangRegDTO gang = new GangRegDTO();
		gang.setName("哈哈sere");
		userService.insertGang(gang);
		System.out.println(gang.getName());
		System.out.println(gang.getId());
	}
	

}
