package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient
{
    public static void main(String[] args)
    {
        try
        {
            Socket s = new Socket("127.0.0.1",9999);

            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            pw.println("hello, this is client");
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String info = br.readLine();
            System.out.println("received from server: "+info);

            s.close();
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
