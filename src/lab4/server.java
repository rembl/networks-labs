package lab4;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class server {

    public static void main(String[] args) {
        int port = 12000;
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.out.println("Ошибка при запуске сервера на порту " + port);
            return;
        }
        server.createContext("/", new handler(port));
        server.start();
        System.out.println("Сервер запущен на порту " + port);
    }

}
