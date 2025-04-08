package com.allbib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.allbib.entity.*;
import com.allbib.service.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@FunctionalInterface
interface InputDataHandlerInterface<T> {
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
					return "reader"; // Temporary answer
				} catch (Exception e) {
					e.printStackTrace();
					return e.toString();
				}
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
				AdminService adminService = new AdminService();
				try {
					//convert list to json
					return new Gson().toJson(adminService.getAllAdmins());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchBooks", e -> {
				BookService bookService = new BookService();
				try {
					//convert list to json
					return new Gson().toJson(bookService.getAllBooks());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("addBook", (book) -> {
				Book inputBook = new Gson().fromJson(book, Book.class);
				BookService bookService = new BookService();
				try {
					bookService.addBook(inputBook);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("removeBook", (book) -> {
				Book bookToRemove = new Gson().fromJson(book, Book.class);
				BookService bookService = new BookService();
				try {
					bookService.deleteBook(bookToRemove,bookToRemove.getIdBook());
					System.out.println("Deleting book with ID:" + bookToRemove.getIdBook());
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("updateBook", (book) -> {
				Book modifiedBook = new Gson().fromJson(book, Book.class);
				BookService bookService = new BookService();
				try {
					bookService.updateBook(modifiedBook);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Valid";
			});

			put("fetchReaders", e -> {
				ReaderService readerService = new ReaderService();
				try {
					//convert list to json
					return new Gson().toJson(readerService.getAllReaders());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchReviews", e -> {
				ReviewService reviewService = new ReviewService();
				try {
					//convert list to json
					return new Gson().toJson(reviewService.getAllReviews());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchReviewsForBook", (book) -> {
				Book inputBook = new Gson().fromJson(book, Book.class);
				ReviewService reviewService = new ReviewService();
				try {
					//convert list to json
					Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					return gson.toJson(reviewService.getReviewsForBook(inputBook));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("addReview", (review) -> {
				Review newReview = new Gson().fromJson(review, Review.class);
				ReviewService reviewService = new ReviewService();
				try {
					reviewService.addReview(newReview);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("addRequest", (request) -> {
				Request newRequest = new Gson().fromJson(request, Request.class);
				RequestService requestService = new RequestService();
				try {
					requestService.addRequest(newRequest);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchRequests", e -> {
				RequestService requestService = new RequestService();
				try {
					//convert list to json
					return new Gson().toJson(requestService.getAllRequests());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("removeRequest", (request) -> {
				Request requestToRemove = new Gson().fromJson(request, Request.class);
				RequestService requestService = new RequestService();
				try {
					requestService.deleteRequest(requestToRemove, requestToRemove.getIdRequest());
					System.out.println("Deleting request with ID:" + requestToRemove.getIdRequest());
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchBookLogs", e -> {
				BookLogService bookLogService = new BookLogService();
				try {
					//convert list to json
					return new Gson().toJson(bookLogService.getAllBookLogs());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("addBookLog", (bookLog) -> {
				BookLog inputBookLog = new Gson().fromJson(bookLog, BookLog.class);
				BookLogService bookLogService = new BookLogService();
				try {
					bookLogService.addBookLog(inputBookLog);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchBookLogsForBook", (book) -> {
				Book inputBook = new Gson().fromJson(book, Book.class);
				BookLogService bookLogService = new BookLogService();
				try {
					//convert list to json
					Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					return gson.toJson(bookLogService.getBookLogsForBook(inputBook));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

            put("fetchGivenBooks", e -> {
                GivenBookService givenBookService = new GivenBookService();
                try {
                    //convert list to json
                    return new Gson().toJson(givenBookService.getAllGivenBooks());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return "Invalid";
            });

			put("addGivenBook", (givenBook) -> {
				GivenBook inputGivenBook = new Gson().fromJson(givenBook, GivenBook.class);
				GivenBookService givenBookService = new GivenBookService();
				try {
					givenBookService.addGivenBook(inputGivenBook);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("removeGivenBook", (givenBook) -> {
				GivenBook givenBookToRemove = new Gson().fromJson(givenBook, GivenBook.class);
				GivenBookService givenBookService = new GivenBookService();
				try {
					givenBookService.deleteGivenBook(givenBookToRemove, givenBookToRemove.getIdGivenBook());
					System.out.println("Deleting givenBook with ID:" + givenBookToRemove.getIdGivenBook());
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("addNotification", (notification) -> {
				Notification inputNotification = new Gson().fromJson(notification, Notification.class);
				NotificationService notificationService = new NotificationService();
				try {
					notificationService.addNotification(inputNotification);
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("removeNotification", (notification) -> {
				Notification notificationToRemove = new Gson().fromJson(notification, Notification.class);
				NotificationService notificationService = new NotificationService();
				try {
					notificationService.deleteNotification(notificationToRemove, notificationToRemove.getIdNotification());
					System.out.println("Deleting notification with ID:" + notificationToRemove.getIdNotification());
					return "Valid";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchNotifications", e -> {
				NotificationService notificationService = new NotificationService();
				try {
					//convert list to json
					return new Gson().toJson(notificationService.getAllNotifications());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchNotificationsForBook", (book) -> {
				Book inputBook = new Gson().fromJson(book, Book.class);
				NotificationService notificationService = new NotificationService();
				try {
					//convert list to json
					Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					return gson.toJson(notificationService.getNotificationsForBook(inputBook));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return "Invalid";
			});

			put("fetchNotificationsForUser", (username) -> {
				String inputUser = new Gson().fromJson(username, String.class);
				NotificationService notificationService = new NotificationService();
				try {
					//convert list to json
//					Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					return new Gson().toJson(notificationService.getNotificationsForUsername(inputUser));
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
			try (Socket socket = serverSocket.accept(); // Waits for a client
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
