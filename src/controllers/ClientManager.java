package controllers;

import views.ScreenManager;
import views.screens.ServerScreen;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientManager {

    private static ClientManager instance;
    private List<Socket> clients;

    private ClientManager() {
        clients = new ArrayList<>();
    }

    public static ClientManager getInstance() {
        if (instance == null)
            instance = new ClientManager();
        return instance;
    }

    public void addClient(Socket socket) {
        this.clients.add(socket);

        // Recebendo Mensagens dos clientes
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(socket.getInputStream());

                while (scanner.hasNextLine()) {
                    if (ScreenManager.getInstance().getCurrentScreen() instanceof ServerScreen) {

                        String text = scanner.nextLine();

                        ((ServerScreen) ScreenManager.getInstance().getCurrentScreen()).setTextAreaLog(text);

                        // System.out.println(text);

                        sendToAll(socket, text);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendToAll(Socket socket, String text) {
        new Thread(() -> {
            for (Socket client : clients) {

                if (!client.equals(socket)) {
                    PrintStream out;
                    try {
                        out = new PrintStream(client.getOutputStream());
                        out.println(text);
                    } catch (Exception ignored) {

                    }
                }
            }

        }).start();
    }

    public List<Socket> getClients() {
        return this.clients;
    }

}
