<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function loadXMLDoc()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	return xmlhttp;
}
function check()
{
	var xmlhttp = loadXMLDoc();
	if(xmlhttp)
	{
     var url = "ajaxCheck.jsp";
     var data = "user="+document.getElementById("user").value;
	  xmlhttp.open("post",url,true);
	  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  xmlhttp.send(data);
	  xmlhttp.onreadystatechange=function()
	  {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	      document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
	    }
      }
	}
}

</script>
</head>
<body>
<form action="" method="post">
  <input type="text" name="user" id="user" >
  <input type="button" value="check" onclick="check()">
  <a id="myDiv"></a>
  <br>
  <input type="text" name="email" >
  <br>
  <input type="button" value="submit" >
</form>
</body>
</html>