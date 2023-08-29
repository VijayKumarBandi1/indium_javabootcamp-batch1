import java.util.Scanner;

public class CalculatorApp {

	public static void main(String[] args)
	   {
		int choice;
	      calculator();
	      Scanner scan = new Scanner(System.in);
	      do {
	      System.out.println("\n1. continue");
	      System.out.println("2. exit");
	      System.out.print("\nEnter Your Choice (1-2):\n ");
	      choice = scan.nextInt();
	      if(choice<=2) {
	    	  if(choice==1) 
	    		  calculator();
	    	  else 
	    		  System.out.println("\nGood Bye!! Have a nice day");
	      }
	      else
	    	  System.out.println("\nInvalid Choice!\n");
	      }while(choice==1);
	   } 

	public static void calculator() {
		float a, b, res;
	      int choice;
	      Scanner scan = new Scanner(System.in);
	      
	      
	      System.out.println("1. Addition(+)");
	      System.out.println("2. Subtraction(-)");
	      System.out.println("3. Multiplication(*)");
	      System.out.println("4. Division(/)");
	      System.out.print("Enter Your Choice (1-4): ");
	      choice = scan.nextInt();
	      
	      if(choice>=1 && choice<=4)
	      {
	         System.out.print("\nEnter First Number: ");
	         a = scan.nextFloat();
	         System.out.print("Enter Second Number: ");
	         b = scan.nextFloat();
	         
	         if(choice==1)
	            res = a+b;
	         else if(choice==2)
	            res = a-b;
	         else if(choice==3)
	            res = a*b;
	         else
	            res = a/b;
	         
	         System.out.println("\nResult = " +res);
	      }
	      else
	         System.out.println("\nInvalid Choice!");
	}
	
}
