import java.util.*;
import java.util.stream.*;

class Student
{
    private int rollNo;
    private String name;
    private int age;
    private int standard;
    private String school;
    private String gender;
    private double percentage;
    private Fees fees;

    public Student(int rn,String n,int a,int std,String s,String g,double percent,Fees f)
    {
        rollNo=rn;
        name=n;
        age=a;
        standard=std;
        school=s;
        gender=g;
        percentage=percent;
        fees=f;
    }

    public int getRollNo()
    {
        return rollNo;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public int getStandard()
    {
        return standard;
    }
    public String getSchool()
    {
        return school;
    }
    public String getGender()
    {
        return gender;
    }
    public double getPercentage()
    {
        return percentage;
    }
    public Fees getFees()
    {
        return fees;
    }

}

class Fees 
{
    private int totalFee;
    private int feesPaid;
    private int feesPending;

    public Fees(int totalFee,int feesPaid)
    {
        this.totalFee=totalFee;
        this.feesPaid=feesPaid;
        this.feesPending=totalFee-feesPaid;
    }

    public int getTotalFee()
    {
        return totalFee;
    }

    public int getFeesPaid()
    {
        return feesPaid;
    }

    public int getFeesPending()
    {
        return feesPending;
    }


}

class Students
{
    public static void main(String args[])
    {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Amit", 15, 10, "KV", "Male", 85.5, new Fees(50000, 40000)));
        students.add(new Student(2, "Priya", 16, 11, "DPL", "Female", 78.0, new Fees(55000, 45000)));
        students.add(new Student(3, "Rahul", 14, 9, "Sainik", "Male", 22.0, new Fees(48000, 30000)));
        students.add(new Student(4, "Sneha", 15, 10, "KV", "Female", 90.0, new Fees(52000, 52000)));
        students.add(new Student(5, "Vikram", 16, 11, "DPL", "Male", 67.5, new Fees(56000, 40000)));
        students.add(new Student(6, "Anjali", 17, 12, "Sainik", "Female", 88.5, new Fees(60000, 45000)));
        students.add(new Student(7, "Rohit", 14, 9, "DPL", "Male", 92.0, new Fees(47000, 47000)));
        students.add(new Student(8, "Neha", 15, 10, "DPL", "Female", 65.5, new Fees(49000, 35000)));
        students.add(new Student(9, "Arjun", 16, 11, "Sainik", "Male", 72.5, new Fees(53000, 38000)));
        students.add(new Student(10, "Meena", 14, 9, "KV", "Female", 81.0, new Fees(51000, 41000)));
        students.add(new Student(11, "Suresh", 15, 10, "DPL", "Male", 76.0, new Fees(52000, 42000)));
        students.add(new Student(12, "Pooja", 16, 11, "Sainik", "Female", 33.0, new Fees(50000, 39000)));
        students.add(new Student(13, "Manish", 14, 9, "KV", "Male", 95.5, new Fees(48000, 48000)));
        students.add(new Student(14, "Kavita", 15, 10, "DPL", "Female", 58.0, new Fees(49000, 37000)));
        students.add(new Student(15, "Nikhil", 16, 11, "Sainik", "Male", 85.0, new Fees(55000, 43000)));
        students.add(new Student(16, "Ritu", 17, 12, "KV", "Female", 91.0, new Fees(61000, 46000)));
        students.add(new Student(17, "Ashish", 14, 9, "DPL", "Male", 30.0, new Fees(47000, 36000)));
        students.add(new Student(18, "Divya", 15, 10, "Sainik", "Female", 39.5, new Fees(49000, 34000)));
        students.add(new Student(19, "Rajesh", 16, 11, "KV", "Male", 78.5, new Fees(53000, 39000)));
        students.add(new Student(20, "Swati", 17, 12, "DPL", "Female", 82.5, new Fees(62000, 47000)));


        //1.How many students in each standard

        Map<Integer,Long> m1=students.stream().collect(Collectors.groupingBy(s->s.getStandard(),Collectors.counting()));
       // System.out.println("9th Standard : "+m1.get(9));
       // System.out.println("10th Standard : "+m1.get(10));
       // System.out.println("11th Standard : "+m1.get(11));
       // System.out.println("12th Standard : "+m1.get(12));
        System.out.println(m1);
        System.out.println("-----------------------");

        //2.How many students male & female

        Map<Boolean,Long> m2=students.stream().collect(Collectors.partitioningBy(s->s.getGender().equals("Male"),Collectors.counting()));
        System.out.println("Male Students : "+m2.get(true));
        System.out.println("Female Students : "+m2.get(false));
        System.out.println("-----------------------");

        //3.How many students failed and pass (Whole university)

        Map<Boolean,Long> m3=students.stream().collect(Collectors.partitioningBy(s->s.getPercentage() >= 40.0,Collectors.counting()));
        System.out.println("Passed Students : "+m3.get(true));
        System.out.println("Failed Students : "+m3.get(false));
        System.out.println("-----------------------");

        //4.How many students failed and pass (School wise)

        Map<String,Map<Boolean,Long>> m4=students.stream().collect(Collectors.groupingBy(s->s.getSchool(),Collectors.partitioningBy(s->s.getPercentage() >= 40.0,Collectors.counting())));
        System.out.println("DPL : Pass :" +m4.get("DPL").get(true) +" Fail : "+m4.get("DPL").get(false));
        System.out.println("KV : Pass :" +m4.get("KV").get(true) +" Fail : "+m4.get("KV").get(false));
        System.out.println("Sainik : Pass :" +m4.get("Sainik").get(true) +" Fail : "+m4.get("Sainik").get(false));
        System.out.println("-----------------------");

        //5.Top 3 students in terms of percentage

        List<Student> m5 = students.stream().sorted(Comparator.comparingDouble(s -> -s.getPercentage())).limit(3).collect(Collectors.toList());
        for(Student s : m5)
        {
            System.out.println("Name : "+s.getName());
            System.out.println("Standard : "+s.getStandard());
            System.out.println("School : "+s.getSchool());
            System.out.println("Percentage : "+s.getPercentage());
            System.out.println();
        }
        
        System.out.println("-----------------------");

        //6. Top Scorer school wise

        Map<String, Optional<Student>> m6 = students.stream()
            .collect(Collectors.groupingBy(s -> s.getSchool(), Collectors.maxBy(Comparator.comparingDouble(s -> s.getPercentage()))));
        
        for (Map.Entry<String, Optional<Student>> entry : m6.entrySet()) 
        {
            entry.getValue().ifPresent(s -> {
                System.out.println("Name : " + s.getName());
                System.out.println("Standard : " + s.getStandard());
                System.out.println("School : " + s.getSchool());
                System.out.println("Percentage : " + s.getPercentage());
                System.out.println();
            });
        
        };
        System.out.println("-----------------------");

        //7.Average age of male & female students

        Map<String,Double> m7=students.stream()
            .collect(Collectors.groupingBy(s-> s.getGender(),Collectors.averagingInt(s -> s.getAge())));
        
        System.out.println("Male : "+m7.get("Male"));
        System.out.println("Female : "+m7.get("Female"));
        System.out.println("-----------------------");

        //8.Total fees collection happened school wise

        Map<String,Integer> m8=students.stream()
            .collect(Collectors.groupingBy(s-> s.getSchool(),Collectors.summingInt(s-> s.getFees().getFeesPaid())));
        System.out.println("DPL : "+m8.get("DPL"));
        System.out.println("KV : "+m8.get("KV"));
        System.out.println("Sainik : "+m8.get("Sainik"));
        System.out.println("-----------------------");

        //9.Total fees pending school wise

        Map<String,Integer> m9=students.stream()
            .collect(Collectors.groupingBy(s-> s.getSchool(),Collectors.summingInt(s-> s.getFees().getFeesPending())));
        System.out.println("DPL : "+m9.get("DPL"));
        System.out.println("KV : "+m9.get("KV"));
        System.out.println("Sainik : "+m9.get("Sainik"));
        System.out.println("-----------------------");

        //10.Total no.of students in university
        long totalStudents = students.size();
        System.out.println("Total number of students: " + totalStudents);


    }
}