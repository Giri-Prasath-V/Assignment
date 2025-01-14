package emp.assignment;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.*;

abstract class Employee 
{
    private int id;
    private String name;
    private int age;
    private int salary;
    private String designation;

    static int count = 0;

    Employee() 
    {
    }

    public void setEmpId(int id) 
    {
        this.id = id;
    }

    public int getEmpId() 
    {
        return this.id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public void setDesignation(String designation) 
    {
        this.designation = designation;
    }

    public void setSalary(int salary) 
    {
        this.salary = salary;
    }

    public int getSalary() 
    {
        return this.salary;
    }

    abstract public void raiseSalary();

    final public void display() 
    {
        System.out.println("\nEmp Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Salary: " + this.salary);
        System.out.println("Designation: " + this.designation);
    }

    public static void removeEmployee(Employee[] employees) 
    {

        Scanner obj=new Scanner(System.in);

        
        int id=Inputs.readEmpIdToRemove();

        boolean removed = false;

        for (int i = 0; i < count; i++) 
        {
            if (employees[i].getEmpId() == id) 
            {
                employees[i].display();
                System.out.print("Do you really want to remove this employee record (Y/N): ");
                
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
                System.out.println("There is no Employee with this id...");
            }
        }
    }

    public static boolean checkId(int id, Employee[] employees) 
    {
        for (int i = 0; i < count; i++) 
        {
            if (employees[i].getEmpId() == id) 
            {
                return false;
            }
        }
        return true;
    }

    

    public void getInput(Employee[] employees) 
    {
        
       
        setEmpId(Inputs.readEmpId(employees));
    
        setName(Inputs.readName());

        setAge(Inputs.readAge(21,60));
    }

   
}

final class Manager extends Employee {
    Manager(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(100000);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 15000);
    }
}

class Programmer extends Employee {
    Programmer(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(30000);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 5000);
    }
}

class Clerk extends Employee {
    Clerk(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(20000);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 2000);
    }
}




