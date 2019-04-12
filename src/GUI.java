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
    private JTextArea welcome;
    private JButton button1;


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
                setDetailsPanel();


            }
        });


    }

    public void setDetailsPanel()
    {
        welcome = new JTextArea(100,50);

        detailsPanel.setVisible(true);
        detailsPanel.add(welcome);
        welcome.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
