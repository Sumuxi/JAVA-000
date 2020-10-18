package myClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

	public static void main(String[] args) {
		try {
			Class<?> clazz = new MyClassLoader().findClass("Hello");
			Method method = clazz.getDeclaredMethod("hello", new Class[] {});
			method.invoke(clazz.newInstance(), new Object[] {});
			
		} catch (IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file = new File(name+".xlass");
		long size = file.length();
		System.out.println(size);
		if (size > Integer.MAX_VALUE) {
            throw new ClassNotFoundException("file too big.");
        }
		
		byte[] buffer = new byte[(int) size];
		int len = -1;
		try {
			FileInputStream fis = new FileInputStream(file);
			len = fis.read(buffer);
			fis.close();
			if (len<size) {
				throw new ClassNotFoundException("could not completely read file.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ClassNotFoundException("file not found.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ClassNotFoundException("could not completely read file.");
		}
		
		//还原字节码
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = (byte) (255 - buffer[i]);
		}
		
		return defineClass(name, buffer, 0, buffer.length);
	}
	
	

}
