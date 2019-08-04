package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.model.entity.OrderEntity;
import com.shengrong.chemicalsystem.service.OrderService;
import com.shengrong.chemicalsystem.utils.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class ChemicalsystemApplicationTests {

    public static void main(String[] args) throws Exception {
        String encoded = RSAUtils.encoded("admin");
        System.out.println(encoded);
//        "EAA78wDwjdH1mtuhpsxOek+23s06EaIAyEP2pSGWPGQXZd1tlZ7qnxiw3XJ6nqLmUSyMm7GCvA/mkMbJb6LFadgt/NYvJ6WV7sKGsxa+Yq8Gzc327hal+EbjXkhfSEH+96kubhm1DwAH+PtzkA7ZXUSy6RCZ4bUnQcD3EbA9PBM="
    }

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderService orderService;


    @Test
    @Transactional
    @Rollback(false)
    public void event() throws Exception{
//        TestEvent event = new TestEvent("HHHHH");
        try {
            OrderEntity entity = new OrderEntity();
            entity.setName("testname");
            entity.setOrderNo("XXXX33333");
            entity.setType("type_4");
            orderService.insert(entity);
            throw new RuntimeException();
        }catch (Exception e){
            log.error("error",e);
        }

//        SpringContextUtils.publish(event);

//        Thread.sleep(5000L);

    }

}
