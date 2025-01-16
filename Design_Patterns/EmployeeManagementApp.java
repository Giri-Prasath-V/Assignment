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

    static boolean isCeoCreated=false;

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
    //abstract public Object getObject(int id,String name,int age);

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

    public static  Employee createObject(int choice)
    {
        Employee employee = null;
        if(choice ==1)
        {
            employee = Ceo.getObject(0,"",0);
        }
        else if (choice == 2) 
        {
            if(Employee.isCeoCreated)
            {
                employee = Clerk.getObject(0,"",0);;
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }
                                
        } else if (choice == 3) 
        {
            if(Employee.isCeoCreated)
            {
                employee = Programmer.getObject(0,"",0);;
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }
        } else if (choice == 4) 
        {
            if(Employee.isCeoCreated)
            {
                employee =Manager.getObject(0,"",0);;
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }

                                
        }

        return employee;

    }

   
}

final class Manager extends Employee {
    private Manager(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(100000);
    }

    public static Manager getObject(int id, String name, int age)
    {
        return new Manager(id,name,age);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 15000);
    }
}

class Programmer extends Employee {
    private Programmer(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(30000);
    }
    public static Programmer getObject(int id, String name, int age)
    {
        return new Programmer(id,name,age);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 5000);
    }
}

class Clerk extends Employee {
    private Clerk(int id, String name, int age) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(20000);
    }
    
    public static Clerk getObject(int id, String name, int age)
    {
        return new Clerk(id,name,age);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 2000);
    }
}

class Ceo extends Employee{

    

    private Ceo(int id, String name, int age)
    {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("CEO");
        setSalary(200000);
    }
    public static Ceo getObject(int id, String name, int age)
    {
        if(!isCeoCreated)
        {
            isCeoCreated=true;
            return new Ceo(id,name,age);
        }
        else
        {
            System.out.println("CEO object is already created....");
            return null;
        }
    }
    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 30000);
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
                        System.out.println("1.CEO\n2.Clerk\n3.Programmer\n4.Manager\n5.Exit");
                        System.out.println("---------------------------");
                        
                        int subChoice=Menu.readChoice(5);

                        if (subChoice == 5) 
                        {
                            break;
                        }

                        try 
                        {
                            Employee employee = Employee.createObject(subChoice);
                            
                           /* if(subChoice ==1)
                            {
                                employee = Ceo.getObject(0,"",0);
                            }
                            else if (subChoice == 2) 
                            {
                                if(Employee.isCeoCreated)
                                {
                                    employee = Clerk.getObject(0,"",0);;
                                }
                                else
                                {
                                    System.out.println("Need to create CEO first...");
                                }
                                
                            } else if (subChoice == 3) 
                            {
                                if(Employee.isCeoCreated)
                                {
                                    employee = Programmer.getObject(0,"",0);;
                                }
                                else
                                {
                                    System.out.println("Need to create CEO first...");
                                }
                            } else if (subChoice == 4) 
                            {
                                if(Employee.isCeoCreated)
                                {
                                    employee =Manager.getObject(0,"",0);;
                                }
                                else
                                {
                                    System.out.println("Need to create CEO first...");
                                }

                                
                            } */

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


