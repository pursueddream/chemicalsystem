package com.shengrong.chemicalsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class ChemicalsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChemicalsystemApplication.class, args);
        log.info("================================================");
        log.info("==============  START  SUCCESS  ================");
        log.info("================================================");
    }

}
