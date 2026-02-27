package socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClientServer {

	public static void main(String[] args) {
		int port = 5000;

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server started on port " + port);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket);

				// Create new thread for each client
				new ClientHandler(clientSocket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class ClientHandler extends Thread {
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);) {
			writer.println("Welcome! Type 'exit' to quit.");

			String message;

			while ((message = reader.readLine()) != null) {
				System.out.println("Received from client: " + message);

				if (message.equalsIgnoreCase("exit")) {
					writer.println("Goodbye!");
					break;
				}

				// Echo back to client
				writer.println("Server received: " + message);
			}

			socket.close();
			System.out.println("Client disconnected");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
