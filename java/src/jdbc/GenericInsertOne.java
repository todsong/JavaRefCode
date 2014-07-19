package jdbc;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class GenericInsertOne
{
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String passwd = "sys";

    public static InsertResp insert(Object obj)
    {

        InsertResp resp = new InsertResp();
        resp.setResult("ok");
        Connection conn = null;
        PreparedStatement st = null;
        StringBuilder sql = new StringBuilder("insert into ");

        Class clazz = obj.getClass();
        String tableName = clazz.getSimpleName();
        sql.append(tableName).append("(");

        Field[] fields = clazz.getDeclaredFields();

        int fieldCount = 0;
        for (Field f : fields)
        {
            if (fieldCount != 0)
            {
                sql.append(",");
            }
            String name = f.getName();
            sql.append(name);
            fieldCount++;
        }
        sql.append(") values(");

        for (int i = 0; i < fieldCount; i++)
        {
            if (i != 0)
                sql.append(",");
            sql.append("?");
        }
        sql.append(")");

        System.out.println(sql);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            st = conn.prepareStatement(sql.toString());

            AccessibleObject.setAccessible(fields, true);
            int count = 1;
            for (Field f : fields)
            {
                String paraName = f.getName();
                try
                {
                    Class t = f.getType();
                    if (t.isPrimitive() || t == String.class)
                    {
                        st.setObject(count, f.get(obj));
                    } else
                    {
                        if (t == java.util.Date.class)
                        {
                            java.util.Date uDate = (java.util.Date) f.get(obj);
                            st.setObject(count,
                                    new java.sql.Date(uDate.getTime()));
                        }
                    }
                    count++;
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            st.execute();
            conn.commit();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            resp.setResult("error");
        } catch (SQLException e)
        {
            e.printStackTrace();
            resp.setResult("error");
        }
        finally
        {
            return resp;   
        }
    }

    public static void main(String[] args)
    {
        T_Test pojoTest = new T_Test();
        pojoTest.setA(0);
        pojoTest.setB(1.2);
        pojoTest.setC("ttt");
        pojoTest.setD(new Date());
        pojoTest.setE(true);
        InsertResp resp = GenericInsertOne.insert(pojoTest);
        System.out.println(resp.getResult());
    }
}

class T_Test
{
    private int a;
    private double b;
    private String c;
    private Date d;
    private boolean e;

    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }

    public double getB()
    {
        return b;
    }

    public void setB(double b)
    {
        this.b = b;
    }

    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    public Date getD()
    {
        return d;
    }

    public void setD(Date d)
    {
        this.d = d;
    }

    public boolean isE()
    {
        return e;
    }

    public void setE(boolean e)
    {
        this.e = e;
    }
}

class InsertResp
{
    private String result;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
}