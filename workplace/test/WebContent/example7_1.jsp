<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="java.sql.*" %>
<HTML><body bgcolor=#EEDDFF>
 <% Connection con;
    Statement sql; 
    ResultSet rs;
    try{  Class.forName("com.mysql.jdbc.Driver");
    }
    catch(Exception e){
       out.println("���ǰ�MySQL���ݿ��JDBC-���ݿ����������Ƶ�JDK����չĿ¼��");
    }
    try { String uri= "jdbc:mysql://127.0.0.1/jspwork?serverTimezone=UTC";
          String user="root";
          String password="root";
          con=DriverManager.getConnection(uri,user,password);
          //Ҳ����д��con=DriverManager.getConnection(uri+"?user=root&password=");
          sql=con.createStatement();
          rs=sql.executeQuery("SELECT * FROM user ");
          out.print("<table border=2>");
          out.print("<tr>");
            out.print("<th width=100>"+"�û���");
            out.print("<th width=100>"+"����");
            out.print("<th width=50>"+"����");
          out.print("</TR>");
          while(rs.next()){
            out.print("<tr>");
              out.print("<td >"+rs.getString(1)+"</td>"); 
              out.print("<td >"+rs.getString(2)+"</td>");
              out.print("<td >"+rs.getString("Aname")+"</td>");
            out.print("</tr>") ; 
          }
          out.print("</table>");
          con.close();
    }
    catch(SQLException e){ 
          out.print(e);
    }
 %>
</body></HTML>
