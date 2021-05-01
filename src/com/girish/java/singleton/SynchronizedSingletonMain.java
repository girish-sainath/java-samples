package com.girish.java.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedSingletonMain {

	public static void main(String[] args) {
		int threadCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(SynchronizedSingleton.getInstance().hashCode());
	        });
	    }
		System.out.println(SynchronizedSingleton.getInstance().hashCode());
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(SynchronizedSingleton.getInstance().hashCode());
	        });
	    }
		
		SynchronizedSingleton instanceOne = SynchronizedSingleton.getInstance();
		SynchronizedSingleton instanceTwo = null;
        ObjectOutput out;
        try {
        	out = new ObjectOutputStream(new FileOutputStream(
        			"syncsingleton.ser"));
        	out.writeObject(instanceOne);
        	out.close();

        	ObjectInput in = new ObjectInputStream(new FileInputStream(
        			"syncsingleton.ser"));
        	instanceTwo = (SynchronizedSingleton) in.readObject();
        	in.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        
        System.out.println("Instance One = " + instanceOne.hashCode());
        System.out.println("Instance Two = " + instanceTwo.hashCode());
	}

}


class SynchronizedSingleton implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2744019878120417393L;
	
	private static volatile SynchronizedSingleton instance;
	private static int index = 0;
	private String name;

	private SynchronizedSingleton() {
		super();
		name = "Synchronized Singleton Implementation - " + index;
		index++;
	}
	
	public static SynchronizedSingleton getInstance() {
		if(instance == null) {
			synchronized (SynchronizedSingleton.class) {
				if(instance == null) {
					System.out.println("Initializing Singleton... This should not repeat...");
					instance = new SynchronizedSingleton();
				}
			}
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	
	protected Object readResolve() {
	    return getInstance();
	}
	
}