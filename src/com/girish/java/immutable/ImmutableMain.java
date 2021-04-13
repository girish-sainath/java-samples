package com.girish.java.immutable;

import java.util.Arrays;
import java.util.Date;

public class ImmutableMain {

	public static void main(String[] args) {
		int[] valuesOdd = {1, 3, 5};
		ImmutableImpl imuOdd = new ImmutableImpl("Girish", valuesOdd, new Date());
		int[] valuesOdd1 = imuOdd.getValues();
		valuesOdd1[1] = 4;
		print(imuOdd.getValues());
		print(valuesOdd1);
		
		Date oddDate = imuOdd.getCreationDate();
		oddDate.setTime(oddDate.getTime() - 100000);
		System.out.println(imuOdd.getCreationDate());
		System.out.println(oddDate);
		
		int[] valuesEven = {2, 4, 6};
		ImmutableImpl imuEven = new ImmutableImpl("Girish", valuesEven, new Date());
		int[] valuesEven1 = imuEven.getValues();
		valuesEven1[1] = 3;
		print(imuEven.getValues());
		print(valuesEven1);
	}
	
	public static void print(int[] arr) {
		for(int i = 0, l = arr.length; i < l; i++) {
			System.out.print(arr[i]);
			if(i < (l - 1)) {
				System.out.print(", ");
			} else {
				System.out.println("");
			}
		}
	}

}

final class ImmutableImpl {
	
	private final String name;
	private final int[] values;
	private final Date creationDate;
	
	public ImmutableImpl(String name, int[] values, Date creationDate) {
		super();
		this.name = name;
		this.values = values;
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public int[] getValues() {
		int[] refValues = Arrays.copyOf(values, values.length);
		return refValues;
	}

	public Date getCreationDate() {
		return new Date(creationDate.getTime());
	}
	
}
