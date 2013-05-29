package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerV01
{
    private String webRoot;
    private int port;
    public MyServerV01(String webRoot, int port)
    {
        this.webRoot = webRoot;
        this.port = port;
    }
    
    public void service()
    {
        ServerSocket ss = null;
        
        try
        {
            ss = new ServerSocket(port);
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
            //ss = new ServerSocket(port,1);
            //System.out.println("in server");
        
        
        while(true)
        {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try
            {
                socket = ss.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                
                Request req = new Request(input);
                
                String filePath = webRoot+req.getURL();

                String htmlContent = readFile(filePath);
                PrintWriter pw = new PrintWriter(output,true);
                pw.println(htmlContent);
                pw.close();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public String readFile(String filePath)
    {
        File f = new File(filePath);
        FileInputStream fis = null;
        StringBuilder res = new StringBuilder("");
        try
        {
            fis = new FileInputStream(f);
            byte[] buffer = new byte[1023];
            int n=0;
            while((n=fis.read(buffer))!=-1)
            {
                String str = new String(buffer,0,n);
                res.append(str);
            }
        } catch (FileNotFoundException e)
        {
            res.append("Page Not Found");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fis!=null)
                    fis.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return res.toString();
    }

    public static void main(String[] args)
    {
        MyServerV01 myHS = new MyServerV01("D:"+File.separator,80);
        myHS.service();
    }
}

class Request
{
    private InputStream input;
    Request(InputStream input)
    {
        this.input = input;
    }
    public String getURL()
    {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(isr);
        String urlPath = null;
        try
        {
            String info = br.readLine();
            System.out.println(info);
            urlPath = info.split("\\s")[1].substring(1);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(br!=null)
                    br.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if(isr!=null)
                    isr.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return urlPath;
    }
}

class Response
{
}