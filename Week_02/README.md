# 学习笔记

## Q1

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

运行截图如下，生成的日志文件在Q1目录下。

![1](https://gitee.com/Sumuxi/public/raw/master/images/20201115190929.png)

### 2.并行GC

```powershell
java -XX:+UseParallelGC -Xms128m -Xmx128m -Xloggc:ParallelGC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:ParallelGC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms1024m -Xmx1024m -Xloggc:ParallelGC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms2048m -Xmx2048m -Xloggc:ParallelGC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms4096m -Xmx4096m -Xloggc:ParallelGC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在Q1目录下。

![2](https://gitee.com/Sumuxi/public/raw/master/images/20201115190947.png)

### 3.CMS GC

```powershell
java -XX:+UseConcMarkSweepGC -Xms128m -Xmx128m -Xloggc:ConcMarkSweepGC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:ConcMarkSweepGC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms1024m -Xmx1024m -Xloggc:ConcMarkSweepGC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms2048m -Xmx2048m -Xloggc:ConcMarkSweepGC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms4096m -Xmx4096m -Xloggc:ConcMarkSweepGC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在Q1目录下。

![3](https://gitee.com/Sumuxi/public/raw/master/images/20201115190955.png)

### 4.G1 GC

```powershell
java -XX:+UseG1GC -Xms128m -Xmx128m -Xloggc:G1GC.Xmx128m.OOM.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:G1GC.Xmx512m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms1024m -Xmx1024m -Xloggc:G1GC.Xmx1024m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms2048m -Xmx2048m -Xloggc:G1GC.Xmx2048m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms4096m -Xmx4096m -Xloggc:G1GC.Xmx4096m.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行截图如下，生成的日志文件在Q1目录下。

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

## Q2

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

## Q4

4.根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。

answer：