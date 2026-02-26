package socketdemo;

import java.io.BufferReader;
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

			BufferReader reader = new BufferReader(new InputStreamReader(socket.getInputStream()));

			String message = reader.readline();
			System.out.println("Received from client: " + message);

			socket.close();
			System.out.println("Connection closed");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
