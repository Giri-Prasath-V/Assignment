package emp.assignment;

import java.util.Scanner;
import java.util.ArrayList;
abstract class Employee{
    private int id;
    private String name;
	private int age;
	private int salary;
	private String designation;

    static int count=0;

    Employee() {  
    }


    public void setEmpId(int id)
    {
        this.id=id;
    }

    public int getEmpId()
    {
        return this.id;
    }


    public void setName(String name)
    {
        this.name=name;
    }

    public void setAge( int age)
    {
        if(age >=21 && age <=60)
        {
            this.age=age;
        }
        else
        {
            System.out.println("Age must be between 21 to 60");
        }
        
    }

    public void setDesignation(String designation)
    {
        this.designation=designation;
    }

    public void setSalary(int salary)
    {
        this.salary=salary;
    }
	
    public int getSalary()
    {
        return this.salary;
    }

    abstract public void raiseSalary();
    
    

    final public void display() {
        System.out.println("\nEmp Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Salary: " + this.salary);
        System.out.println("Designation: " + this.designation);
    }

    public static void removeEmployee(Employee[] employees, int id) {
        boolean removed = false;

        for (int i = 0; i < count; i++) 
        {
            if (employees[i].getEmpId() == id) 
            {
                employees[i].display();
                System.out.print("Do you really want to remove this employee record (Y/N): ");
                Scanner obj = new Scanner(System.in);
                String result = obj.nextLine();

                if (result.equals("Y")) 
                {
                    
                    for (int j = i; j < count - 1; j++) 
                    {
                        employees[j] = employees[j + 1];
                    }
                    employees[count - 1] = null;
                    count--;
                    removed = true;
                    System.out.println("Employee removed successfully...");
                    break;
                } 
                else if (result.equals("N")) 
                {
                    System.out.println("Employee removal cancelled.");
                    break;
                }
            }
            else
            {
                System.out.println("Employee not found...");
            }
        }

        
    }




}

final class Manager extends Employee
{
	Manager(int id,String name,int age)
	{
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(100000);
	}
    public void raiseSalary()
    {
        int sal=getSalary();
        setSalary(sal+15000);
    }
}


class Programmer extends Employee
{
	Programmer(int id,String name,int age)
	{
        setEmpId(id);
	    setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(30000);
	}

    public void raiseSalary()
    {
        int sal=getSalary();
        setSalary(sal+5000);
    }

}


class Clerk extends Employee
{
	Clerk(int id,String name,int age)
	{
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(20000);
	}

    public void raiseSalary()
    {
        int sal=getSalary();
        setSalary(sal+2000);
    }

}

public class EmployeeManagement
{
    public static void main(String args[])
    {
        Scanner obj=new Scanner(System.in);
        final int MAX_EMPLOYEES = 100;
        Employee[] employees = new Employee[MAX_EMPLOYEES];
        

        

        while(true)
        {
            System.out.println("---------------------------");
            System.out.println("1.Create\n2.Display\n3.Raise Salary\n4.Remove\n5.Exit");
            System.out.println("---------------------------");
            System.out.print("Enter Choice :");
            int choice;
            choice=obj.nextInt();
            obj.nextLine();

            if(choice == 5)
                {
                    System.out.println("\nTotal No.of Employee : "+Employee.count);
                    break;
                }

            switch(choice)
            {


                case 1:
                    {
                        while(true)
                        {
                            System.out.println("---------------------------");
                            System.out.println("1.Clerk\n2.Programmer\n3.Manager\n4.Exit");
                            System.out.println("---------------------------");
                            System.out.print("Enter Choice :");
                            int subChoice;
                            subChoice=obj.nextInt();
                            obj.nextLine();

                            if(subChoice==4)
                            {
                                break;
                            }
                            

                            System.out.print("Enter Emp Id :");
                            int id=obj.nextInt();
                            obj.nextLine();
                            System.out.print("Enter Name :");
                            String name=obj.nextLine();
                            System.out.print("Enter Age :");
                            int age=obj.nextInt();
                            obj.nextLine();

                            Employee employee=null;


                            switch(subChoice)
                            {
                                case 1:
                                    {
                                        if(checkId(id,employees))
                                        {
                                            employee=new Clerk(id,name,age);
                                        }
                                        else
                                        {
                                            System.out.println("Employee Id already taken");
                                        }
                                        
                                        break;
                                    }
                                case 2:
                                    {
                                        if(checkId(id,employees))
                                        {
                                            employee=new Programmer(id,name,age);
                                        }
                                        else
                                        {
                                            System.out.println("Employee Id already taken");
                                        }
                                        break;

                                    }
                                case 3:
                                    {
                                        if(checkId(id,employees))
                                        {
                                            employee=new Manager(id,name,age);
                                        }
                                        else
                                        {
                                            System.out.println("Employee Id already taken");
                                        }
                                        break;

                                    }
                            }

                            if (employee != null && Employee.count < MAX_EMPLOYEES) 
                            {
                                employees[Employee.count] = employee; 
                                Employee.count++;
                                System.out.println("Employee added successfully");
                            } 
                            else if (Employee.count >= MAX_EMPLOYEES) 
                            {
                            System.out.println("Cannot add more employees, array is full!");
                            }
                        }


                        

                    break;
                        

                    }
                case 2:
                    {
                       for (int i = 0; i < Employee.count; i++) 
                       {
                            System.out.println("---------------------------");
                            employees[i].display();
                            System.out.println("---------------------------");
                    }

                       break;

                    }
                case 3:
                    {
                        for (int i = 0; i < Employee.count; i++) {
                        employees[i].raiseSalary();
                    }
                    

                       System.out.println("Salary of all employee raised succesfully");

                       break;

                    }
                case 4:
                    {
                    System.out.print("Enter Emp Id to Remove: ");
                    int id = obj.nextInt();
                    obj.nextLine();
                    Employee.removeEmployee(employees, id);

                    break;
                    }
                
            }
        }

        
    }
    public static boolean checkId(int id,Employee[] employees)
        {
            for (int i = 0; i < Employee.count; i++) 
            {
                if(employees[i].getEmpId() ==id)
                {
                    return false;
                }
                
            }
            return true;

        }
}
