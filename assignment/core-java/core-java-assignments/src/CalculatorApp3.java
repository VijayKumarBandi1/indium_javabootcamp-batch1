public class CalculatorApp3 {
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
                result = add(x, y);
                break;

            case "sub":
                result = subtract(x, y);
                break;

            case "mul":
                result = multiply(x, y);
                break;

            case "div":
                try {
                	if(y==0) throw new Exception("Cannot divide by zero");
                    result = divide(x, y);
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

 

    public static double add(double x, double y) {
        return x + y;
    }

    public static double subtract(double x, double y) {
        return x - y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

 

    public static double divide(double x, double y) {
        return x / y;
    }
}
