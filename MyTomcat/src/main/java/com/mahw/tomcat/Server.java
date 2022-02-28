package com.mahw.tomcat;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 实现一个Tomcat的服务端
 */
public class Server {

    private static final String STATIC_WEB_PATH = System.getProperty("user.dir") + "/MyTomcat/webapp/";
    private static final Map<String, String> SERVLET_MAP;

    static {
        SERVLET_MAP = new HashMap<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(System.getProperty("user.dir") + "/MyTomcat/src/main/resources/servlet.properties")));
            Set<Object> objects = properties.keySet();
            for(Object ob : objects){
                String key = (String) ob;
                String value = properties.getProperty(key);
                SERVLET_MAP.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        InputStream is = null;
        OutputStream os = null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                accept = serverSocket.accept();
                is = accept.getInputStream();
                os = accept.getOutputStream();
                //我们要能识别出要访问的文件是哪个
                String name = parse(is, os);
                //判断是否是静态资源
                if (name.contains(".html")) {
                    //根据输入作出响应
                    doResponseStatic(is, os, name);
                } else {
                    doResponseDynamic(is, os, name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
                is = null;
            }
            if (os != null) {
                os.close();
                os = null;
            }
            if (accept != null) {
                accept.close();
                accept = null;
            }
        }
    }

    private static void doResponseDynamic(InputStream is, OutputStream os, String name) {
        String clzName = SERVLET_MAP.get(name.substring(1));
        Class<?> aClass = null;
        Object o = null;
        try {
            aClass = Class.forName(clzName);
            o = aClass.newInstance();
            Method init = aClass.getMethod("init");
            init.invoke(o);
            Method service = aClass.getMethod("service", InputStream.class, OutputStream.class);
            service.invoke(o, is, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(aClass != null && o != null){
                    Method destroy = aClass.getMethod("destroy");
                    destroy.invoke(o);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static String parse(InputStream is, OutputStream os) {
        try {
            byte[] bytes = new byte[1024];
            String input = "";
            while (is.read(bytes) != -1) {
                input = new String(bytes);
                String[] s = input.split(" ");
                return s[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void doResponseStatic(InputStream is, OutputStream os, String name) {
        File file = new File(STATIC_WEB_PATH + name);
        try {
            try {
                InputStream inputStream = new FileInputStream(file);
                byte[] fileContext = new byte[1024];
                while (inputStream.read(fileContext) != -1) {
                    os.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
                    os.write("Content-Type: text/html; charset=utf-8\n".getBytes(StandardCharsets.UTF_8));
                    os.write("\n\n".getBytes(StandardCharsets.UTF_8));
                    os.write(fileContext);
                    os.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    os.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
                    os.write("Content-Type: text/html; charset=utf-8\n".getBytes(StandardCharsets.UTF_8));
                    os.write("\n\n".getBytes(StandardCharsets.UTF_8));
                    os.write("<html>".getBytes(StandardCharsets.UTF_8));
                    os.write("<body>".getBytes(StandardCharsets.UTF_8));
                    os.write("<p>404 NOT FOUND</p>".getBytes(StandardCharsets.UTF_8));
                    os.write("</body>".getBytes(StandardCharsets.UTF_8));
                    os.write("</html>".getBytes(StandardCharsets.UTF_8));
                    os.flush();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}