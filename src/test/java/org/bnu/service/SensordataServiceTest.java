package org.bnu.service;

import org.bnu.model.Sensordata;
import org.bnu.service.SensordataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xie on 14-12-9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hibernate.xml","classpath:spring.xml"})
public class SensordataServiceTest {
    @Autowired
    private SensordataService sensordataService;

    @Test
    @Transactional
    @Rollback(true)
    public void testSave(){
        Sensordata sensordata=new Sensordata();
        sensordata.setAccX(1.1);
        sensordata.setAccY(1.2);
        sensordata.setAccZ(1.3);
        sensordataService.save(sensordata);
    }

    @Test
    public void testFindSummary(){
        List<Sensordata> list=sensordataService.findSummary();
        for(Sensordata sensordata:list){
            System.out.println(sensordata.getUuid().toString());
        }
    }
}
