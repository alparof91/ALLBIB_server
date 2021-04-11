# ALLBIB_server - a library management desktop app

These file represent the server side of the ALLBIB Desktop App which is a client-server application written in Java 8. This component communicates with the client app trough sockets. It's a JPA project and it connects to a local MySql server through Hibernate.
To try it, you must download both the server and client app, setup a MySql server with tables User, Books, Readers. You can look for the table formats in the entity classes from the ALLBIB_client/sample/entity library.
This application was made for educational purposes for my own use. Since I'm a beginner it may contain bad design concepts.
