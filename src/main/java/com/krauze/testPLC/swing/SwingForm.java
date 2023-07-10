package com.krauze.testPLC.swing;

import com.krauze.testPLC.PlcConnect;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class SwingForm extends JFrame {
    private JButton setIpButton;
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton readButton;
    private JButton writeButton;
    private JButton connectDiscButton;
    private JTextField statusConnectTextField;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private static int num;

    public SwingForm() {
        this.getContentPane().add(panel);
        this.setIpButton.addActionListener(new SetIpButtonListener());
        this.readButton.addActionListener(new ReadButtonListener());
        this.connectDiscButton.addActionListener(new ConnectDiscButtonListener());

    }

    private class SetIpButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PlcConnect.setIpAddress(textField1.getText());
        }
    }

    private class ReadButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                textField4.setText(PlcConnect.readData(textField3.getText(), textField5.getText()));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class ConnectDiscButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if (PlcConnect.createConnection().isConnected()) {
                statusConnectTextField.setBackground(Color.GREEN);
            } else {
                statusConnectTextField.setBackground(Color.RED);
            }
        }
    }

}
