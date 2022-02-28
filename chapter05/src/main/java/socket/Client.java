package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 模拟浏览器发出请求
 */
public class Client {

    /**
     * 1. 构建socket
     * 2. 拿到input
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            socket = new Socket("localhost", 8080);
            os = socket.getOutputStream();
            os.write("GET / HTTP/1.1\n".getBytes(StandardCharsets.UTF_8));
            os.write("Host: localhost\n".getBytes(StandardCharsets.UTF_8));
            os.write("\n".getBytes(StandardCharsets.UTF_8));

            is = socket.getInputStream();
            byte[] b = new byte[1024];
            while(is.read(b) != -1){
                System.out.println(new String(b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                is.close();
                is = null;
            }
            if(os != null){
                os.close();
                os = null;
            }
            if(socket != null){
                socket.close();
                socket = null;
            }
        }
    }

}
