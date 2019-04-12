import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardGui {
    private JPanel home;
    private JPanel panel2;
    private JPanel panel1;
    private JButton button1;

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        CardGui demo = new CardGui();
        demo.createUIComponents();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public CardGui() {

        setUp();
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                panel1.setVisible(false);
                panel2.setVisible(true);
                button1.setVisible(false);
            }
        });
    }

    public void setUp(){
        button1 = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel1.setVisible(true);
    }

    private void createUIComponents() {
       home = new JPanel(new CardLayout());
       home.add(panel1);
       home.add(panel2);
    }
}
