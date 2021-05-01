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

public class InnerClassSingletonMain {

	public static void main(String[] args) {
		int threadCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(InnerClassSingleton.getInstance().hashCode());
	        });
	    }
		System.out.println(InnerClassSingleton.getInstance().hashCode());
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(InnerClassSingleton.getInstance().hashCode());
	        });
	    }
		
		InnerClassSingleton instanceOne = InnerClassSingleton.getInstance();
		InnerClassSingleton instanceTwo = null;
        ObjectOutput out;
        try {
        	out = new ObjectOutputStream(new FileOutputStream(
        			"innersingleton.ser"));
        	out.writeObject(instanceOne);
        	out.close();

        	ObjectInput in = new ObjectInputStream(new FileInputStream(
        			"innersingleton.ser"));
        	instanceTwo = (InnerClassSingleton) in.readObject();
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


class InnerClassSingleton implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1110682083122445968L;
	
	private String name;
	private static int index = 0;

	private InnerClassSingleton() {
		super();
		name = "Inner Class Singleton Implementation - " + index;
		index++;
	}
	
	private static class InnerClassSingletonHolder {
		private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
		
	}
	
	public static InnerClassSingleton getInstance() {
		return InnerClassSingletonHolder.INSTANCE;
	}
	
	public String getName() {
		return name;
	}
	
	protected Object readResolve() {
	    return getInstance();
	}
	
}