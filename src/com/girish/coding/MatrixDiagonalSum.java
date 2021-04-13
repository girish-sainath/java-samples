package com.girish.coding;

public class MatrixDiagonalSum {

	public static void main(String[] args) {
		int[][] matrix = { {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5, 6} };
		int l = matrix[0].length - 1;
		int sum = 0;
		for(int i = 0; i <= l; i++) {
			int j = l - i;
			sum += matrix[i][i];
			sum += matrix[j][i];
		}
		if(l % 2 == 0) {
			sum -= matrix[l/2][l/2];
		}
		System.out.println("Input Matrix:");
		for(int i = 0; i <= l; i++) {
			for(int j = 0; j <= l; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println("\n");
		}
		System.out.println("Sum of Matix Diagonal = " + sum);
	}

}
