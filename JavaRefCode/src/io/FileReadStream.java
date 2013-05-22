package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReadStream
{
    public static void main(String[] args)
    {
        File f = new File("D:\\1.txt");
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(f);
            byte[] buffer = new byte[1023];
            int n=0;
            while((n=fis.read(buffer))!=-1)
            {
                String str = new String(buffer,0,n);
                System.out.print(str);
            }
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
                fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
    }
}
