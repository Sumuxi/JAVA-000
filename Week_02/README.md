# 学习笔记

## 周四Q1

1.使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

answer:

### 1.串行GC

串行GC使用单线程做GC，fullGC暂停时间长

```powershell
java -XX:+UseSerialGC -Xms128m -Xmx128m -Xloggc:SerialGC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:SerialGC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseSerialGC -Xms1024m -Xmx1024m -Xloggc:SerialGC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseSerialGC -Xms2048m -Xmx2048m -Xloggc:SerialGC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseSerialGC -Xms4096m -Xmx4096m -Xloggc:SerialGC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在周四Q1目录下。

![1](https://gitee.com/Sumuxi/public/raw/master/images/20201115190929.png)

### 2.并行GC

```powershell
java -XX:+UseParallelGC -Xms128m -Xmx128m -Xloggc:ParallelGC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:ParallelGC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms1024m -Xmx1024m -Xloggc:ParallelGC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms2048m -Xmx2048m -Xloggc:ParallelGC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms4096m -Xmx4096m -Xloggc:ParallelGC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在周四Q1目录下。

![2](https://gitee.com/Sumuxi/public/raw/master/images/20201115190947.png)

### 3.CMS GC

```powershell
java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -Xloggc:ConcMarkSweepGC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:ConcMarkSweepGC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -Xloggc:ConcMarkSweepGC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms2048m -Xmx2048m -Xloggc:ConcMarkSweepGC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms4096m -Xmx4096m -Xloggc:ConcMarkSweepGC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在周四Q1目录下。

![3](https://gitee.com/Sumuxi/public/raw/master/images/20201115190955.png)

### 4.G1 GC

```powershell
java -XX:+UseG1GC -Xms128m -Xmx128m -Xloggc:G1GC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:G1GC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms1024m -Xmx1024m -Xloggc:G1GC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms2048m -Xmx2048m -Xloggc:G1GC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms4096m -Xmx4096m -Xloggc:G1GC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在周四Q1目录下。

![4](https://gitee.com/Sumuxi/public/raw/master/images/20201115191011.png)

汇总四种GC的不同堆内存下的运行结果如下（此结果意义不大）。

| 生成对象次数 | 128M | 512M | 1024M | 2048M |
| ------------ | ---- | ---- | ----- | ----- |
| SerialGC     | OOM  | 9626 | 13770 | 14276 |
| ParallelGC   | OOM  | 8566 | 12554 | 15493 |
| CMS GC       | OOM  | 9969 | 13696 | 14898 |
| G1 GC        | OOM  | 9547 | 12953 | 13807 |

因为测试代码是一直在分配内，需要的内存较多，所以4种GC在128M堆内存时均引发了OOM异常。对于特定的程序，堆内存设置的越小，越容易OOM。

------

## 周四Q2

2.使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

answer:

​	堆内存分别使用512M，1G，2G，4G，GC算法分别使用 Parallel GC、CMS GC 和 G1 GC。

### 512M && Parallel GC 压测

```powershell
java -jar -XX:+UseParallelGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:10:34
[Press C to stop the test]
651434  (RPS: 10244.4)
---------------Finished!----------------
Finished at 2020-11-15 22:11:38 (took 00:01:03.8008741)
Status 200:    651435

RPS: 10638.2 (requests/second)
Max: 313ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![p1](https://gitee.com/Sumuxi/public/raw/master/images/20201115230801.PNG)

![p2](https://gitee.com/Sumuxi/public/raw/master/images/20201115230808.PNG)

### 512M && CMS GC 压测

```powershell
java -jar -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:20:58
[Press C to stop the test]
652699  (RPS: 10270.3)
652692  (RPS: 10270.2)                  ---------------Finished!----------------
Finished at 2020-11-15 22:22:02 (took 00:01:03.5722406)
Status 200:    652707

RPS: 10693.6 (requests/second)
Max: 277ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![c1](https://gitee.com/Sumuxi/public/raw/master/images/20201115231103.PNG)

![c2](https://gitee.com/Sumuxi/public/raw/master/images/20201115231109.PNG)

### 512M && G1 GC 压测

```powershell
java -jar -XX:+UseG1GC -Xms512m -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:28:58
[Press C to stop the test]
597373  (RPS: 9387.2)
---------------Finished!----------------
Finished at 2020-11-15 22:30:02 (took 00:01:03.6650936)
Status 200:    597382

RPS: 9787.5 (requests/second)
Max: 306ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 2ms
99.9%   below 8ms
```

![g1](https://gitee.com/Sumuxi/public/raw/master/images/20201115231451.PNG)

![g2](https://gitee.com/Sumuxi/public/raw/master/images/20201115231455.PNG)

### 1G && ParallelGC 压测

```powershell
java -jar -XX:+UseParallelGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:38:45
[Press C to stop the test]
671689  (RPS: 10579.6)
---------------Finished!----------------
Finished at 2020-11-15 22:39:49 (took 00:01:03.5172499)
Status 200:    671692

RPS: 11000.5 (requests/second)
Max: 299ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 4ms
```

![p1](https://gitee.com/Sumuxi/public/raw/master/images/20201115231921.PNG)

![p2](https://gitee.com/Sumuxi/public/raw/master/images/20201115231938.PNG)

### 1G && CMS GC 压测

```powershell
java -jar -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:42:35
[Press C to stop the test]
667498  (RPS: 10500.5)
---------------Finished!----------------
Finished at 2020-11-15 22:43:39 (took 00:01:03.7855747)
Status 200:    667501

RPS: 10901.9 (requests/second)
Max: 180ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![c1](https://gitee.com/Sumuxi/public/raw/master/images/20201115232028.PNG)

![c2](https://gitee.com/Sumuxi/public/raw/master/images/20201115232036.PNG)

### 1G && G1 GC 压测

```powershell
java -jar -XX:+UseG1GC -Xms1g -Xmx1g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:46:11
[Press C to stop the test]
655217  (RPS: 10342)1)
---------------Finished!----------------
Finished at 2020-11-15 22:47:15 (took 00:01:03.5596361)
Status 200:    655232

RPS: 10701.8 (requests/second)
Max: 224ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![g1](https://gitee.com/Sumuxi/public/raw/master/images/20201115232120.PNG)

![g2](https://gitee.com/Sumuxi/public/raw/master/images/20201115232129.PNG)

### 2G && ParallelGC 压测

```powershell
java -jar -XX:+UseParallelGC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:50:18
[Press C to stop the test]
671761  (RPS: 10572.8)
---------------Finished!----------------
Finished at 2020-11-15 22:51:22 (took 00:01:03.7521638)
Status 200:    671762

RPS: 10971.3 (requests/second)
Max: 235ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 4ms
```

![p1](https://gitee.com/Sumuxi/public/raw/master/images/20201115232202.PNG)

![p2](https://gitee.com/Sumuxi/public/raw/master/images/20201115232210.PNG)

### 2G && CMS GC 压测

```powershell
java -jar -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:53:16
[Press C to stop the test]
671392  (RPS: 10565.1)
---------------Finished!----------------
Finished at 2020-11-15 22:54:19 (took 00:01:03.7599174)
Status 200:    671393

RPS: 10965.9 (requests/second)
Max: 174ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 4ms
```

![c1](https://gitee.com/Sumuxi/public/raw/master/images/20201115232229.PNG)

![c2](https://gitee.com/Sumuxi/public/raw/master/images/20201115232235.PNG)

### 2G && G1 GC 压测

```powershell
java -jar -XX:+UseG1GC -Xms2g -Xmx2g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 22:55:38
[Press C to stop the test]
672462  (RPS: 10603.3)
---------------Finished!----------------
Finished at 2020-11-15 22:56:42 (took 00:01:03.6260568)
Status 200:    672469

RPS: 10983 (requests/second)
Max: 227ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![g1](https://gitee.com/Sumuxi/public/raw/master/images/20201115232310.PNG)

![g2](https://gitee.com/Sumuxi/public/raw/master/images/20201115232320.PNG)

### 4G && ParallelGC 压测

```powershell
java -jar -XX:+UseParallelGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-16 00:09:39
[Press C to stop the test]
655963  (RPS: 10320.9)
---------------Finished!----------------
Finished at 2020-11-16 00:10:43 (took 00:01:03.7753033)
Status 200:    655973

RPS: 10710.1 (requests/second)
Max: 86ms
Min: 0ms
Avg: 0ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![image-20201116002000397](https://gitee.com/Sumuxi/public/raw/master/images/20201116002000.png)

![image-20201116002027079](https://gitee.com/Sumuxi/public/raw/master/images/20201116002027.png)

### 4G && CMS GC 压测

```powershell
java -jar -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 23:38:56
[Press C to stop the test]
652069  (RPS: 10260.1)
---------------Finished!----------------
Finished at 2020-11-15 23:40:00 (took 00:01:03.7687305)
Status 200:    652071

RPS: 10648.1 (requests/second)
Max: 295ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 0ms
  99%   below 2ms
99.9%   below 5ms
```

![c1](https://gitee.com/Sumuxi/public/raw/master/images/20201115234739.PNG)

![c2](https://gitee.com/Sumuxi/public/raw/master/images/20201115234744.PNG)

### 4G && G1 GC 压测

```powershell
java -jar -XX:+UseG1GC -Xms4g -Xmx4g gateway-server-0.0.1-SNAPSHOT.jar

PS C:\Users\yangguohui> sb -u http://localhost:8088/api/hello -c 24 -N 60
Starting at 2020-11-15 23:43:54
[Press C to stop the test]
619021  (RPS: 9746.7)
---------------Finished!----------------
Finished at 2020-11-15 23:44:57 (took 00:01:03.7192232)
Status 200:    619023

RPS: 10108.9 (requests/second)
Max: 241ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 2ms
99.9%   below 8ms
```

![g1](https://gitee.com/Sumuxi/public/raw/master/images/20201115234818.PNG)

![](https://gitee.com/Sumuxi/public/raw/master/images/20201115234823.PNG)

### 结果比较

| RPS(requests/second) |  512M   |   1G    |   2G    |   4G    |
| -------------------: | :-----: | :-----: | :-----: | :-----: |
|          Parallel GC | 10638.2 | 11000.5 | 10971.3 | 10710.1 |
|     ConcMarkSweep GC | 10693.6 | 10901.9 | 10965.9 | 10648.1 |
|                G1 GC | 9787.5  | 10701.8 |  10983  | 10108.9 |

|      Total Count |  512M  |   1G   |   2G   |   4G   |
| ---------------: | :----: | :----: | :----: | :----: |
|      Parallel GC | 651435 | 671692 | 671762 | 655973 |
| ConcMarkSweep GC | 652707 | 667501 | 671393 | 652071 |
|            G1 GC | 597382 | 655232 | 672469 | 619023 |

------

## 周四Q4

**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。

answer：

### GC日志信息解读：

![20190408003018473](https://gitee.com/Sumuxi/public/raw/master/images/20201117124205.png)

### GC中出现的一些概念

#### 1、并行与并发：

并行（Parallel）：指多条垃圾收集线程并行工作，但此时用户线程仍然处于等待状态。

并发（Concurrent）：指用户线程与垃圾收集线程同时执行（但不一定是并行的，可能会交替执行），用户程序在继续运行，而垃圾收集程序运行于另一个CPU上。

#### 2、minor gc和full gc：

新生代GC（Minor GC）：指发生在新生代的垃圾收集动作，因为Java对象大多都具备朝生夕灭的特性，所以Minor GC非常频繁，一般回收速度也比较快。

老年代GC（Major GC / Full GC）：指发生在老年代的GC，出现了Major GC，经常会伴随至少一次的Minor GC（但非绝对的，在Parallel Scavenge收集器的收集策略里就有直接进行Major GC的策略选择过程）。Major GC的速度一般会比Minor GC慢10倍以上。

#### 3、吞吐量：

吞吐量就是CPU用于运行用户代码的时间与CPU总消耗时间的比值，即吞吐量 = 运行用户代码时间 /（运行用户代码时间 + 垃圾收集时间）。例如虚拟机总共运行了100分钟，其中垃圾收集花掉1分钟，那吞吐量就是99%。

#### 4、JVM运行模式：

JVM如果不指定-server或-client选项，JVM会在启动的时候根据硬件环境判断以server模式启动还是以client模式启动（适用于Java 5及以上版本，Java 5检测的根据是至少2个CPU和最低2GB内存）。

JVM工作在server模式可以大大提高性能，但应用的启动会比client模式慢大概10%。

当JVM用于启动GUI界面的交互应用时适合于使用client模式，当JVM用于运行服务器后台程序时建议用server模式。

### 各种GC的简单总结

接下来我们重点分析不同gc器的特点和他们的搭配使用（并非任何一种新生代GC策略都可以和另一种年老代GC策略进行配合工作）

![20170527163516039](https://gitee.com/Sumuxi/public/raw/master/images/20201116234626.jpg)

上图展示了7种作用于不同分代的收集器，如果两个收集器之间存在连线，就说明它们可以搭配使用。GC所处的区域，则表示它是属于新生代收集器还是老年代收集器。

#### 年轻代垃圾回收：

##### 1、Serial收集器：

Serial收集器是最基本、发展历史最悠久的收集器，曾经（在JDK 1.3.1之前）是虚拟机新生代收集的唯一选择。

![20170527164146557](https://gitee.com/Sumuxi/public/raw/master/images/20201117120031.jpg)

1）特性：
这个收集器是一个单线程的收集器，但它的“单线程”的意义并不仅仅说明它只会使用一个CPU或一条收集线程去完成垃圾收集工作，更重要的是在它进行垃圾收集时，必须暂停其他所有的工作线程，直到它收集结束。Stop The World

2）应用场景：
Serial收集器是虚拟机运行在Client模式下的默认新生代收集器；也可使用-XX:+UseSerialGC指定。

3）优势：
简单而高效（与其他收集器的单线程比），对于限定单个CPU的环境来说，Serial收集器由于没有线程交互的开销，专心做垃圾收集自然可以获得最高的单线程收集效率。

##### 2、ParNew收集器：

![20170527164230315](https://gitee.com/Sumuxi/public/raw/master/images/20201117120128.jpg)

1）特性：
ParNew收集器其实就是Serial收集器的多线程版本，除了使用多条线程进行垃圾收集之外，其余行为包括Serial收集器可用的所有控制参数、收集算法、Stop The World、对象分配规则、回收策略等都与Serial收集器完全一样，在实现上，这两种收集器也共用了相当多的代码。（内存分配、回收和PS相同，不同的仅在于会配合CMS做些处理）

2）应用场景：
ParNew收集器是许多运行在Server模式下的虚拟机中首选的新生代收集器，可以和cms老年代回收期配合使用。当old代采用CMS GC时new代默认采用ParNew，也可以采用-XX:+UseParNewGC指定。

很重要的原因是：除了Serial收集器外，目前只有它能与CMS收集器配合工作。

在JDK 1.5时期，HotSpot推出了一款在强交互应用中几乎可认为有划时代意义的垃圾收集器——CMS收集器，这款收集器是HotSpot虚拟机中第一款真正意义上的并发收集器，它第一次实现了让垃圾收集线程与用户线程同时工作。
不幸的是，CMS作为老年代的收集器，却无法与JDK 1.4.0中已经存在的新生代收集器Parallel Scavenge配合工作，所以在JDK 1.5中使用CMS来收集老年代的时候，新生代只能选择ParNew或者Serial收集器中的一个

3）Serial收集器 VS ParNew收集器：

ParNew收集器在单CPU的环境中绝对不会有比Serial收集器更好的效果，甚至由于存在线程交互的开销，该收集器在通过超线程技术实现的两个CPU的环境中都不能百分之百地保证可以超越Serial收集器。
然而，随着可以使用的CPU的数量的增加，它对于GC时系统资源的有效利用还是很有好处的。

##### 3、Parallel Scavenge收集器：

1）特性：

Parallel Scavenge收集器是一个新生代收集器，它也是使用复制算法的收集器，又是并行的多线程收集器。server模式下的默认GC方式，也可用-XX:+UseParallelGC强制指定。

2）应用场景：

停顿时间越短就越适合需要与用户交互的程序，良好的响应速度能提升用户体验，而高吞吐量则可以高效率地利用CPU时间，尽快完成程序的运算任务，主要适合在后台运算而不需要太多交互的任务。

3）对比分析：

A、Parallel Scavenge收集器 VS CMS等收集器：

Parallel Scavenge收集器的特点是它的关注点与其他收集器不同，CMS等收集器的关注点是尽可能地缩短垃圾收集时用户线程的停顿时间，而Parallel Scavenge收集器的目标则是达到一个可控制的吞吐量（Throughput）。

由于与吞吐量关系密切，Parallel Scavenge收集器也经常称为“吞吐量优先”收集器。

B、Parallel Scavenge收集器 VS ParNew收集器：

Parallel Scavenge收集器与ParNew收集器的一个重要区别是它具有自适应调节策略。

GC自适应的调节策略：

Parallel Scavenge收集器有一个参数-XX:+UseAdaptiveSizePolicy。当这个参数打开之后，就不需要手工指定新生代的大小、Eden与Survivor区的比例、晋升老年代对象年龄等细节参数了，虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或者最大的吞吐量，这种调节方式称为GC自适应的调节策略（GC Ergonomics）。

#### 老年代垃圾回收器：

##### 1、Serial Old收集器：

![20170527171921171](https://gitee.com/Sumuxi/public/raw/master/images/20201117122501.jpg)

1）特性：
Serial Old是Serial收集器的老年代版本，它同样是一个单线程收集器，使用标记－整理算法。每次进行全部回收，进行Compact，非常耗费时间。

2）应用场景：

A、Client模式：client模式下的默认GC方式，可通过-XX:+UseSerialGC强制指定。

B、Server模式：在Server模式下，那么它主要还有两大用途：一种用途是在JDK 1.5以及之前的版本中与Parallel Scavenge收集器搭配使用，另一种用途就是作为CMS收集器的后备预案，在并发收集发生Concurrent Mode Failure时使用。

##### 2、Parallel Old收集器：

![20201117122614](https://gitee.com/Sumuxi/public/raw/master/images/20201117122614.jpg)

1）特性：
Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和“标记－整理”算法。

2）应用场景：
在注重吞吐量以及CPU资源敏感的场合，都可以优先考虑Parallel Scavenge加Parallel Old收集器。server模式下的默认GC方式，也可用-XX:+UseParallelGC= 强制指定；可以在选项后加等号来指定并行的线程数。

这个收集器是在JDK 1.6中才开始提供的，在此之前，新生代的Parallel Scavenge收集器一直处于比较尴尬的状态。原因是，如果新生代选择了Parallel Scavenge收集器，老年代除了Serial Old收集器外别无选择（Parallel Scavenge收集器无法与CMS收集器配合工作）。由于老年代Serial Old收集器在服务端应用性能上的“拖累”，使用了Parallel Scavenge收集器也未必能在整体应用上获得吞吐量最大化的效果，由于单线程的老年代收集中无法充分利用服务器多CPU的处理能力，在老年代很大而且硬件比较高级的环境中，这种组合的吞吐量甚至还不一定有ParNew加CMS的组合“给力”。直到Parallel Old收集器出现后，“吞吐量优先”收集器终于有了比较名副其实的应用组合。

3、CMS收集器：

1）特性：
CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。目前很大一部分的Java应用集中在互联网站或者B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。CMS收集器就非常符合这类应用的需求。可以使用-XX:+UseConcMarkSweepGC=指定使用，后边接等号指定并发线程数。

![1358504-20190510162825930-420192127](https://gitee.com/Sumuxi/public/raw/master/images/20201117123017.png)


CMS收集器是基于“标记—清除”算法实现的，它的运作过程相对于前面几种收集器来说更复杂一些，整个过程分为4个步骤：

A、初始标记（CMS initial mark）：初始标记仅仅只是标记一下GC Roots能直接关联到的对象，速度很快，需要“Stop The World”。

B、并发标记（CMS concurrent mark）：并发标记阶段就是进行GC Roots Tracing的过程。

C、重新标记（CMS remark）：重新标记阶段是为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段稍长一些，但远比并发标记的时间短，仍然需要“Stop The World”。

D、并发清除（CMS concurrent sweep）：并发清除阶段会清除对象。

由于整个过程中耗时最长的并发标记和并发清除过程收集器线程都可以与用户线程一起工作，所以，从总体上来说，CMS收集器的内存回收过程是与用户线程一起并发执行的。

2）优点：
CMS是一款优秀的收集器，它的主要优点在名字上已经体现出来了：并发收集、低停顿。

3）缺点：

A、CMS收集器对CPU资源非常敏感。其实，面向并发设计的程序都对CPU资源比较敏感。在并发阶段，它虽然不会导致用户线程停顿，但是会因为占用了一部分线程（或者说CPU资源）而导致应用程序变慢，总吞吐量会降低。

CMS默认启动的回收线程数是（CPU数量+3）/ 4，也就是当CPU在4个以上时，并发回收时垃圾收集线程不少于25%的CPU资源，并且随着CPU数量的增加而下降。但是当CPU不足4个（譬如2个）时，CMS对用户程序的影响就可能变得很大。

B、CMS收集器无法处理浮动垃圾：

CMS收集器无法处理浮动垃圾，可能出现“Concurrent Mode Failure”失败而导致另一次Full GC的产生。

由于CMS并发清理阶段用户线程还在运行着，伴随程序运行自然就还会有新的垃圾不断产生，这一部分垃圾出现在标记过程之后，CMS无法在当次收集中处理掉它们，只好留待下一次GC时再清理掉。这一部分垃圾就称为“浮动垃圾”。
也是由于在垃圾收集阶段用户线程还需要运行，那也就还需要预留有足够的内存空间给用户线程使用，因此CMS收集器不能像其他收集器那样等到老年代几乎完全被填满了再进行收集，需要预留一部分空间提供并发收集时的程序运作使用。要是CMS运行期间预留的内存无法满足程序需要，就会出现一次“Concurrent Mode Failure”失败，这时虚拟机将启动后备预案：临时启用Serial Old收集器来重新进行老年代的垃圾收集，这样停顿时间就很长了。

C、CMS收集器会产生大量空间碎片
CMS是一款基于“标记—清除”算法实现的收集器，这意味着收集结束时会有大量空间碎片产生。空间碎片过多时，将会给大对象分配带来很大麻烦，往往会出现老年代还有很大空间剩余，但是无法找到足够大的连续空间来分配当前对象，不得不提前触发一次Full GC。

四、G1收集器：

 ![1358504-20190510162846378-660822275](https://gitee.com/Sumuxi/public/raw/master/images/20201117123338.png)

1、特性：
G1（Garbage-First）是一款面向服务端应用的垃圾收集器。HotSpot开发团队赋予它的使命是未来可以替换掉JDK 1.5中发布的CMS收集器。与其他GC收集器相比，G1具备如下特点。

1）并行与并发
G1能充分利用多CPU、多核环境下的硬件优势，使用多个CPU来缩短Stop-The-World停顿的时间，部分其他收集器原本需要停顿Java线程执行的GC动作，G1收集器仍然可以通过并发的方式让Java程序继续执行。

2）分代收集
与其他收集器一样，分代概念在G1中依然得以保留。虽然G1可以不需要其他收集器配合就能独立管理整个GC堆，但它能够采用不同的方式去处理新创建的对象和已经存活了一段时间、熬过多次GC的旧对象以获取更好的收集效果。

3）空间整合
与CMS的“标记—清理”算法不同，G1从整体来看是基于“标记—整理”算法实现的收集器，从局部（两个Region之间）上来看是基于“复制”算法实现的，但无论如何，这两种算法都意味着G1运作期间不会产生内存空间碎片，收集后能提供规整的可用内存。这种特性有利于程序长时间运行，分配大对象时不会因为无法找到连续内存空间而提前触发下一次GC。

4）可预测的停顿
这是G1相对于CMS的另一大优势，降低停顿时间是G1和CMS共同的关注点，但G1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒。

在G1之前的其他收集器进行收集的范围都是整个新生代或者老年代，而G1不再是这样。使用G1收集器时，Java堆的内存布局就与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的独立区域（Region），虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔离的了，它们都是一部分Region（不需要连续）的集合。

G1收集器之所以能建立可预测的停顿时间模型，是因为它可以有计划地避免在整个Java堆中进行全区域的垃圾收集。G1跟踪各个Region里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的Region（这也就是Garbage-First名称的来由）。这种使用Region划分内存空间以及有优先级的区域回收方式，保证了G1收集器在有限的时间内可以获取尽可能高的收集效率。


2、执行过程：
G1收集器的运作大致可划分为以下几个步骤：

1）初始标记（Initial Marking）
初始标记阶段仅仅只是标记一下GC Roots能直接关联到的对象，并且修改TAMS（Next Top at Mark Start）的值，让下一阶段用户程序并发运行时，能在正确可用的Region中创建新对象，这阶段需要停顿线程，但耗时很短。

2）并发标记（Concurrent Marking）
并发标记阶段是从GC Root开始对堆中对象进行可达性分析，找出存活的对象，这阶段耗时较长，但可与用户程序并发执行。

3）最终标记（Final Marking）
最终标记阶段是为了修正在并发标记期间因用户程序继续运作而导致标记产生变动的那一部分标记记录，虚拟机将这段时间对象变化记录在线程Remembered Set Logs里面，最终标记阶段需要把Remembered Set Logs的数据合并到Remembered Set中，这阶段需要停顿线程，但是可并行执行。

4）筛选回收（Live Data Counting and Evacuation）
筛选回收阶段首先对各个Region的回收价值和成本进行排序，根据用户所期望的GC停顿时间来制定回收计划，这个阶段其实也可以做到与用户程序一起并发执行，但是因为只回收一部分Region，时间是用户可控制的，而且停顿用户线程将大幅提高收集效率。

总结：

虽然我们是在对各个收集器进行比较，但并非为了挑选出一个最好的收集器。因为直到现在为止还没有最好的收集器出现，更加没有万能的收集器，所以我们选择的只是对具体应用最合适的收集器。这点不需要多加解释就能证明：如果有一种放之四海皆准、任何场景下都适用的完美收集器存在，那HotSpot虚拟机就没必要实现那么多不同的收集器了。

比较推荐的组合方式：

1）web接口交互的系统：优先使用G1，或者使用ParNew+CMS组合，可以提高响应时间；

2）服务型的系统：使用parallel scavenge + parallel old，可以提高吞吐量；

------

## 周六Q2

**2.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 Github。

answer：在[OkHttpUtils.java](./周六Q2/OkHttpUtils.java)中

