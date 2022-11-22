package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    public void startServer() {
        try (final var serverSocket = new ServerSocket(9999)) {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ExecutorService executorService = Executors.newFixedThreadPool(60);
                ConnectionHandler connectionHandler = new ConnectionHandler(socket);
                executorService.submit(connectionHandler);
                System.out.println("Server has started");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}


