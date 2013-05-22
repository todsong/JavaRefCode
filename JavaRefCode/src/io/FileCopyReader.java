package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyReader
{
    public static void main(String[] args)
    {
        FileReader fr = null;
        FileWriter fw = null;
        
        try
        {
            fr = new FileReader("D:\\1.txt");
            fw = new FileWriter("D:\\3.txt");
            
            char[] cbuf = new char[1024];
            int n=0;
            while((n=fr.read(cbuf))!=-1)
            {
                String str = new String(cbuf,0,n);
                System.out.println(str);
                fw.write(cbuf,0,n);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fw.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try
            {
                fr.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
