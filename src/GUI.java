import com.mysql.cj.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {
    private JButton pressToStart;
    private JPanel homePanel;
    private JPanel startPanel;
    private JPanel detailsPanel;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Surgery System");
        frame.setContentPane(new GUI().startPanel);
        frame.setSize(5000,5000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public GUI() {
        SessionManager sessionManager = new SessionManager();
        sessionManager.setUpFactory();

        pressToStart.setFocusPainted(false);


        pressToStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                startPanel.setVisible(false);
                homePanel.setVisible(true);


            }
        });


    }


}
