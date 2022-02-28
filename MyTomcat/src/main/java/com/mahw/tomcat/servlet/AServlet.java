package com.mahw.tomcat.servlet;

import com.mahw.tomcat.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("AServlet init");
    }

    @Override
    public void service(InputStream is, OutputStream os) throws IOException {
        //A做的响应
        os.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
        os.write("Content-Type: text/html; charset=utf-8\n".getBytes(StandardCharsets.UTF_8));
        os.write("\n\n".getBytes(StandardCharsets.UTF_8));
        os.write("<html>".getBytes(StandardCharsets.UTF_8));
        os.write("<body>".getBytes(StandardCharsets.UTF_8));
        os.write("<p>This is A servlet </p>".getBytes(StandardCharsets.UTF_8));
        os.write("</body>".getBytes(StandardCharsets.UTF_8));
        os.write("</html>".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void destroy() {
        System.out.println("AServlet destroy");
    }
}
