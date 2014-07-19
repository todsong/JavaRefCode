package factory;

public class EagerSingleton
{
    private int a;
    private String b;
    
    private static EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton()
    {
    }
    
    public static EagerSingleton getInstance()
    {
        return instance;
    }
    
    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }

    public String getB()
    {
        return b;
    }

    public void setB(String b)
    {
        this.b = b;
    }
    public static void main(String[] args)
    {
        EagerSingleton e = EagerSingleton.getInstance();
        e.setA(1);
        e.setB("xx");
        System.out.println(EagerSingleton.getInstance().getB());
    }

}
