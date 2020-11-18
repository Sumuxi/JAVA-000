package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 * 
 * 以下方法
 * lock signal、await
 * countdownlatch
 * cyclicbarrier
 * 
 */
public class Homework03_04 {
	
    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        
        //int result = sum(); //这是得到的返回值
        
        //利用ForkJoin框架，通过ForkJoinPool提交任务，主线程通过ForkJoinTask.get()方法等待结果
        Callable<Integer> task = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return sum();
			}
		};
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> job = pool.submit(task);
        try {
        	int result = job.get();
			// 确保  拿到result 并输出
	        System.out.println("异步计算结果为："+result);
	        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	        
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // 然后退出main线程
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    
    
    private static int fibo2(int a) {
    	if(a<=0)
    		return 0;
    	if(a == 1)
    		return 1;
    	
    	int f1 = 1;
    	int f2 = 1;
    	int i = 2;
    	while(i++ <= a) {
    		f2 = f1 + f2;
    		f1 = f2 - f1;
    	}
    	return f2;
    }
    
    private static int fibo3(int a) {
        if ( a < 2) 
            return 1;
        int a1 = 0, a2 = 1;
        for (int i = 2;i <= a;i++) {
            a2 = a1 + (a1 = a2);
        }
        return a1 + a2;
    }
    
}
