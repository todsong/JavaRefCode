package factory;

public class LazySingleton
{

    private int a;
    private String b;
    private static LazySingleton instance;
    private LazySingleton()
    {
    }
    
    public synchronized static LazySingleton getInstance()
    {
        if(instance==null)
        {
            instance = new LazySingleton();
        }
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
        LazySingleton e = LazySingleton.getInstance();
        e.setA(1);
        e.setB("xx");
        System.out.println(LazySingleton.getInstance().getB());
        System.out.println(LazySingleton.getInstance().getB());
    }
    
}
