# GroupTaskInterprocessCommunication
Inter-process communication using Java Sockets group task

## Getting the project running
Run the `SocketServer.java` class under `src/server`. This runs the server on `localhost` port `1234`<br>


Run the `GUI.java` class under `src/UI/GUI` A GUI forms open prompting for details <br>

## Using the GUI

1. Add the **#** (student number) and click send
2. The **Student Name** text field is enabled after receiving the server response.
3. Continue adding details until **Degree**.
4. After sending the **degree** the program automatically generates a personal token id, the status text displays the id.
5. Click send to send this token to the server.
6. The server accepts the id and prompts the user to send all details back. Click send.
7. The server replies with the count of the details received.
8. The send button text changes to exit.
9. Click on the `exit` button to end the client program
