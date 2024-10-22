import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleChatServer {
    private final List<PrintWriter> pw = new ArrayList<PrintWriter>();//构建一个内容为PrintWriter对象的列表。
    public static void main(String[] args) throws IOException {
        com.data.send.SimpleChatServer server = new com.data.send.SimpleChatServer();
        server.go();
    }
    public void go() throws IOException {
        try {
            ServerSocketChannel serversocketChannel = ServerSocketChannel.open();
            serversocketChannel.socket().bind(new InetSocketAddress(8080));//建一个ServerSocketChannel并且绑定一个端口。
            SocketChannel socketChannel = serversocketChannel.accept();//新的SocketChannel可以和客户端通信
            while (true) {
               Reader reader = Channels.newReader(socketChannel, "UTF-8");
               BufferedReader bufferedReader = new BufferedReader(reader);
                // 在go中新建一个BufferedReader对象
                String msg = bufferedReader.readLine();
                tellEveryone("收到信息", bufferedReader);
                if ("bye".equals(msg)) {
                        break;
                }
                System.out.println(msg);
            }
            while (true) {
                Writer writer = Channels.newWriter(socketChannel, "UTF-8");
                PrintWriter writer1 = new PrintWriter(writer);
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                String s = "你好你好！";
                if("bye".equals(line)) {
                    break;
                }
                writer1.write(s);
                writer1.write("收到信息");
                writer1.write(line);
                writer1.flush();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        //让服务器运行起来。为了方便操作，在该方法中同时体现 tellEveryOne方法
    }
    private void tellEveryone(String message,BufferedReader bufferedReader) throws IOException {
        String s = bufferedReader.readLine();//将上个方法的对象传递过来。
    }
    public class ClientHandler implements Runnable {
        private SocketChannel socketChannel;//定义一个控制类

        @Override
        public void run() {
            System.out.println("Hello! I am running!");//运行函数。
        }
    }
    public void Read() throws IOException {

    }
}
