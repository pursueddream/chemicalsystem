package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.utils.RSAUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChemicalsystemApplicationTests {

    public static void main(String[] args) throws Exception {
        String encoded = RSAUtils.encoded("admin");
        System.out.println(encoded);
//        "EAA78wDwjdH1mtuhpsxOek+23s06EaIAyEP2pSGWPGQXZd1tlZ7qnxiw3XJ6nqLmUSyMm7GCvA/mkMbJb6LFadgt/NYvJ6WV7sKGsxa+Yq8Gzc327hal+EbjXkhfSEH+96kubhm1DwAH+PtzkA7ZXUSy6RCZ4bUnQcD3EbA9PBM="
    }

    @Test
    public void contextLoads() {
    }

}
