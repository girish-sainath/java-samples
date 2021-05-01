package com.girish.coding;

public class AlphabetCountCompression {

	public static void main(String[] args) {
		String input = "aaaaaaaaaaaaaaaaaaaaaabbbccaaabbeaaacccccfffa";
		System.out.println(input + " - Input");
		String compressed = AlphabetCountCompression.compress(input);
		System.out.println(compressed + " - Compressed");
		String decompressed = AlphabetCountCompression.decompress(compressed);
		System.out.println(decompressed + " - Decompressed");
	}
	
	public static String compress(String input) {
		StringBuilder builder = new StringBuilder();
		int count = 1;
		for(int i = 0, l = input.length(); i < l - 1; i++) {
			if(input.charAt(i) == input.charAt(i + 1)) {
				count++;
				if (i == l - 2) {
					builder.append(input.charAt(i));
					builder.append(count);
				}
			} else {
				builder.append(input.charAt(i));
				builder.append(count);
				count = 1;
				if (i == l - 2) {
					builder.append(input.charAt(i + 1));
					builder.append(1);
				}
			}
		}
		return builder.toString();
	}
	
	public static String decompress(String input) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		char curr;
		for(int i = 0, l = input.length(); i < l; i++) {
			if (Character.isAlphabetic(input.charAt(i))) {
				curr = input.charAt(i);
				i++;
				while(i < input.length() && Character.isDigit(input.charAt(i))) {
					if(count > 0) {
						count *= 10;
					}
					count += Integer.parseInt(input.charAt(i) + "");
					i++;
				}
				while(count > 0) {
					builder.append(curr);
					count--;
				}
				count = 0;
				i--;
			}
		}
		return builder.toString();
	}

}
