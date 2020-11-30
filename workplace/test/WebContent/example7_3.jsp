<%@ page contentType="text/html;charset=gb2312" %>
<HTML><body bgcolor=pink ><font size=2>
<form action="insertServlet?dataBase=jspwork&tableName=user" method=post>
添加新记录:
<table border=1>
<tr><td> 用户名：</td><td><Input type="text" value="lubenwei" name="username"></td></tr>
<tr><td>密码：</td><td><Input type="text" value="niubi" name="passowrd"></td></tr>
<tr><td>名称：</td><td><Input type="text" value="卢本伟" name="name"></td></tr>
<tr><td>社团名称：</td><td><Input type="text" value="足球社" name="Aname"></td></tr>
</table>
<br><input type="submit" name="b" value="提交">
</font></body></HTML>
