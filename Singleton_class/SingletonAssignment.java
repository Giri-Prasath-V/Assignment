class A
{
    static private A a1=null;
    A()
    {
        if(this instanceof B)
        {
            System.out.println("B instance created...");
        }
        else if(this instanceof A && a1==null)
        {
            a1=this;
            System.out.println("A instance created....");
        }
        else
        {
            throw new RuntimeException("Cannot create more than one instance");
        }

    }
    public static A getObject()
    {
        if(a1==null)
        {
            a1=new A();
        }
        
        return a1;
    }
}

final class B extends A
{
    static private B b1=new B();
    private B()
    {
    
    }
    public static B getObject()
    {
       
        return b1;
    }
}

class SingletonAssignment
{
    public static void main(String args[])
    {
        try
        {
            
           // A a1=A.getObject();
            A a=new A();
            B b1=B.getObject();

           // A a=new A();
           // System.out.println(a1);
           System.out.println(a);
           System.out.println(b1);

            A a2=A.getObject();
            System.out.println(a2);

            A a3=new A();
            System.out.println(a3);

            //B b2=B.getObject();
        }
        catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
