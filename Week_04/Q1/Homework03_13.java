package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_13 {
	
	public static int result = 0;
	
    public static void main(String[] args) {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        
        //int result = sum(); //这是得到的返回值
        
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
        	return (result = sum());
        });
        
        //在无法预估任务耗时多长时，超时等待任务终止是不可取的，因为无法保证超时时间内任务一定能运行完成
//        try {
//			executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        //利用线程池提交任务，通过ExecutorService::isTerminated(),循环查询任务是否终止，主线程自旋等待任务结果
        executorService.shutdown();
        //循环查询任务是否终结
        while(!executorService.isTerminated()) {
        	
        }
        
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
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
