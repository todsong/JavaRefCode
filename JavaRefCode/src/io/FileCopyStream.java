package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyStream
{
    public static void main(String[] args)
    {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        try
        {
            fis = new FileInputStream("D:\\1.txt");
            fos = new FileOutputStream("D:\\2.txt");
            
            byte[] buffer = new byte[1024];
            
            int n=0;
            while((n=fis.read(buffer))!=-1)
            {
                fos.write(buffer);
            }
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
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
                fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
    }
}
