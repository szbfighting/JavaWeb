package com.down.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "DownloadServlet",urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要下载的文件
        String filename = request.getParameter("filename");
        //header作用，让浏览器收到这份资源时，以下载的方式提醒用户，而不是直接展示
        response.setHeader("Content-Disposition","attachment;filename="+filename);
        InputStream is =  getServletContext().getResourceAsStream("download/" + filename);
        OutputStream os = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        os.close();
        is.close();
    }
}
