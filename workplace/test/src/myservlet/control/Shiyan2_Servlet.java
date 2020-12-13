package myservlet.control;
import mybean.data.Example7_2_Bean; //��������2�е�Javabeanģ��
import mybean.data.Shiyan1_Bean;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import cn.fyfye.json.FyJsonUtil;

import java.util.Calendar;
public class Shiyan2_Servlet extends HttpServlet{
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
	              recordBean=new Shiyan1_Bean();  //����Javabean����
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
	              int columnCount = metaData.getColumnCount(); //�õ������������
	              String []columnName = new String[columnCount];
	              for(int i=0;i<columnName.length;i++) {
	                 columnName[i] = metaData.getColumnName(i+1); //�õ�����
	              }
	              recordBean.setColumnName(columnName);   //����Javabean����ģ��
	              rs.last();
	              int rowNumber=rs.getRow();  //�õ���¼��
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
	                 
	                  recordBean.setTableRecord(tableRecord); //����Javabean����ģ��
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
	        		  System.out.println("�������ݴ���");
	        		  response.getWriter().write("{\"msg\":\"1\"}");
	        	  }
	        	  
	          }
	          
	          
	     }
	     catch(SQLException e){
	    //	 response.getWriter().write("{\"msg\":\"error\"}");
	    	 System.out.println("�����ˣ�");
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
	         out.println("����");
	         out.println("<a href =inputDatabase.jsp>������ȷ��Ϣ</a>");
	         out.println("</body></html>");
	        }
	        catch(IOException exp){} 
	  }
}
