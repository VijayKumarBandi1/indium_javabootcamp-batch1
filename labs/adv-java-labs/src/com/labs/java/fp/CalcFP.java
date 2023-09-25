package com.labs.java.fp;

public class CalcFP {
	
	public int add(int a,int b) {
		return a+b;
	}

	public int sub(int a,int b) {
		return a-b;
	}
	
	public int mul(int a,int b) {
		return a*b;
	}
	
	public int div(int a,int b) {
		return a/b;
	}
	
	public int mod(int a,int b) {
		return a%b;
	}
	
	public int compute(String op,int a, int b ) {
		switch (op) {
		case "add":
			return a+b;
		case "sub":
			return a-b;
		case "mul":
			return a*b;
		case "div":
			return a/b;
		default:
			System.out.println("invalid op");
		}
		return 0;
	}
	
	public int compute(MathOperation op,int a, int b ) {
		return op.compute(a,b);
	}
}
