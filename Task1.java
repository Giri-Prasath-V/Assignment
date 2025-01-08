import java.util.Scanner;
class Employee
{
	private String name;
	private int age;
	private int salary;
	private String designation;

	Employee(){}
	
	void set(String name,int age,int salary)
	{
	this.name=name;
	this.age=age;
	this.salary=salary;
	}
	void setdesignation(String designation)
	{
	this.designation=designation;
	}

	void display()
	{
		System.out.println("\nName :"+name+"\nAge :"+age+"\nSalary :"+salary+"\nDesignation :"+designation);
	}

	void raisesalary()
	{
		Scanner obj=new Scanner(System.in);
		if(designation == "Manager")
		{
		System.out.println("\nEnter Amount to increase :");
		int amount=obj.nextInt();
		salary=salary+amount;
		display();
		}
		else
		{
			System.out.println("Only manager can raise the salary");
			return;
		}
	}
}

class Manager extends Employee
{
	Manager()
	{
	setdesignation("Manager");
	}
}


class Programmer extends Employee
{
	Programmer()
	{
	setdesignation("Programmer");
	}

}


class Clerk extends Employee
{
	Clerk()
	{
	setdesignation("Clerk");
	}

}


public class Task1
{
	public static void main(String args[])
	{
	Employee m1=new Manager();
	m1.set("AAAA",21,50000);
	//m1.designation="Watchman";
	m1.display();
	m1.raisesalary();

	Employee c1=new Clerk();
	c1.set("BBBB",21,10000);
	c1.display();
	c1.raisesalary();
	
	
	}
}