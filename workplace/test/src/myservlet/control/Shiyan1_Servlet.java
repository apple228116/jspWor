package myservlet.control;
import mybean.data.Shiyan1_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import cn.fyfye.json.FyJsonUtil;
public class Shiyan1_Servlet extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try {  Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch(Exception e){} 
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      request.setCharacterEncoding("UTF-8");
      String username = request.getParameter("username");
      String password= request.getParameter("password");
      String name = request.getParameter("name");
      String Aname = request.getParameter("Aname");
      int play = Integer.parseInt(request.getParameter("play"));
      String tableName = request.getParameter("tableName");
      response.setCharacterEncoding("UTF-8");
      
      HttpSession session=request.getSession(true); 
      Connection con=null; 
      Shiyan1_Bean recordBean=null;
      try{ 
           recordBean=(Shiyan1_Bean)session.getAttribute("recordBean");
           if(recordBean==null){
              recordBean=new Shiyan1_Bean();  //创建Javabean对象
              session.setAttribute("recordBean",recordBean);
           }
      }
      catch(Exception exp){
           recordBean=new Shiyan1_Bean();  
           session.setAttribute("recordBean",recordBean);
      } 
      String uri="jdbc:mysql://127.0.0.1/jspwork?serverTimezone=UTC";
      try{ 
          con=DriverManager.getConnection(uri,"root","root");
          Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
          
          if(play==1) {
        	  System.out.println(play);
        	  String Sql = "SELECT * FROM "+tableName + " where username='"+username+"' and password='"+password+"';";
              System.out.println(Sql);
              ResultSet rs=sql.executeQuery(Sql);
              ResultSetMetaData metaData = rs.getMetaData();
              int columnCount = metaData.getColumnCount(); //得到结果集的列数
              String []columnName = new String[columnCount];
              for(int i=0;i<columnName.length;i++) {
                 columnName[i] = metaData.getColumnName(i+1); //得到列名
              }
              recordBean.setColumnName(columnName);   //更新Javabean数据模型
              rs.last();
              int rowNumber=rs.getRow();  //得到记录数
              if(rowNumber!=0) {
            	  String [][] tableRecord=recordBean.getTableRecord();
                  tableRecord = new String[rowNumber][columnCount];
                  rs.beforeFirst();
                  int i=0;
                  while(rs.next()){
                    for(int k=0;k<columnCount;k++) 
                      tableRecord[i][k] = rs.getString(k+1);
                      i++; 
                  }
                 
                  recordBean.setTableRecord(tableRecord); //更新Javabean数据模型
                  response.getWriter().print(FyJsonUtil.convertObjectToJSON(tableRecord));
              }else {
            	  response.getWriter().write("{\"msg\":\"error\"}");
              }
              con.close();
          }else if(play == 2) {
        	  try {
        		  String condition = "INSERT INTO "+tableName+" VALUES"+
            		      "(0,"+"'"+username+"','"+password+"','"+name+"','"+Aname+"',1)";
            	  System.out.println(condition);
        		  sql.executeUpdate(condition);
            	  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(IOException exp) {
        		  System.out.println("增加数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
        	  
          }else if(play==3) {  //更新
        	  String role = request.getParameter("role");
        	  String uid = request.getParameter("uid");
        	  String condition = "update "+tableName+" set name='"+name+"', role= '"+role
        			  +"' where id="+uid;
        	  System.out.println(condition);
        	  try {
        		  sql.executeUpdate(condition);
        		  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(Exception exp) {
        		  System.out.println("修改数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }else if(play==31) {  //更新社团
        	  String uid = request.getParameter("uid");
        	  String condition = "update "+tableName+" set Aname='"+Aname+"', role=1 where id="+uid;
        	  System.out.println(condition);
        	  try {
        		  sql.executeUpdate(condition);
        		  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(Exception exp) {
        		  System.out.println("修改数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }else if(play==4) {
        	  String uid = request.getParameter("uid");
        	  String condition = "delete from "+tableName+"  where id="+uid;
        	  System.out.println(condition);
        	  try {
        		  sql.executeUpdate(condition);
        		  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(Exception exp) {
        		  System.out.println("修改数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }else if(play == 5) {
        	  System.out.println(play);
        	  String Sql = "SELECT * FROM "+tableName + ";";
              System.out.println(Sql);
              ResultSet rs=sql.executeQuery(Sql);
              ResultSetMetaData metaData = rs.getMetaData();
              int columnCount = metaData.getColumnCount(); //得到结果集的列数
              String []columnName = new String[columnCount];
              for(int i=0;i<columnName.length;i++) {
                 columnName[i] = metaData.getColumnName(i+1); //得到列名
              }
              recordBean.setColumnName(columnName);   //更新Javabean数据模型
              rs.last();
              int rowNumber=rs.getRow();  //得到记录数
              if(rowNumber!=0) {
            	  String [][] tableRecord=recordBean.getTableRecord();
                  tableRecord = new String[rowNumber][columnCount];
                  rs.beforeFirst();
                  int i=0;
                  while(rs.next()){
                    for(int k=0;k<columnCount;k++) 
                      tableRecord[i][k] = rs.getString(k+1);
                      i++; 
                  }
                 
                  recordBean.setTableRecord(tableRecord); //更新Javabean数据模型
                  response.getWriter().print(FyJsonUtil.convertObjectToJSON(tableRecord));
              }else {
            	  response.getWriter().write("{\"msg\":\"error\"}");
              }
              con.close();
          }else if(play == 6) {
        	  System.out.println(play);
        	  String Sql = "SELECT * FROM "+tableName +" where Aname ='"+ Aname + "';";
              System.out.println(Sql);
              ResultSet rs=sql.executeQuery(Sql);
              ResultSetMetaData metaData = rs.getMetaData();
              int columnCount = metaData.getColumnCount(); //得到结果集的列数
              String []columnName = new String[columnCount];
              for(int i=0;i<columnName.length;i++) {
                 columnName[i] = metaData.getColumnName(i+1); //得到列名
              }
              recordBean.setColumnName(columnName);   //更新Javabean数据模型
              rs.last();
              int rowNumber=rs.getRow();  //得到记录数
              if(rowNumber!=0) {
            	  String [][] tableRecord=recordBean.getTableRecord();
                  tableRecord = new String[rowNumber][columnCount];
                  rs.beforeFirst();
                  int i=0;
                  while(rs.next()){
                    for(int k=0;k<columnCount;k++) 
                      tableRecord[i][k] = rs.getString(k+1);
                      i++; 
                  }
                 
                  recordBean.setTableRecord(tableRecord); //更新Javabean数据模型
                  response.getWriter().print(FyJsonUtil.convertObjectToJSON(tableRecord));
              }else {
            	  response.getWriter().write("{\"msg\":\"error\"}");
              }
              con.close();
          }else if(play == 21) {
        	  try {
        		  String uid = request.getParameter("uid");
        		  String condition = "INSERT INTO "+tableName+" VALUES"+
            		      "("+uid+","+"'"+name+"','"+Aname+"',0)";
            	  System.out.println(condition);
        		  sql.executeUpdate(condition);
            	  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(IOException exp) {
        		  System.out.println("增加数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }else if(play == 22) {
        	  try {
        		  String say = request.getParameter("say");
        		  String condition = "INSERT INTO "+tableName+" VALUES"+
            		      "(0,"+"'"+Aname+"','"+name+"','"+say+"')";
            	  System.out.println(condition);
        		  sql.executeUpdate(condition);
            	  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(IOException exp) {
        		  System.out.println("增加数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }else if(play == 23) {
        	  try {
        		  String title = request.getParameter("title");
        		  String go_time = request.getParameter("go_time");
        		  String content = request.getParameter("content");
        		  String condition = "INSERT INTO "+tableName+" VALUES"+
            		      "(0,"+"'"+title+"','"+go_time+"','"+content+"',null,'"+Aname+"')";
            	  System.out.println(condition);
        		  sql.executeUpdate(condition);
            	  response.getWriter().write("{\"msg\":\"0\"}");
        	  }catch(IOException exp) {
        		  System.out.println("增加数据错误！");
        		  response.getWriter().write("{\"msg\":\"1\"}");
        	  }
          }
          
          
     }
     catch(SQLException e){
    //	 response.getWriter().write("{\"msg\":\"error\"}");
    	 System.out.println("错误了！");
     } 
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
       doPost(request,response);
   }
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("返回");
         out.println("<a href =inputDatabase.jsp>输入正确信息</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){} 
  }
}
