package java0.conc0303;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_02 {
	
    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        
//        int result = sum(); //这是得到的返回值
        
        //利用线程池提交任务返回的Future对象，通过Future.get()方法等待结果
        
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
        	return sum();
        });
        
		try {
			int result = future.get();
			// 确保  拿到result 并输出
	        System.out.println("异步计算结果为："+result);
	         
	        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
			
		} catch (InterruptedException | ExecutionException e) {
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
    
    
    /**
     * 	用循环的方式计算斐波那契数
     * @author 杨雄辉
     * update: 2020年11月9日 下午9:52:52
     * @param a
     * @return int
     */
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
