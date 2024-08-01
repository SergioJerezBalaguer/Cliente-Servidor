package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(4444);) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado");

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            String mensaje = null;
            do {
                mensaje = (String) objectInputStream.readObject();
                System.out.println("Mensaje del cliente: " + mensaje);
                objectOutputStream.writeObject("Respuesta del servidor: " + mensaje.toUpperCase());
            } while (!mensaje.equalsIgnoreCase("salir"));

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
