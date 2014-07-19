package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("in server");
            Socket s = ss.accept();
            
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            
            String info = br.readLine();
            
            System.out.println("received from client: "+info);
            
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            pw.println("hello, this is server");

            s.close();
            ss.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
