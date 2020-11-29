package myservlet.control;
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
      request.setCharacterEncoding("utf-8");
      String dataBase= "jspwork";
      String tableName= request.getParameter("tableName");
      String user= "root";
//      String password= "root";
      boolean boo =( dataBase==null||dataBase.length()==0);
      boo = boo||( tableName==null||tableName.length()==0);
      boo = boo||( user==null||user.length()==0);
      if(boo) {
         fail(request,response,"查询失败");
      }
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
      String uri="jdbc:mysql://localhost:3306/shop?characterEncoding=gb2312&serverTimezone=Asia/Shanghai&useSSL=false";
      try{ 
          con=DriverManager.getConnection(uri,"root","root");
          Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
          ResultSet rs=sql.executeQuery("SELECT * FROM "+tableName);
          ResultSetMetaData metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount(); //得到结果集的列数
          String []columnName = new String[columnCount];
          for(int i=0;i<columnName.length;i++) {
             columnName[i] = metaData.getColumnName(i+1); //得到列名
          }
          recordBean.setColumnName(columnName);   //更新Javabean数据模型
          rs.last();
          int rowNumber=rs.getRow();  //得到记录数
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
          con.close();
     }
     catch(SQLException e){
          System.out.println(e);
     }  
   }
   
   
   
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
	   
	   
	   
	   
	   
	   response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/json; charset=utf-8"); 
		request.setCharacterEncoding("gb2312");
	      String dataBase= "jspwork";
	      String tableName= "user";
	      String user= "root";
	      String password= "root";
	      boolean boo =( dataBase==null||dataBase.length()==0);
	      boo = boo||( tableName==null||tableName.length()==0);
	      if(boo) {
	         fail(request,response,"查询失败");
	      }
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
	      String uri="jdbc:mysql://localhost:3306/jspwork?characterEncoding=gb2312&serverTimezone=Asia/Shanghai&useSSL=false";
	      try{ 
	          con=DriverManager.getConnection(uri,"root","root");
	          Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	                                                ResultSet.CONCUR_READ_ONLY);
	          ResultSet rs=sql.executeQuery("SELECT * FROM "+tableName);
	          ResultSetMetaData metaData = rs.getMetaData();
	          int columnCount = metaData.getColumnCount(); //得到结果集的列数      
	          String []columnName = new String[columnCount];
	          for(int i=0;i<columnName.length;i++) {
	             columnName[i] = metaData.getColumnName(i+1); //得到列名
	             
	          }
	          recordBean.setColumnName(columnName);   //更新Javabean数据模型
	          rs.last();
	          int rowNumber=rs.getRow();  //得到记录数
	          String [][] tableRecord=recordBean.getTableRecord();
	          tableRecord = new String[rowNumber][columnCount];
	          rs.beforeFirst();
	          int i=0;
	          while(rs.next()){
	            for(int k=0;k<columnCount;k++) 
	              tableRecord[i][k] = rs.getString(k+1);
	              i++; 
	          } 
	          for(int l=0;l<tableRecord.length;l++) {
	        	  for(int j=0;j<tableRecord[l].length;j++) {
	        		  System.out.println(tableRecord[l][j]);
	        		  System.out.println(l + "--- " + j);
	        	  }
	          }
	          recordBean.setTableRecord(tableRecord); //更新Javabean数据模型
//	          response.getWriter().print("{\"msg\":\""+ FyJsonUtil.convertObjectToJSON(tableRecord)	  +"\"}"); // 返回对象的json
	          response.getWriter().print(FyJsonUtil.convertObjectToJSON(tableRecord));
	          con.close();
	          
	     }
	     catch(SQLException e){
	          System.out.println(e);
	     }  
	      
	      
   }
   
   
   
   
   
   
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=GB2312");
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
