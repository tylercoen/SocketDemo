package socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiClientClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 5000;

		try (Socket socket = new Socket(host, port);
				BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				Scanner scanner = new Scanner(System.in);) {

			// Thread to listen for server message
			new Thread(() -> {
				try {
					String serverMessage;
					while ((serverMessage = serverReader.readLine()) != null) {
						System.out.println("Sever: " + serverMessage);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();

			// Main thread handles user input

			while (true) {
				String userInput = scanner.nextLine();
				writer.println(userInput);

				if (userInput.equalsIgnoreCase("exit")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
