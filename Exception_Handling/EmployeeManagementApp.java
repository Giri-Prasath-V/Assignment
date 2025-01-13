package emp.assignment;

import java.util.Scanner;
import java.util.InputMismatchException;

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

        Scanner obj = new Scanner(System.in);
        int id=0;

       
        while (true) 
        {
            try
            {
            System.out.print("Enter Emp Id to remove: ");
            String input = obj.nextLine();

            if (!isNumeric(input)) 
            {
                throw new InvalidEmpIdException("Employee Id must be a numeric value.");
            }

            id = Integer.parseInt(input);

            if (id < 0) 
            {
                throw new InvalidEmpIdException("Employee Id must be a positive numeric value.");
                
            }
            break;
            }
            catch(InvalidEmpIdException e)
            {
                System.out.println("Exception occurs: " + e.getMessage());
                obj.nextLine();
            }
            
           
        }


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

    public static boolean isNumeric(String str) 
    {
        try 
        {
            Integer.parseInt(str);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            return false;
        }
    }

    public void getInput(Employee[] employees) 
    {
        Scanner obj = new Scanner(System.in);

       
       int id=0;

       
        while (true) 
        {
            try
            {
            System.out.print("Enter Emp Id : ");
            String input = obj.nextLine();

            if (!isNumeric(input)) 
            {
                throw new InvalidEmpIdException("Employee Id must be a numeric value.");
            }

            id = Integer.parseInt(input);

            if (id < 0) 
            {
                throw new InvalidEmpIdException("Employee Id must be a positive numeric value.");
                
            }
            if( !checkId(id,employees))
            {
                System.out.println("Employee Id is already taken. Provide new Employee Id....");
                continue;
            }
            setEmpId(id);
            break;
            }
            catch(InvalidEmpIdException e)
            {
                System.out.println("Exception occurs: " + e.getMessage());
                obj.nextLine();
            }
            
           
        }

        
        System.out.print("Enter Name: ");
        setName(obj.nextLine());

        
        while (true) 
        {
            System.out.print("Enter Age: ");
            try 
            {
                int age = obj.nextInt();
                if (age < 0) 
                {
                    throw new InputMismatchException("Age must be a positive numeric value.");
                }
                if (age <= 21 || age >= 60) 
                {
                    throw new AgeMismatchException("Age should be between 21 to 60.");
                }
                setAge(age);
                break;
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Exception occurs: Age must be a numeric value.");
                obj.nextLine(); 
            } 
            catch (AgeMismatchException e) 
            {
                System.out.println("Exception occurs: " + e.getMessage());
                obj.nextLine(); 
            }
        }
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

class AgeMismatchException extends Exception 
{
    public AgeMismatchException()
    {

    }
    public AgeMismatchException(String msg) 
    {
        super(msg);
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
            
            int choice;
            
            while(true)
            {
            try
            {
                System.out.print("Enter Choice :");
                choice = obj.nextInt();
                obj.nextLine();
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Choice must be a numeric value....");
                obj.nextLine();
            }
            }

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
                        
                        int subChoice;
                        while(true)
                        {
                            try
                            {
                                System.out.print("Enter Choice :");
                                subChoice = obj.nextInt();
                                obj.nextLine();
                                break;
                            }
                            catch(InputMismatchException e)
                            {
                                System.out.println("Choice must be a numeric value....");
                                obj.nextLine();
                            }
                        }

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
    }
}
