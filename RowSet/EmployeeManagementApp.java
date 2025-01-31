import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;

abstract class Employee 
{
    private int id;
    private String name;
    private int age;
    private int salary;
    private String designation;
    private String department;

    

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

    public void setDepartment(String department)
    {
        this.department=department;
    }

    public String getDepartment()
    {
        return this.department;
    }

    final public static void raiseSalary()
    {
        
        int id=Inputs.readEmpId();
        Database.searchById(id);
        System.out.print("Enter amount to be raise :");
        int amount=new Scanner(System.in).nextInt();

        System.out.print("Do you want to update the salary of this Employee (Y/N) :");
        String choice=new Scanner(System.in).nextLine();
        if(choice.equalsIgnoreCase("Y"))
        {
            Database.updateSalary(id,amount);
        }
        else if (choice.equalsIgnoreCase("N"))
        {
            System.out.println("Updation cancelled....");
        }
        else
        {
            System.out.println("Invalid Option.....");
        }

        

    }

    final public static void display(ResultSet rs) 
    {
        try
        {
            System.out.println("------------------------------------------");
            System.out.println("ID :"+rs.getInt("eid"));
            System.out.println("Name :"+rs.getString("name"));
            System.out.println("Age :"+rs.getInt("age"));
            System.out.println("Salary :"+rs.getInt("salary"));
            System.out.println("Designation :"+rs.getString("designation"));
            System.out.println("Department :"+rs.getString("department"));
            System.out.println("------------------------------------------");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

    public static void removeEmployee() 
    {
        Scanner obj=new Scanner(System.in);
        int id=Inputs.readEmpId();
        Database.searchById(id);

        System.out.print("Do you want to delete this Employee (Y/N) :");
        String choice=obj.nextLine();
        if(choice.equalsIgnoreCase("Y"))
        {
            Database.employeeDelete(id);
        }
        else if (choice.equalsIgnoreCase("N"))
        {
            System.out.println("Deletion cancelled....");
        }
        else
        {
            System.out.println("Invalid Option.....");
        }
            
    }

    
    public void getInput() 
    {

        while(true)
        {
            int id=Inputs.readEmpId();

            if(!Database.checkID(id))
            {
                setEmpId(id);
                setName(Inputs.readName());
                setAge(Inputs.readAge(21,60));
                setSalary(Inputs.readSalary());
                setDepartment(Inputs.readDepartment());
                break;
            }
            else
            {
                System.out.println("Employee Id already taken....!!!");
            }
        }

        
       
        
    }

    public static Employee createObject(int choice) 
    {
        return switch (choice) {
            case 1 -> Ceo.getObject(0, "", 0, 0, "");
            case 2 -> Clerk.getObject(0, "", 0, 0, "");
            case 3 -> Programmer.getObject(0, "", 0, 0, "");
            case 4 -> Manager.getObject(0, "", 0, 0, "");
            case 5 -> Others.getObject(0, "", 0, 0, "");
            default -> throw new IllegalArgumentException("Invalid choice: " + choice);
        };
    }


    public static void searchEmployee(int choice)
    {

        switch(choice)
        {
            case 1 ->{
                int id=Inputs.readEmpId();
                Database.searchById(id);
  
            }
            case 2 ->{
                String  name=Inputs.readName();
                Database.searchByName(name);

            }
            case 3 ->{
                String  designation=Inputs.readDesignation();
                Database.searchByDesignation(designation);
            }

            default -> System.out.println("Invalid choice");
                                      
                                        
        }; 
        

    }
}

final class Manager extends Employee {
    private Manager(int id, String name, int age,int salary,String department) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Manager");
        setSalary(salary);
        setDepartment(department);
    }

    public static Manager getObject(int id, String name, int age,int salary,String department)
    {
        return new Manager(id,name,age,salary,department);
    }

}

class Programmer extends Employee {
    private Programmer(int id, String name, int age,int salary,String department) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Programmer");
        setSalary(salary);
        setDepartment(department);
    }
    public static Programmer getObject(int id, String name, int age,int salary,String department)
    {
        return new Programmer(id,name,age,salary,department);
    }

}

