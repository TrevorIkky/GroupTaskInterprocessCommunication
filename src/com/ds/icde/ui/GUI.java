package com.ds.icde.ui;

import com.ds.icde.Config;
import com.ds.icde.client.ClientObject;
import com.ds.icde.client.SocketClient;
import com.ds.icde.server.ResponseObject;

import javax.swing.*;
import java.io.IOException;
import java.util.UUID;

/*
* The GUI
* The first field is enabled to send the student number to the server
* If the server processes this field and returns a 200 response code in the
* response object, then the client keeps track of the successful response counts
* and enables the next field.
* This continuously happens till the server gets all inputs. The send button will prompt
* the user to exit after the server has received all inputs from the client.
* */

public class GUI {
    private JPanel mainPanel;
    private JTextField faculty;
    private JTextField studentNumber;
    private JTextField studentName;
    private JTextField course;
    private JTextField degree;
    private JButton sendRequest;
    private JLabel personalCode;
    private JProgressBar progressBar;
    private JLabel serverMessage;
    private JLabel lName;
    private JLabel lFaculty;
    private JLabel lCourse;
    private JLabel lDegree;
    private SocketClient client;
    private final String randomPersonalCode;
    private boolean shouldExit = false;


    public GUI() {
        startClient();
        serverMessage.setVisible(false);
        progressBar.setVisible(false);
        personalCode.setVisible(false);
        this.randomPersonalCode = UUID.randomUUID().toString().split("-")[0];
        sendRequest.addActionListener(e -> {
            if (!shouldExit)
                chooseDetails();
            else
                System.exit(0);
        });
    }


    private void startClient() {
        try {
            client = new SocketClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void chooseDetails() {
        switch (client.clientProtocol.getResponsesLength()) {
            case 0 -> sendClientDetails(studentNumber.getText());
            case 1 -> sendClientDetails(studentName.getText());
            case 2 -> sendClientDetails(faculty.getText());
            case 3 -> sendClientDetails(course.getText());
            case 4 -> sendClientDetails(degree.getText());
            case 5 -> sendClientDetails(this.randomPersonalCode);
            case 6 -> sendClientDetails(client.clientProtocol.getAllDetails());
        }
    }

    private void sendClientDetails(String field) {
        showProgress();
        ResponseObject response = client.sendRequest(new ClientObject(field, client.clientProtocol.getResponsesLength()));

        serverMessage.setVisible(true);
        serverMessage.setText(response.message);

        int responsesCount = client.clientProtocol.getResponsesLength();


        switch (responsesCount) {
            case 1 -> {
                studentName.setEnabled(true);
                lName.setEnabled(true);
            }
            case 2 -> {
                faculty.setEnabled(true);
                lFaculty.setEnabled(true);
            }
            case 3 -> {
                course.setEnabled(true);
                lCourse.setEnabled(true);
            }

            case 4 -> {
                degree.setEnabled(true);
                lDegree.setEnabled(true);
            }

            case 5 -> serverMessage.setText("SEND personal code as: " + this.randomPersonalCode + "?");
            case 7 -> {
                Timer timer = new Timer(6000, evt -> {
                    serverMessage.setText("Press EXIT to terminate client");
                    shouldExit = true;
                    sendRequest.setText("EXIT");
                });
                timer.setRepeats(false);
                timer.start();
            }
            default -> {
            }
        }
        stopProgress();
    }

    private void showProgress() {
        progressBar.setVisible(true);
        sendRequest.setText("SENDING...");
    }

    private void stopProgress() {
        progressBar.setVisible(false);
        sendRequest.setText("SEND");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("gUI");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
