package com.testcompany;

import com.google.gson.Gson;
import com.testcompany.entity.Books;
import com.testcompany.entity.User;
import com.testcompany.service.AdminsService;
import com.testcompany.service.BooksService;
import com.testcompany.service.ReadersService;
import com.testcompany.service.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@FunctionalInterface
interface InputDataHandlerInterface<T> {
	// abstract method
	String executeTask(T inputData);
}

public class CommandService implements Runnable {

	private final ServerSocket serverSocket;

	// store one lambda for each command
	private static final HashMap<String, InputDataHandlerInterface<String>> map = new HashMap<String, InputDataHandlerInterface<String>>() {
		//The serialVersionUID is a universal version identifier for a Serializable class.
		//Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object.
		//If no match is found, then an InvalidClassException is thrown.
		private static final long serialVersionUID = 1L;

		//Anonymous Subclass to Initialize the HashMap
		{
			put("login", (user) -> {
				System.out.println("Trying to login...");
				User inputUser = new Gson().fromJson(user, User.class);
				UserService userService = new UserService();
				try {
					userService.findUser(inputUser.getUsername(),inputUser.getPassword());
					return "Valid credentials";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid credentials";
			});

			put("addUser", (user) -> {
				User inputUser = new Gson().fromJson(user, User.class);
				UserService userService = new UserService();
				try {
					userService.addUser(inputUser);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("modifyUser", (user) -> {
				User modifiedUser = new Gson().fromJson(user, User.class);
				UserService userService = new UserService();
				try {
					userService.updateUser(modifiedUser);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Valid";
			});

			put("fetchAdmins", e -> {
				AdminsService adminsService = new AdminsService();
				try {
					//convert list to json
					return new Gson().toJson(adminsService.getAllAdmins());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchBooks", e -> {
				BooksService booksService = new BooksService();
				try {
					//convert list to json
					return new Gson().toJson(booksService.getAllBooks());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("addBook", (book) -> {
				Books inputBook = new Gson().fromJson(book, Books.class);
				BooksService booksService = new BooksService();
				try {
					booksService.addBook(inputBook);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("removeBook", (bookId) -> {
				int id = Integer.parseInt(bookId);
				BooksService booksService = new BooksService();
				try {
					booksService.deleteBook(id);
					System.out.println("Deleting book with ID:" + id);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchReaders", e -> {
				ReadersService readersService = new ReadersService();
				try {
					//convert list to json
					return new Gson().toJson(readersService.getAllReaders());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});
		}
	};

	public CommandService(int port) throws IOException {
		// Create server socket and set the timeout for serverSocket.accept method
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(250);
	}

	@Override
	public void run() {
		try {
			accept();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void accept() throws IOException {
		System.out.println("Accepting connections on port " + serverSocket.getLocalPort());

		// Loop until the thread is interrupted
		while (!Thread.interrupted()) {
			// Use a try-with resources to instantiate the client socket and
			// buffers for reading and writing messages from and to the client
			try (Socket socket = serverSocket.accept();
				 BufferedWriter bufferedOutputWriter = new BufferedWriter(
				 		new OutputStreamWriter(socket.getOutputStream()));
				 BufferedReader bufferedInputReader = new BufferedReader(
				 		new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)))
			{
				System.out.println("\n\n\nConnection accepted");

				// Read the command and data from the client
				String receivedCommand = bufferedInputReader.readLine();
				String receivedData = bufferedInputReader.readLine();

				System.out.println("Command received:  " + receivedCommand);
				System.out.println("Data received:  " + receivedData);

				if (map.get(receivedCommand)==null) {
					System.out.println("Unexpected command!");
					outputToClient(bufferedOutputWriter, "Unexpected command!", true);
				}
				else {
					String result = map.get(receivedCommand).executeTask(receivedData);
					System.out.println("Sending back to client: " + result);
					outputToClient(bufferedOutputWriter, result, true);
				}

			} catch (SocketTimeoutException ste) {
				// timeout every .25 seconds to see if interrupted
			}
		}

		System.out.println("server connection end");
	}

	// Helper methods to send data to client
	private void outputToClient(BufferedWriter bufferedOutputWriter, String message, boolean withNewLine)
			throws IOException {

		bufferedOutputWriter.write(message);

		if (withNewLine) {
			bufferedOutputWriter.newLine();
		}

		bufferedOutputWriter.flush();
	}
}
