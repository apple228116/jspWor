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
        //������
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //����json,getParameter()������ʵ�α����json�ļ�����Ӧ
        String name = req.getParameter("name");
        String age = req.getParameter("age");

        //����json,ƴ���ַ�������д��Ҫϸ��
        String result = "{\"name\":\"������\",\"favourite\":\"������\"}";
        resp.getWriter().write(result);
    }
}
