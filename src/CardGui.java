import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardGui {
    private JPanel home;
    private JPanel panel2;
    private JPanel panel1;
    private JButton button1;
    private JComboBox comboBox1;
    private JButton button2;

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

        $$$setupUI$$$();
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

    public void setUp() {
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        home.setLayout(new CardLayout(0, 0));
        panel1 = new JPanel();
        panel1.setLayout(new CardLayout(0, 0));
        home.add(panel1, "Card1");
        button1 = new JButton();
        button1.setText("Button");
        panel1.add(button1, "Card1");
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("sads");
        comboBox1.setModel(defaultComboBoxModel1);
        panel1.add(comboBox1, "Card2");
        button2 = new JButton();
        button2.setText("Button");
        panel1.add(button2, "Card3");
        panel2 = new JPanel();
        panel2.setLayout(new CardLayout(0, 0));
        panel2.setBackground(new Color(-917501));
        home.add(panel2, "Card2");
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return home;
    }
}
