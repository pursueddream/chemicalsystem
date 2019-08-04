package com.shengrong.chemicalsystem.listen;


import com.shengrong.chemicalsystem.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListenerCC implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("监听一 >>>监听的内容为 " + event.getSource());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("监听一 >>> 时间处理结束");
    }
}
