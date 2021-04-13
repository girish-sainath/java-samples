package com.girish.java.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedSingletonMain {

	public static void main(String[] args) {
		int threadCount = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(SynchronizedSingletonImpl.getInstance().getName());
	        });
	    }
		System.out.println(SynchronizedSingletonImpl.getInstance().getName());
		for (int i = 0; i < threadCount; i++) {
	        executorService.execute(() -> {
	        	System.out.println(SynchronizedSingletonImpl.getInstance().getName());
	        });
	    }
	}

}


class SynchronizedSingletonImpl {
	
	private static SynchronizedSingletonImpl instance;
	private static int index = 0;
	private String name;

	private SynchronizedSingletonImpl() {
		super();
		name = "Synchronized Singleton Implementation - " + index;
		index++;
	}
	
	public static SynchronizedSingletonImpl getInstance() {
		if(instance == null) {
			synchronized (SynchronizedSingletonImpl.class) {
				if(instance == null) {
					System.out.println("Initializing Singleton... This should not repeat...");
					instance = new SynchronizedSingletonImpl();
				}
			}
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	
}