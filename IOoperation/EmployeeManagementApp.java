import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.*;
import java.util.*;
import java.io.*;

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

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public String getDesignation()
    {
        return this.designation;
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

    public static void removeEmployee(HashMap<Integer,Employee> employees) 
    {

        Scanner obj=new Scanner(System.in);
        
        int id=Inputs.readEmployeeId();

        if (employees.containsKey(id)) 
        {
            Employee e = employees.get(id);
            e.display();
            System.out.print("Do you really want to remove this employee record (Y/N): ");
            String result = obj.nextLine();

            if (result.equals("Y")) 
            {
                employees.remove(id);
                count--;
                System.out.println("Employee removed successfully...");
                 
            } 
            else if (result.equals("N")) 
            {
                System.out.println("Employee removal cancelled.");
            }
        }
        else{
            System.out.println("There is no Employee with this id...");
        }
            
    }

    public static boolean checkId(int id, HashMap<Integer,Employee> employees) 
    {
       return !employees.containsKey(id);
    }

    

    public void getInput(HashMap<Integer,Employee> employees) 
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
            employee = Ceo.getObject(0,"",0,200000);
        }
        else if (choice == 2) 
        {
            if(Employee.isCeoCreated)
            {
                employee = Clerk.getObject(0,"",0,10000);
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }
                                
        } else if (choice == 3) 
        {
            if(Employee.isCeoCreated)
            {
                employee = Programmer.getObject(0,"",0,30000);;
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }
        } else if (choice == 4) 
        {
            if(Employee.isCeoCreated)
            {
                employee =Manager.getObject(0,"",0,100000);
            }
            else
            {
                System.out.println("Need to create CEO first...");
            }

                                
        }

        return employee;

    }

    public static void searchEmployee(HashMap<Integer,Employee> employees,int choice)
    {

        switch(choice)
        {
            case 1 ->{
                int id=Inputs.readEmployeeId();

                if(employees.containsKey(id))
                {
                        Employee e = employees.get(id);
                        System.out.println("---------------------------");
                        e.display();
                }
                else
                {
                    System.out.println("There is no Employee with this id...");

                }
            }
            case 2 ->{
                String  name=Inputs.readName();

                boolean found=false;

                for(Employee e : employees.values())
                {
                    if(e.getName().equals(name))
                    {
                        System.out.println("---------------------------");
                        e.display();
                        found=true;
                    }
                }
                if(!found)
                {
                    System.out.println("There is no Employee with this name..");
                }
            }
            case 3 ->{
                System.out.print("Enter Designation :");
                String  designation=new Scanner(System.in).nextLine();

                boolean found=false;

                for(Employee e : employees.values())
                {
                    if(e.getDesignation().equals(designation))
                    {
                        System.out.println("---------------------------");
                        e.display();
                        found=true;
                    }
                }
                if(!found)
                {
                    System.out.println("There is no Employee with this designation (only have Ceo/Manager/Programmer/Clerk)...");
                }
            }

            default -> System.out.println("Invalid choice");
                                      
                                        
        };
        

    }

    public static void saveEmployeesToFile(HashMap<Integer, Employee> employees) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.csv"))) 
        {
            
            for (Employee employee : employees.values()) 
            {
                String employeeData = String.join(",",
                        String.valueOf(employee.getEmpId()),
                        employee.getName(),
                        String.valueOf(employee.getAge()),
                        employee.getDesignation(),
                        String.valueOf(employee.getSalary()));
                writer.write(employeeData);
                writer.newLine();  
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error writing employee data to file: " + e.getMessage());
        }
    }

    public static void loadEmployeesFromFile(HashMap<Integer, Employee> employees) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                int id = Integer.parseInt(tokenizer.nextToken().trim());
                String name = tokenizer.nextToken().trim();
                int age = Integer.parseInt(tokenizer.nextToken().trim());
                String designation = tokenizer.nextToken().trim();
                int salary = Integer.parseInt(tokenizer.nextToken().trim());

                Employee employee = createEmployee(id, name, age, designation, salary);
                if (employee != null) 
                {
                    employees.put(id, employee);
                    count++;
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading employee data from file: " + e.getMessage());
        }
    }

    private static Employee createEmployee(int id, String name, int age, String designation, int salary) 
    {
        switch (designation) {
            case "CEO":
                return Ceo.getObject(id, name, age,salary);
            case "Manager":
                return Manager.getObject(id, name, age,salary);
            case "Programmer":
                return Programmer.getObject(id, name, age,salary);
            case "Clerk":
                return Clerk.getObject(id, name, age,salary);
            default:
                return null;
        }
    }
   

   
}

final class Manager extends Employee {
    private Manager(int id, String name, int age,int salary) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(salary);
    }

    public static Manager getObject(int id, String name, int age,int salary)
    {
        return new Manager(id,name,age,salary);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 15000);
    }
}

