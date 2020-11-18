# 学习笔记

## 周四题目2

Question（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？写出你的方法，越多越好，提交到 Github。  

### Answer：

​	目前共给出14中方法，在我的电脑上均成功通过测试。

1. 利用FutureTaskde.get()方法，等待任务结束，拿到结果

2. 利用线程池提交任务返回的Future对象，通过Future.get()方法等待结果

3. 通过CompletableFuture执行异步任务，利用CompletableFuture.join()方法把任务结果传给主线程

4. 利用ForkJoin框架，通过ForkJoinPool提交任务，主线程通过ForkJoinTask.get()方法等待结果

5. 利用线程的join方法，等待任务结束，拿到结果

6. 利用锁的wait/notify机制，获得任务结果

7. 使用LockSupport提供的park()和unpark(thread)方法

8. 利用显示锁Lock的await/signal机制

9. 利用CountDownLatch实现线程协作，任务线程countDown()，主线程await等待任务线程的结果

10. 利用CyclicBarrier实现线程协作，在CyclicBarrier的回调方法中获得结果

11. 利用线程的synchronized的同步机制，通过Thread.sleep(1)触发操作系统立刻进行一次CPU调度,期望主线程让出CPU，线程T先进入同步块执行，线程T结束后主线程再进入同步块，即可拿到结果

12. 利用Semaphore的同步机制，通过Thread.sleep(1)触发操作系统立刻进行一次CPU调度,期望主线程让出CPU，线程T先进入同步块执行，线程T结束后主线程再进入同步块，即可拿到结果

13. 利用线程池提交任务，通过ExecutorService::isTerminated(),循环查询任务是否终止，主线程自旋等待任务结果

14. 给任务线程自定义完成状态，主线程自旋检测任务是否完成，任务完成后获得任务结果

    

简单分类一下：

1. 主线程通过各种Future接口的get()或join()方法等待子线程结果，1~4
2. 利用锁的同步机制，例如线程的join()方法，锁对象的wait/notify机制，显示锁Lock的await/signal机制,LockSupport的park()和unpark(thread)方法，5~8
3. AQS提供的CountDownLatch、CyclicBarrier 等机制，9~10
4. 利用同步块的串行执行原理，设法切换CPU调度，让子线程先进入同步块，主线程后进入同步块，11~12
5. 主线程自旋判断子线程的完成状态，13~14

## 周六题目4

4.（必做）把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github上 

answer：

![多线程与并发](https://gitee.com/Sumuxi/public/raw/master/images/20201118150115.png)