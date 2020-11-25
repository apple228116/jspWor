<%@ page contentType="text/html;charset=gb2312" %>
<jsp:useBean id="resultBean" class="myservlet.control.Shiyan1_Bean" scope="request" />
<table border=1>
 <tr><td>¼¦ÄãÌ«ÃÀ</td></tr>
  <%
      String [] columnName=resultBean.getColumnName();
  %>
  	  <tr>
  <%
  	  for(String s:columnName){
  %>
  	  <th><%= s %></th>
  <%
  	  }
  %>
  </tr>
  <%
      String [][]record=resultBean.getTableRecord();
      for(int i=0;i<record.length;i++){
   %>
   <tr>
   <%
   	for(int j=0;j<record[i].length;j++){
   %>
   <td><%= record[i][j] %></td>
   <%
   	}
   %>
   </tr>
   <%
   }
   %>
 </table>
</font></body></HTML>  
