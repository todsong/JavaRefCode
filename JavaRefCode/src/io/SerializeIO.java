package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializeIO
{
    public static void main(String[] args)
    {
        mySerialize();
        myDeserialize();
    }
    
    public static void mySerialize()
    {
        File f = new File("D:"+File.separator+"1.txt");
        ObjectOutputStream oos = null;
        OutputStream out = null;
        try
        {
            out = new FileOutputStream(f);
            oos = new ObjectOutputStream(out);
            oos.writeObject(new Person("aaa",123));
            
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                oos.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try
            {
                out.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public static void myDeserialize()
    {
        File f = new File("D:"+File.separator+"1.txt");
        ObjectInputStream ois = null;
        InputStream in = null;
        try
        {
            in = new FileInputStream(f);
            ois = new ObjectInputStream(in);
            Object obj = ois.readObject();
            System.out.println(obj.toString());
            
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ois.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Person implements Serializable
{
    private static final long serialVersionUID = 4911899814118879158L;
    private String name;
    private int age;
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    public String toString()
    {
        return name+","+age;
    }
    
}