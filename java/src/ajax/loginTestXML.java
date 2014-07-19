package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginTestXML extends HttpServlet
{
    private static final long serialVersionUID = 8827076544015662768L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    {
        response.setContentType("text/xml;charset=utf-8");  
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
        } catch (IOException e)
        {

            e.printStackTrace();
        }  
        String buffer = "<res><mes>exsited</mes></res>";  
        out.println(buffer);
    }
}
