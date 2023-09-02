public class CalculatorApp2 {
	 public static void main(String[] args) {
		 
	        if (args.length != 3) {
	            System.out.println("Usage: java Calculator  <operand1> <operand2><operation>");
	            System.exit(1);
	        }

	        
	        double x = Double.parseDouble(args[0]);
	        double y = Double.parseDouble(args[1]);
	        String operation = args[2];
	        double result = 0.0;

	        switch (operation) {

	            case "add":
	                result = x+y;
	                break;

	            case "sub":
	                result = x-y;
	                break;

	            case "mul":
	                result = x * y;
	                break;

	            case "div":
	                try {
	                	if(y==0)  throw new Exception("Cannot divide by zero");
	                    result = x / y;
	                } catch (Exception e) {
	                    System.out.println(e.getMessage());
	                    System.exit(1);
	                }
	                break;

	            default:
	                System.out.println("Invalid operation. Choose from 'add', 'subtract', 'multiply', or 'divide'.");
	                System.exit(1);
	        }

	        System.out.println("Result: " + result);
	    }

}
