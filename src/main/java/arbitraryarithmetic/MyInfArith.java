package arbitraryarithmetic;

public class MyInfArith {
    public static void main(String[] args) {
        //System.out.println(AInteger.add(new AInteger("1"),new AInteger("2")).value);
        if(args.length != 4) {
            System.out.println("Usage: java MyInfArith int/float add/sub/mul/div <number1> <number2>");
            return;
        }
        //System.out.println(AInteger.add(new AInteger("1"),new AInteger("2")));
        String type = args[0];
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];
        if(type.equals("int")){
            AInteger first = new AInteger(num1);
            AInteger second = new AInteger(num2);
            switch(operation) {
                case "add":
                    System.out.println(AInteger.add(first, second).value);
                    break;
                case "sub":
                    System.out.println(AInteger.sub(first, second).value);
                    break;
                case "mul":
                    System.out.println(AInteger.mul(first, second).value);
                    break;
                case "div":
                    System.out.println(AInteger.div(first, second).value);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
        }
        else if(type.equals("float")){
            AFloat first = new AFloat(num1);
            AFloat second = new AFloat(num2);
            switch(operation) {
                case "add":
                    System.out.println(AFloat.add(first, second).value);
                    break;
                case "sub":
                    System.out.println(AFloat.sub(first, second).value);
                    break;
                case "mul":
                    System.out.println(AFloat.mul(first, second).value);
                    break;
                case "div":
                    System.out.println(AFloat.div(first, second).value);
                    break;
                default:
                    System.out.println("Invalid operation. Use add, sub, mul, or div.");
                    break;    
            }
        }
        else{
            System.out.println("Invalid type. Use int or float.");
        }
    }
}
