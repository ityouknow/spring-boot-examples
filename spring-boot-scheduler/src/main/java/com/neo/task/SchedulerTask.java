package com.neo.task;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by summer on 2016/12/1.
 */

//@Component
public class SchedulerTask {

  private int j = 0;

  @Scheduled(fixedDelay = 6000)
  public void reportCurrentTime2() throws InterruptedException {
    System.out.println("next:" + j + "现在时间：" + LocalDateTime.now().toString());
    Thread.sleep(2 * 1000);
    System.out.println("next:" + j + "结束时间：" + LocalDateTime.now().toString());
    j++;
  }

}
