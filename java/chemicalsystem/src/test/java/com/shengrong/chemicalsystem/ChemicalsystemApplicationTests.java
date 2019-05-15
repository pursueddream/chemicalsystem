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
        String encoded = RSAUtils.encoded("GGGGGG");
        System.out.println(encoded);

        String encoded1 = RSAUtils.decrypt(encoded);
        System.out.println(encoded1);
    }

    @Test
    public void contextLoads() {
    }

}