class Clerk extends Employee {
    private Clerk(int id, String name, int age,int salary,String department) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Clerk");
        setSalary(salary);
        setDepartment(department);
    }
    
    public static Clerk getObject(int id, String name, int age,int salary,String department)
    {
        return new Clerk(id,name,age,salary,department);
    }

    
}

class Ceo extends Employee{


    private Ceo(int id, String name, int age,int salary,String department)
    {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("CEO");
        setSalary(salary);
        setDepartment(department);
    }
    public static Ceo getObject(int id, String name, int age,int salary,String department)
    {
        return new Ceo(id,name,age,salary,department);
    }
    
}

class Others extends Employee {
    private Others(int id, String name, int age,int salary,String department) {
        setEmpId(id);
        setName(name);
        setAge(age);
        setDesignation("Others");
        setSalary(salary);
        setDepartment(department);
    }
    
    public static Others getObject(int id, String name, int age,int salary,String department)
    {
        return new Others(id,name,age,salary,department);
    }

    
}


class DatabaseConnection 
{
    private static JdbcRowSet jdbcRowSet;
    
    static 
    {
        try 
        {
            jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRowSet.setUrl("jdbc:postgresql://localhost:5432/employeemanagementapp");
            jdbcRowSet.setUsername("postgres");
            jdbcRowSet.setPassword("tiger");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static JdbcRowSet getConnection() 
    {
        return jdbcRowSet;
    }

    public static void closeConnection()
    {
        try
        {
            jdbcRowSet.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
}

class Database 
{
    private static final JdbcRowSet jdbcRowSet = DatabaseConnection.getConnection();
    
    public static void insertToDb(Employee e) 
    {
        try 
        {
            jdbcRowSet.setCommand("SELECT * FROM employees");
            jdbcRowSet.execute();
            jdbcRowSet.moveToInsertRow();
            jdbcRowSet.updateInt(1, e.getEmpId());
            jdbcRowSet.updateString(2, e.getName());
            jdbcRowSet.updateInt(3, e.getAge());
            jdbcRowSet.updateInt(4, e.getSalary());
            jdbcRowSet.updateString(5, e.getDesignation());
            jdbcRowSet.updateString(6, e.getDepartment());
            jdbcRowSet.insertRow();
        } 
        catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public static void employeeDisplay(String orderBy) 
    {
        String query = "SELECT * FROM employees ORDER BY " + orderBy;
        try {
            jdbcRowSet.setCommand(query);
            jdbcRowSet.execute();

            if (!jdbcRowSet.next()) 
            {
                System.out.println("There is no employee....!!!!");
            } 
            else 
            {
                do {
                    Employee.display(jdbcRowSet);
                } while (jdbcRowSet.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void employeeDelete(int id) 
    {
        try 
        {
            jdbcRowSet.setCommand("DELETE FROM employees WHERE eid = ?");
            jdbcRowSet.setInt(1, id);
            jdbcRowSet.execute();
            System.out.println("Employee removed successfully.....");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchById(int id) 
    {
        try {
            jdbcRowSet.setCommand("SELECT * FROM employees WHERE eid = ?");
            jdbcRowSet.setInt(1, id);
            jdbcRowSet.execute();

            if (!jdbcRowSet.next()) 
            {
                System.out.println("There is no employee....!!!!");
            } 
            else {
                do {
                    Employee.display(jdbcRowSet);
                } while (jdbcRowSet.next());
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchByName(String name) 
    {
        try {
            jdbcRowSet.setCommand("SELECT * FROM employees WHERE name = ?");
            jdbcRowSet.setString(1, name);
            jdbcRowSet.execute();

            if (!jdbcRowSet.next()) 
            {
                System.out.println("There is no employee....!!!!");
            } 
            else 
            {
                do {
                    Employee.display(jdbcRowSet);
                } while (jdbcRowSet.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchByDesignation(String designation) 
    {
        try {
            jdbcRowSet.setCommand("SELECT * FROM employees WHERE designation = ?");
            jdbcRowSet.setString(1, designation);
            jdbcRowSet.execute();

            if (!jdbcRowSet.next()) 
            {
                System.out.println("There is no employee....!!!!");
            } else 
            {
                do {
                    Employee.display(jdbcRowSet);
                } while (jdbcRowSet.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateSalary(int id, int amount) 
    {
        try 
        {
            jdbcRowSet.setCommand("UPDATE employees SET salary = salary + ? WHERE eid = ?");
            jdbcRowSet.setInt(1, amount);
            jdbcRowSet.setInt(2, id);
            jdbcRowSet.execute();
            System.out.println("Employee salary updated successfully.....");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean checkID(int id) 
    {
        try 
        {
            jdbcRowSet.setCommand("SELECT 1 FROM employees WHERE eid = ? LIMIT 1");
            jdbcRowSet.setInt(1, id);
            jdbcRowSet.execute();
            return jdbcRowSet.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}



public class EmployeeManagementApp 
{
    public static void main(String args[]) 
    {
        
        try
        {
            Scanner obj = new Scanner(System.in);
        
        while (true) 
        {
            System.out.println("---------------------------");
            System.out.println("1.Create\n2.Display\n3.Raise Salary\n4.Remove\n5.Search\n6.Exit");
            System.out.println("---------------------------");
            
            int choice=Menu.readChoice(6);
        

            if (choice == 6) 
            {
                
                break;
                
            }

            switch (choice) 
            {
                case 1: 
                {
                    while (true) 
                    {
                        System.out.println("---------------------------");
                        System.out.println("1.CEO\n2.Clerk\n3.Programmer\n4.Manager\n5.Others\n6.Exit");
                        System.out.println("---------------------------");
                        
                        int subChoice=Menu.readChoice(6);

                        if (subChoice == 6) 
                        {
                            break;
                        }

                        try 
                        {
                            Employee employee = Employee.createObject(subChoice);

                            if (employee != null) 
                            {
                                employee.getInput();
                                Database.insertToDb(employee);
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

                        try
                        {
                            switch(subChoice)
                            {
                                case 1 -> Database.employeeDisplay("eid");
                                case 2 -> Database.employeeDisplay("name");
                                case 3 -> Database.employeeDisplay("age");
                                case 4 -> Database.employeeDisplay("designation");
                                case 5 -> Database.employeeDisplay("salary");
                                default -> {
                                            System.out.println("Invalid choice, no sorting applied.");
                                            
                                            }
                            } 

                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        
                        

                    }
                    break;

                    
                }

                case 3: {

                    Employee.raiseSalary();
                    break;
                }

                case 4: {
                    Employee.removeEmployee();
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
                        
                        Employee.searchEmployee(subChoice);
                        }
                        break;
                    }
            }
        }
        obj.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            DatabaseConnection.closeConnection();
        }
        
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

    public static int readEmpId()
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
            catch(Exception e)
            {
                System.out.println("Exception occurs...");
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

    public static int readSalary()
    {
        int salary;
        while(true)
        {
            try
            {
                System.out.print("Enter Salary: ");
                salary=new Scanner(System.in).nextInt();
                if(salary < 15000)
                {
                    System.out.println("Salary must be greater than 15000");
                }
                else
                {
                    return salary;
                }
                
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
    }

    public static String readDepartment()
    {
        System.out.print("Enter Department: ");
        String department=new Scanner(System.in).nextLine();
        return department;
    }

    public static String readDesignation()
    {
        System.out.print("Enter Designation: ");
        String designation=new Scanner(System.in).nextLine();
        return designation;
    }
}


