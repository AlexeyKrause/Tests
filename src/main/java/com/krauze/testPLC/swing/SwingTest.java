package com.krauze.testPLC.swing;

import javax.swing.*;
import java.awt.*;

public class SwingTest {
    public static void main(String[] args) {
//        JFrame jFrame = getFrame();

        SwingForm swingForm = new SwingForm();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        swingForm.setBounds(dimension.width/2 - 300, dimension.height/2 - 175, 600, 350);

        swingForm.pack();

        swingForm.setSize(new Dimension(600, 350));

        swingForm.setVisible(true);

        swingForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    static JFrame getFrame() {
//        JFrame jFrame = new JFrame() {};
//        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension dimension = toolkit.getScreenSize();
//        jFrame.setBounds(dimension.width/2 - 300, dimension.height/2 - 175, 600, 350);
//        return jFrame;
//    }
}