public class EmployeeManagementApp 
{
    public static void main(String args[]) 
    {
        Scanner obj = new Scanner(System.in);
        final int MAX_EMPLOYEES = 100;
        Employee[] employees = new Employee[MAX_EMPLOYEES];

        while (true) 
        {
            System.out.println("---------------------------");
            System.out.println("1.Create\n2.Display\n3.Raise Salary\n4.Remove\n5.Exit");
            System.out.println("---------------------------");
            
            int choice=Menu.readChoice(5);
        

            if (choice == 5) 
            {
                System.out.println("\nTotal No.of Employee : " + Employee.count);
                break;
            }

            switch (choice) 
            {
                case 1: 
                {
                    while (true) 
                    {
                        System.out.println("---------------------------");
                        System.out.println("1.Clerk\n2.Programmer\n3.Manager\n4.Exit");
                        System.out.println("---------------------------");
                        
                        int subChoice=Menu.readChoice(4);

                        if (subChoice == 4) 
                        {
                            break;
                        }

                        try 
                        {
                            Employee employee = null;
                            
                            if (subChoice == 1) 
                            {
                                employee = new Clerk(0, "", 0);
                            } else if (subChoice == 2) 
                            {
                                employee = new Programmer(0, "", 0);
                            } else if (subChoice == 3) 
                            {
                                employee = new Manager(0, "", 0);
                            }

                            if (employee != null) 
                            {
                                employee.getInput(employees);
                                employees[Employee.count] = employee;
                                Employee.count++;
                                System.out.println("Employee added successfully");
                            }
                        } 
                        catch (Exception e) 
                        {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
                    break;
                }

                case 2: {
                    for (int i = 0; i < Employee.count; i++) {
                        System.out.println("---------------------------");
                        employees[i].display();
                        System.out.println("---------------------------");
                    }
                    break;
                }

                case 3: {
                    for (int i = 0; i < Employee.count; i++) 
                    {
                        employees[i].raiseSalary();
                    }
                    System.out.println("Salary of all employee raised successfully");
                    break;
                }

                case 4: {
                    Employee.removeEmployee(employees);
                    
                }
            }
        }
        obj.close();
    }
}

class InvalidChoiceException extends Exception 
{
    public InvalidChoiceException()
    {

    }
    public InvalidChoiceException(String msg) 
    {
        super(msg);
    }
    public void displayMsg(int max)
    {
        System.out.println("You only have options from 1 to "+max);
    }
}

class Menu extends Exception
{
    private static int maxChoice;
    public static int readChoice(int max)
    {
        maxChoice=max;

        while(true)
        {
            System.out.print("Enter Choice :");
            try
            {
                int choice=new Scanner(System.in).nextInt();
                if(choice <1 || choice > maxChoice)
                {
                    throw new InvalidChoiceException();
                }
                return choice;
                
            }
            catch(InvalidChoiceException e)
                {
                    e.displayMsg(maxChoice);
                }
                catch(InputMismatchException e)
                {
                    System.out.println("Only numerical values allowed...");
                }
        }
    }
}

class AgeMismatchException extends Exception 
{
    public AgeMismatchException()
    {

    }
    public AgeMismatchException(String msg) 
    {
        super(msg);
    }
    public void displayMsg(int minAge,int maxAge)
    {
        System.out.println("Age should be between "+minAge+" to "+ maxAge);
    }

}

class InvalidEmpIdException extends Exception 
{
    public InvalidEmpIdException()
    {

    }
    public InvalidEmpIdException(String message) 
    {
        super(message);
    }
    
}

class NameMismatchException extends Exception 
{
    public NameMismatchException()
    {

    }
    public NameMismatchException(String msg) 
    {
        super(msg);
    }
    public void displayMsg()
    {
        System.out.println("Name must be in format...");
    }

}

class Inputs extends Exception 
{
    public static int readAge(int minAge,int maxAge)
    {
        int age;
        while (true) 
        {
            System.out.print("Enter Age: ");
            try 
            {
                age = new Scanner(System.in).nextInt();
                
                if (age <= minAge || age >= maxAge) 
                {
                    throw new AgeMismatchException();
                }
                return age;
                
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Exception occurs: Age must be a numeric value.");
                
            } 
            catch (AgeMismatchException e) 
            {
                e.displayMsg(minAge,maxAge);
            }
        }
    }

    public static int readEmpId(Employee[] employees)
    {
        int id;
       
        while (true) 
        {
            try
            {
            System.out.print("Enter Emp Id : ");
            id = new Scanner(System.in).nextInt();


            if (id < 0) 
            {
                throw new InvalidEmpIdException("Employee Id must be a positive numeric value.");
            }
            if( !Employee.checkId(id,employees))
            {
                throw new InvalidEmpIdException("Employee Id is already taken. Provide new Employee Id....");
                
            }
            return id;
            }
            catch(InvalidEmpIdException e)
            {
                System.out.println("Exception occurs: " + e.getMessage());
                
            }
            catch (InputMismatchException e) 
            {
                System.out.println("Exception occurs: Employee Id must be a numeric value.");
                
            }
            
           
        }

        
    }

    public static int readEmpIdToRemove()
    {
        int id;
       
        while (true) 
        {
            try
            {
            System.out.print("Enter Emp Id : ");
            id = new Scanner(System.in).nextInt();


            if (id < 0) 
            {
                throw new InvalidEmpIdException("Employee Id must be a positive numeric value.");
            }
            
            return id;
            }
            catch(InvalidEmpIdException e)
            {
                System.out.println("Exception occurs: " + e.getMessage());
                
            }
            catch (InputMismatchException e) 
            {
                System.out.println("Exception occurs: Employee Id must be a numeric value.");
                
            }
            
           
        }

        
    }

    public static String readName()
    {
        String  name;
        
        while(true)
        {
            try
            {
                System.out.print("Enter Name: ");
                name=new Scanner(System.in).nextLine();
                Pattern p1=Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]*$");
                Matcher m1=p1.matcher(name);

                if(m1.matches())
                {
                    return name;
                }
                else
                {
                    throw new NameMismatchException();
                }
            }
            catch(NameMismatchException e)
            {
                e.displayMsg();
            }
        }

    }
}