class Programmer extends Employee {
    private Programmer(int id, String name, int age,int salary) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(salary);
    }
    public static Programmer getObject(int id, String name, int age,int salary)
    {
        return new Programmer(id,name,age,salary);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 5000);
    }
}

class Clerk extends Employee {
    private Clerk(int id, String name, int age,int salary) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(salary);
    }
    
    public static Clerk getObject(int id, String name, int age,int salary)
    {
        return new Clerk(id,name,age,salary);
    }

    public void raiseSalary() 
    {
        int sal = getSalary();
        setSalary(sal + 2000);
    }
}

class Ceo extends Employee{


    private Ceo(int id, String name, int age,int salary)
    {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("CEO");
        setSalary(salary);
    }
    public static Ceo getObject(int id, String name, int age,int salary)
    {
        if(!isCeoCreated)
        {
            isCeoCreated=true;
            return new Ceo(id,name,age,salary);
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

class EmployeeIdSorter implements Comparator<Employee>
{
    public int compare(Employee e1,Employee e2)
    {
        return new Integer(e1.getEmpId()).compareTo(e2.getEmpId());
    }
}
class EmployeeNameSorter implements Comparator<Employee>
{
    public int compare(Employee e1,Employee e2)
    {
        return e1.getName().compareTo(e2.getName());
    }
}
class EmployeeAgeSorter implements Comparator<Employee>
{
    public int compare(Employee e1,Employee e2)
    {
        return new Integer(e1.getAge()).compareTo(e2.getAge());
    }
}
class EmployeeDesignationSorter implements Comparator<Employee>
{
    public int compare(Employee e1,Employee e2)
    {
        return e1.getDesignation().compareTo(e2.getDesignation());
    }
}
class EmployeeSalarySorter implements Comparator<Employee>
{
    public int compare(Employee e1,Employee e2)
    {
        return new Integer(e1.getSalary()).compareTo(e2.getSalary());
    }
}



public class EmployeeManagementApp 
{
    public static void main(String args[]) 
    {
        
        Scanner obj = new Scanner(System.in);
        HashMap<Integer,Employee> employees=new HashMap<>();
        Employee.loadEmployeesFromFile(employees);

        while (true) 
        {
            System.out.println("---------------------------");
            System.out.println("1.Create\n2.Display\n3.Raise Salary\n4.Remove\n5.Search\n6.Exit");
            System.out.println("---------------------------");
            
            int choice=Menu.readChoice(6);
        

            if (choice == 6) 
            {
                System.out.println("\nTotal No.of Employee : " + Employee.count);
                Employee.saveEmployeesToFile(employees);
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

                            if (employee != null) 
                            {
                                employee.getInput(employees);
                                employees.put(employee.getEmpId(),employee);
                                Employee.count++;
                                System.out.println("Employee added successfully");
                                //Employee.saveEmployeesToFile(employees);
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

                    while(true)
                    {
                        System.out.println("---------------------------");
                        System.out.println("1.Display by ID\n2.Display by Name\n3.Display by Age\n4.Display by Designation\n5.Display by Salary\n6.Exit");
                        System.out.println("---------------------------");
                        
                        int subChoice=Menu.readChoice(6);

                        if (subChoice == 6) 
                        {
                            break;
                        }

                        List<Employee> employeeList = new ArrayList<>(employees.values());

                        Comparator<Employee> comparator =switch(subChoice)
                        {
                            case 1 -> new EmployeeIdSorter();
                            case 2 -> new EmployeeNameSorter();
                            case 3 -> new EmployeeAgeSorter();
                            case 4 -> new EmployeeDesignationSorter();
                            case 5 -> new EmployeeSalarySorter();
                            default -> {
                                        System.out.println("Invalid choice, no sorting applied.");
                                        yield null;  
                                        }
                        };

                        if (comparator != null) 
                        {
                            employeeList.sort(comparator);
                            for (Employee e : employeeList) 
                            {
                                System.out.println("---------------------------");
                                e.display();
                                System.out.println("---------------------------");
                            }
                        } else 
                        {
                            for (Employee e : employeeList) 
                            {
                                System.out.println("---------------------------");
                                e.display();
                                System.out.println("---------------------------");
                            }
    
                        }

                    }
                    break;

                    
                }

                case 3: {

                    for(Employee e : employees.values())
                    {
                        e.raiseSalary();
                    }
                    System.out.println("Salary of all employee raised successfully");
                    break;
                }

                case 4: {
                    Employee.removeEmployee(employees);
                    break;
                    
                }

                case 5:
                    {
                        while(true)
                        {
                            System.out.println("---------------------------");
                            System.out.println("1.Search by ID\n2.Search by Name\n3.Search by Designation\n4.Exit");
                            System.out.println("---------------------------");
                        
                        int subChoice=Menu.readChoice(4);

                        if (subChoice == 4) 
                        {
                            break;
                        }
                        
                        Employee.searchEmployee(employees,subChoice);
                        }
                        break;
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

    public static int readEmpId(HashMap<Integer,Employee> employees)
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

    public static int readEmployeeId()
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


