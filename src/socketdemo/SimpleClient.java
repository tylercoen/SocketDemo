package socketdemo;

import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 5000;

		try (Socket socket = new Socket(host, port)) {
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			String message = "Hello from the client!";
			writer.println(message);

			System.out.println("Message sent to server.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
