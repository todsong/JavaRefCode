package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyBuffered
{
    public static void main(String[] args)
    {
        BufferedReader br = null;
        FileReader ft = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try
        {
            ft = new FileReader("D:\\1.txt");
            br = new BufferedReader(ft);
            
            fw = new FileWriter("D:\\4.txt");
            bw = new BufferedWriter(fw); 
            
            String str = null;
            while((str=br.readLine())!=null)
            {
                System.out.println(str);
                bw.write(str+"\n");
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
                bw.close();
                fw.close();
                br.close();
                ft.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
