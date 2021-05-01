package com.girish.coding;

public class SubsetArrayOrdered {

	public static void main(String[] args) {
		int[] arr2 = {1, 5, 3, 8, 4, 6};
		int[] arr1 = {5, 4};
		System.out.println(SubsetArrayOrdered.checkForSubsetArray(arr1, arr2));
	}
	
	public static boolean checkForSubsetArray(int[] arr1, int[] arr2) {
		boolean isSubset = false;
		int[] big = arr1;
		int[] small = arr2;
		int bigIndex = 0;
		int smallIndex = 0;
		if ((arr1 != null && arr1.length > 0) && (arr2 != null && arr2.length > 0)) {
			if (arr1.length < arr2.length) {
				big = arr2;
				small = arr1;
			}
			while ((bigIndex < big.length)) {
				if (big[bigIndex] == small[smallIndex]) {
					smallIndex++;
					if (smallIndex == small.length) {
						isSubset = true;
						break;
					}
				}
				bigIndex++;
			}
		}
		return isSubset;
	}

}
