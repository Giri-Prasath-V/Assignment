package calculator;


class Calculator
{
	public int add(int a, int b)
	{
		System.out.println("Addition : "+(a+b));
		return a+b;
	}
	public int sub(int a, int b)
	{
		System.out.println("Subtract : "+(a-b));
		return a-b;
	}
	public int mul(int a, int b)
	{
		System.out.println("Multiply : "+(a * b));
		return a*b;
	}
}
