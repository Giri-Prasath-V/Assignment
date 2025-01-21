import java.lang.reflect.Method;
import java.util.*;

class Calculator
{
    public static int add(int a, int b)
    {
        return a + b;
    }

    public static int sub(int a, int b)
    {
        return a - b;
    }

    public static int mul(int a, int b)
    {
        return a * b;
    }

    public static int div(int a, int b)
    {
        if (b == 0) 
        {
            System.out.println("Error: Division by zero!");
            return 0; 
        }
        return a / b;
    }
}

public class CalculatorApp
{
    public static void main(String args[]) throws Exception
    {
        int a, b;
        String op;
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Enter the operation (add, sub, mul, div): ");
        op = sc.nextLine();

        
        System.out.print("Enter Parameter 1: ");
        a = sc.nextInt();
        System.out.print("Enter Parameter 2: ");
        b = sc.nextInt();

       
        Method method = null;
        try 
        {
            method = Calculator.class.getMethod(op, int.class, int.class);
        } catch (NoSuchMethodException e) 
        {
            System.out.println("Invalid operation!");
            System.exit(0);
        }

        Calculator obj=new Calculator();
        int result = (int) method.invoke(obj,a, b); 

      
        System.out.println("Result: " + result);
    }
}
