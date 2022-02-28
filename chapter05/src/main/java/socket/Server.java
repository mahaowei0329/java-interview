package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 实现一个简单的服务端
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        InputStream is = null;
        OutputStream os = null;
        Socket accept = null;
        try {
            serverSocket = new ServerSocket(8080);
            while(true){
                accept = serverSocket.accept();
                os = accept.getOutputStream();
                os.write("HTTP/1.1 200 OK\n".getBytes(StandardCharsets.UTF_8));
                os.write("Content-Type: text/html; charset=utf-8\n".getBytes(StandardCharsets.UTF_8));
                os.write("\n\n".getBytes(StandardCharsets.UTF_8));
                os.write("<html>".getBytes(StandardCharsets.UTF_8));
                os.write("<body>".getBytes(StandardCharsets.UTF_8));
                os.write("<p>This is a web</p>".getBytes(StandardCharsets.UTF_8));
                os.write("</body>".getBytes(StandardCharsets.UTF_8));
                os.write("</html>".getBytes(StandardCharsets.UTF_8));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is != null){
                is.close();
                is = null;
            }
            if(os != null){
                os.close();
                os = null;
            }
            if(accept != null){
                accept.close();
                accept = null;
            }
        }
    }
}