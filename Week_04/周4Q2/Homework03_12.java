package java0.conc0303;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池， 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_12 {

	static class MyThread extends Thread {
		public int myResult;
		
		private Semaphore semaphore;
		private Method method;
		

		public MyThread(Method method, Semaphore semaphore) {
			this.method = method;
			this.semaphore = semaphore;
		}

		@Override
		public void run() {
			try {
				semaphore.acquireUninterruptibly();
				myResult = (int) method.invoke(null);
				semaphore.release();
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
			Semaphore semaphore = new Semaphore(1);
			
			final Method method = Homework03_14.class.getDeclaredMethod("sum");
			method.setAccessible(true);
			MyThread t = new MyThread(method, semaphore);
			t.start();
			
			//利用Semaphore的同步机制，通过Thread.sleep(1)触发操作系统立刻进行一次CPU调度,期望主线程让出CPU，线程T先进入同步块执行，线程T结束后主线程再进入同步块，即可拿到结果
			//此方法在Windows10操作系统（Windows操作系统CPU调度为抢占式）上运行多次均可以达到上述期望结果，Linux系统暂未测试
			//另外，Thread.sleep(0)达不到上述期望结果
			Thread.sleep(1);
			
			semaphore.acquireUninterruptibly();
			int result = t.myResult;
			semaphore.release();

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
