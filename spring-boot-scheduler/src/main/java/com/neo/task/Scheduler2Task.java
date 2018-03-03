package com.neo.task;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lizhipeng
 * @date 2017-12-28 09:08:09
 */
@Component
public class Scheduler2Task {

  private int i = 0;
  private int j = 0;

  @Scheduled(fixedRate = 3000)
  public void reportCurrentTime() throws InterruptedException {
    System.out.println(i + "现在时间：" + LocalDateTime.now().toString() + " Thread:"
        + Thread.currentThread().getName());
    Thread.sleep(6 * 1000);
    System.out.println(i + "结束时间：" + LocalDateTime.now().toString());
    i++;
  }

  @Scheduled(fixedDelay = 6000)
  public void reportCurrentTime2() throws InterruptedException {
    System.out.println("next:" + j + "现在时间：" + LocalDateTime.now().toString() + " Thread:"
        + Thread.currentThread().getName());
    Thread.sleep(2 * 1000);
    System.out.println("next:" + j + "结束时间：" + LocalDateTime.now().toString());
    j++;
  }


}
