package com.labs.java.fp;

public class CalcMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CalOOP calcOOP = new CalOOP();

		calcOOP.a = 50;
		calcOOP.b = 25;
//		
//		System.out.println(calcOOP.add());
//		System.out.println(calcOOP.sub());
//		System.out.println(calcOOP.mul());
//		System.out.println(calcOOP.div());
//		
		calcOOP.a = 20;
		calcOOP.b = 30;

//		System.out.println(calcOOP.add());
//		System.out.println(calcOOP.sub());
//		System.out.println(calcOOP.mul());
//		System.out.println(calcOOP.div());

		// functional programming
		CalcFP cFP = new CalcFP();

//		System.out.println(cFP.add(25,55));
//		System.out.println(cFP.add(30,60));
//		System.out.println(cFP.add(35,65));
//		
//		System.out.println(cFP.sub(25,55));
//		System.out.println(cFP.mul(25,55));
//		System.out.println(cFP.div(25,55));

		// functional programming with compute method
//		System.out.println(cFP.compute("add", 15, 10));
//		System.out.println(cFP.compute("sub", 15, 10));
//		System.out.println(cFP.compute("mul", 15, 10));
//		System.out.println(cFP.compute("div", 15, 10));

		// F P with math operation interface and compute method
//		MathOperation add = new MathOperation() {
//
//			@Override
//			public int compute(int a, int b) {
//
//				return a + b;
//			}
//		};
//
//		MathOperation sub = new MathOperation() {
//
//			@Override
//			public int compute(int a, int b) {
//
//				return a - b;
//			}
//		};
//
//		MathOperation div = new MathOperation() {
//
//			@Override
//			public int compute(int a, int b) {
//
//				return a / b;
//			}
//		};
//
//		MathOperation mul = new MathOperation() {
//
//			@Override
//			public int compute(int a, int b) {
//
//				return a * b;
//			}
//		};
//
//		int result = cFP.compute(mul, 10, 30);
//		System.out.println(result);
//
//		result = cFP.compute(add, 10, 30);
//		System.out.println(result);
//
//		result = cFP.compute(sub, 10, 30);
//		System.out.println(result);
//
//		result = cFP.compute(div, 30, 10);
//		System.out.println(result);
		
		//Lambda Expression
		
		MathOperation add = (int a,int b)->{return a+b;};

		MathOperation sub = (a,b)->a-b;

		MathOperation div = (a,b)->{int c = a/b; return c;};

		MathOperation mul = (a,b)->a*b;
		
		MathOperation mod = (a,b)->a%b;

		int result = cFP.compute(mul, 10, 30);
		System.out.println(result);

		result = cFP.compute(add, 10, 30);
		System.out.println(result);

		result = cFP.compute(sub, 10, 30);
		System.out.println(result);

		result = cFP.compute(div, 30, 10);
		System.out.println(result);
		
		result = cFP.compute(mod, 30, 10);
		System.out.println(result);
		
		
	}

}

//class Add implements MathOperation{
//
//	@Override
//	public int compute(int a, int b) {
//		
//		return a+b;
//	}
//	
//}
