package com.awes.TestJava;

public class TestRegex {

	public static void main(String[] args) {
		 String str = "London, Jon 2 A";
	     System.out.println(str.replaceAll("\\d.*",""));
	}
}
