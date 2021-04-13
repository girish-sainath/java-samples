package com.girish.java.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InnerClassSingletonMain {

	public static void main(String[] args) {
		int threadCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(InnerClassSingletonImpl.getInstance().getName());
	        });
	    }
		System.out.println(InnerClassSingletonImpl.getInstance().getName());
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(InnerClassSingletonImpl.getInstance().getName());
	        });
	    }
	}

}


class InnerClassSingletonImpl {
	
	private String name;
	private static int index = 0;

	private InnerClassSingletonImpl() {
		super();
		name = "Inner Class Singleton Implementation - " + index;
		index++;
	}
	
	private static class InnerClassSingletonHolder {
		private static final InnerClassSingletonImpl INSTANCE = new InnerClassSingletonImpl();
		
	}
	
	public static InnerClassSingletonImpl getInstance() {
		return InnerClassSingletonHolder.INSTANCE;
	}
	
	public String getName() {
		return name;
	}
	
}