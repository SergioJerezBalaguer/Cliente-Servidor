package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 4444);
             Scanner scanner = new Scanner(System.in);) {

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            String mensaje = null;
            do {
                System.out.println("Introduzca mensaje:  (Para acabar la ejecucion escriba 'salir')");
                mensaje = scanner.nextLine();
                objectOutputStream.writeObject(mensaje);

                String respuesta = (String) objectInputStream.readObject();
                System.out.println(respuesta);
            } while (!mensaje.equalsIgnoreCase("salir"));

            objectOutputStream.close();
            objectInputStream.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
