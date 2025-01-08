import java.util.Scanner;
import java.util.ArrayList;
class Employee{
    private String name;
	private int age;
	private int salary;
	private String designation;

    Employee() {  
    }


    void setName(String name)
    {
        this.name=name;
    }

    void setAge( int age)
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

    void setDesignation(String designation)
    {
        this.designation=designation;
    }

    void setSalary(int salary)
    {
        this.salary=salary;
    }
	

    void raiseSalary(){

        if(this.designation == "Clerk")
        {
            this.salary=this.salary+2000;
        }
        else if(this.designation=="Programmer")
        {
            this.salary=this.salary+5000;
        }
        else if(this.designation=="Manager")
        {
            this.salary=this.salary+15000;
        }

    }

    void display() {
        System.out.println("\nName: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Salary: " + this.salary);
        System.out.println("Designation: " + this.designation);
    }



}

class Manager extends Employee
{
	Manager(String name,int age)
	{
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(100000);
	}
}


class Programmer extends Employee
{
	Programmer(String name,int age)
	{
	    setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(30000);
	}

}


class Clerk extends Employee
{
	Clerk(String name,int age)
	{
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(20000);
	}

}

public class EmployeeManagementApp{
    public static void main(String args[])
    {
        Scanner obj=new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();
        int count = 0;

        

        while(true)
        {
            System.out.println("---------------------------");
            System.out.println("1.Create\n2.Display\n3.Raise Salary\n4.Exit");
            System.out.println("---------------------------");
            System.out.print("Enter Choice :");
            int choice;
            choice=obj.nextInt();
            obj.nextLine();

            if(choice == 4)
                {
                    System.out.println("\nTotal No.of Employee : "+count);
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
                                        employee=new Clerk(name,age);
                                        break;
                                    }
                                case 2:
                                    {
                                        employee=new Programmer(name,age);
                                        break;

                                    }
                                case 3:
                                    {
                                        employee=new Manager(name,age);
                                        break;

                                    }
                            }

                            if(employee != null)
                            {
                                employees.add(employee);
                                count++;
                                System.out.println("Employee added successfully" );
                            }


                        }

                    break;
                        

                    }
                case 2:
                    {
                       for(Employee emp:employees)
                       {
                        System.out.println("---------------------------");
                        emp.display();
                        System.out.println("---------------------------");
                       }

                       break;

                    }
                case 3:
                    {
                        for(Employee emp:employees)
                       {
                        emp.raiseSalary();
                       }

                       System.out.println("Salary of all employee raised succesfully");

                       break;

                    }
                
            }
        }
    }
}