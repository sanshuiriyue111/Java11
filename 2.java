import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SympleChatClientA {
    public static void main(String[] args) throws IOException {
        SympleChatClientA client = new SympleChatClientA();
        client.go();//相当于同时调用了SetupNetWork和SendMessage方法
    }
    public void go() throws IOException {
        SetupNetWork();
    }
    private void SetupNetWork() throws IOException {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8080);
        SocketChannel socketChannel = SocketChannel.open(address);//open a SocketChannel to the Server;
        Writer writer1 = Channels.newWriter(socketChannel, "UTF-8");
        PrintWriter writer = new PrintWriter(writer1);
        SendMessage(writer);
        //为了方便，直接在该方法中call出 SendMessage 方法。
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            if(message.equals("bye")) {
                break;
            }
            writer.println(message);
            writer.flush();
        }
        while(true) {
            Reader reader = Channels.newReader(socketChannel, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String message = bufferedReader.readLine();
            if("bye".equals(message)) {
                break;
            }
            System.out.println(message);
            bufferedReader.close();

        }
        //成功建了一个Printer 并且赋值writer
    }
    private void SendMessage(PrintWriter writer) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message1 = scanner.nextLine();
        writer.write(message1);


    }

}
