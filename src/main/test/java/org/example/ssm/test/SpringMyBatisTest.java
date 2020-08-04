package org.example.ssm.test;

import org.example.ssm.pojo.Item;
import org.example.ssm.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class SpringMyBatisTest {
    @Autowired
    private ItemService itemService;
    @Test
    public void test(){
        Item item = itemService.findById(2);
        System.out.println(item.toString());
    }
}
