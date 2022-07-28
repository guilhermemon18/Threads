package exemplos;

import java.lang.reflect.InvocationTargetException;

public class MyThreadApp {

	 /**
	  * @param args
	  * @throws InvocationTargetException
	  * @throws InterruptedException
	  */
	 public static void main(String[] args) throws InterruptedException,
	 InvocationTargetException {
	     Thread t1 = new Thread() {
	            @Override
	            public void run() {
	                   for(int i = 0; i < 10000; i++)
	                   System.out.println(i+": t1");
	            }

	     };

	     Thread t2 = new Thread() {
	            @Override
	            public void run() {
	                   for(int i = 0; i < 10000; i++)
	                          System.out.println(i+": t2");
	            }

	     };


	     t1.start();
	     t2.start();
	     System.out.println("THREADS ATIVAS = "+Thread.activeCount());

	 }

	}