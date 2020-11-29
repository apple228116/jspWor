package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ji  extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收json,getParameter()方法的实参必须和json的键名对应
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        //返回json,拼接字符串容易写错，要细心
        String result = "{\"name\":\"蔡徐坤\",\"favourite\":\"爱打球\"}";
        resp.getWriter().write(result);
    }
}
