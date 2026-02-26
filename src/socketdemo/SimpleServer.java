package socketdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) {
		int port = 5000;
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server started on port " + port);
			System.out.println("Waiting for client connection...");

			Socket socket = serverSocket.accept();
			System.out.println("Client connected!");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message = reader.readLine();
			System.out.println("Received from client: " + message);

			socket.close();
			System.out.println("Connection closed");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
