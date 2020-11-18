package java0.conc0303;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池， 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_09 {
	
	static class MyThread extends Thread {
		public int myResult;
		
		private CountDownLatch latch;
		private Method method;

		public MyThread(Method method, CountDownLatch latch) {
			this.method = method;
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				myResult = (int) method.invoke(null);
				latch.countDown();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		// 在这里创建一个线程或线程池，
		// 异步执行 下面方法

//        int result = sum(); //这是得到的返回值

		try {
			final Method method = Homework03_09.class.getDeclaredMethod("sum");
			method.setAccessible(true);
			
			CountDownLatch latch = new CountDownLatch(1);
			
			MyThread t = new MyThread(method, latch);
			t.start();
			
			
			//利用CountDownLatch实现线程协作，任务线程countDown()，主线程await等待任务线程的结果
			//主线程await
			latch.await();
			
			int result = t.myResult;

			// 确保 拿到result 并输出
			System.out.println("异步计算结果为：" + result);

			System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
			// 然后退出main线程

		} catch (InterruptedException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} 
	}

	private static int sum() {
		return fibo(36);
	}

	private static int fibo(int a) {
		if (a < 2)
			return 1;
		return fibo(a - 1) + fibo(a - 2);
	}

	/**
	 * 用循环的方式计算斐波那契数
	 * @author 杨雄辉
	 * update: 2020年11月9日 下午9:46:54
	 * @param a
	 * @return int
	 */
	private static int fibo3(int a) {
		if (a < 2)
			return 1;
		int a1 = 0, a2 = 1;
		for (int i = 2; i <= a; i++) {
			a2 = a1 + (a1 = a2);
		}
		return a1 + a2;
	}

}
