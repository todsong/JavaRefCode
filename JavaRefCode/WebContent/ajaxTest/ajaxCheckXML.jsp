<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/xml;charset=utf-8");  
String req = request.getParameter("user");
 if(req!=null&&req.equals("xx"))
    response.getWriter().print("<res><mes>exsited</mes></res>");
else
    response.getWriter().print("<res><mes>ok</mes></res>");
%>