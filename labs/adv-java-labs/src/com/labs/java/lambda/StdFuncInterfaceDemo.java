package com.labs.java.lambda;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class StdFuncInterfaceDemo {

	public static void main(String[] args) {
		//predicate
	Predicate<Integer> condition =	(a)->a>10;
	System.out.println(condition.test(20));
	
	//Bipredicate
	BiPredicate<Integer, Integer> condition1 = (a,b)-> a>b;
	System.out.println(condition1.test(20, 30));
	
	//function
	Function<Integer, Integer> compute =(a)->a+10;
	System.out.println(compute.apply(50));
	
	//BiFunction
		BiFunction<Integer, Integer, Integer> addition = (a,b)->a+b;
		System.out.println(addition.apply(30, 40));
		
		 // UnaryOperator
        UnaryOperator<Integer> square = a -> a * a;
        System.out.println(square.apply(5));
        
        BinaryOperator<Integer> area = (a, b) -> a * b;
        System.out.println(area.apply(30, 40));
	
	//consumer 
	Consumer<String> greetings = message ->System.out.println(message);
	greetings.accept("Hello Lambda");

	//BiConsumer
		BiConsumer<String, String> greetings1 = (msg1,msg2)->System.out.println(msg1+" "+msg2);
		greetings1.accept("hello", "hi");
	
	//supplier
	Supplier<List> skills = () -> {return List.of("java","css","js");};
	System.out.println(skills.get());
	}
  
	
}
