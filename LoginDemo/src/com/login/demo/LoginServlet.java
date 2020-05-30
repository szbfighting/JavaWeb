package com.login.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username = "+username+"\n"+"password = "+password);
        //2.校验数据
        PrintWriter writer = resp.getWriter();
        if ("songzebin".equals(username)&&"123456".equals(password)){
            //writer.write("login success...");
            Object obj = getServletContext().getAttribute("count");
            int totalCount = 0;
            if (obj!=null){
                totalCount = (int)obj;
            }
            getServletContext().setAttribute("count",totalCount+1);
            resp.setStatus(302);
            //定位跳转到的位置是哪一个页面
            resp.setHeader("Location","login_success.html");
        }else {
            writer.write("login failed...");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
