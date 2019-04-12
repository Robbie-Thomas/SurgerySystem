import com.github.lgooddatepicker.components.DatePicker;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class surgeryGUI extends JFrame implements ActionListener{

    JPanel cards, type, home, admin, doctor, patient, adminPatient, adminAppointment, adminStaff, createPatientPnl, updatePatientPnl, deletePatientPnl, viewPatientPnl;
    private final static String HOME_PANEL = "Home Panel";
    private final static String YOU_ARE = "You are?";
    private final static String DOCTOR = "Doctor";
    private final static String ADMIN = "Admin";
    private final static String PATIENT = "Patient";
    private final static String ADMINPATIENT = "Manage patients";
    private final static String ADMINAPP = "Manage Appointments";
    private final static String ADMINSTAFF = "Manage Staff";
    private final static String CREATEPATIENT = "Create patient";
    private final static String VIEWPATIENT = "View patient";
    private final static String UPDATEPATIENT = "Update patient";
    private final static String DELETEPATIENT = "Delete Patient";
    private JButton beginBtn, backBtn, adminBtn, doctorBtn, patientBtn, managePatient, manageAppointment, manageStaff, createPatient, viewPatient, updatePatient, deletePatient;
    private JTextArea DoctorType;
    SessionManager sM = new SessionManager();

    @SuppressWarnings("Duplicates")
    public void addComponentToPane(Container pane) {

       // sM.setUpFactory();

        backBtn = new JButton("Back");
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.previous(cards);
            }
        });

        home = new JPanel();
        home.setLayout(new GridBagLayout());
        home.setMinimumSize(new Dimension(1000,1000));
        beginBtn = new JButton("Click to begin");
        beginBtn.setFont(new Font("Arial", Font.BOLD, 48));
        beginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });
        beginBtn.setMargin( new Insets(500, 500, 500, 500) );
        beginBtn.setFocusPainted(false);
        home.add(beginBtn);




        type = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        type.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        DoctorType = new JTextArea("You are?");
        DoctorType.setMargin( new Insets(100, 500, 100, 500));
        DoctorType.setFont(new Font("Arial", Font.BOLD, 48));
        DoctorType.setEditable(false);
        type.add(DoctorType, c);
        c.weightx = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        adminBtn = new JButton("Admin");
        adminBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        doctorBtn = new JButton("Doctor");
        doctorBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, DOCTOR);
            }
        });
        patientBtn = new JButton("Patient");
        patientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, PATIENT);
            }
        });
        type.add(adminBtn,c);
        type.add(doctorBtn,c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        type.add(patientBtn,c);





        admin = new JPanel();

        GridBagConstraints g = new GridBagConstraints();
        admin.setLayout(new GridBagLayout());

        g.anchor = GridBagConstraints.NORTH;
        g.weighty = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 4;
        g.weightx = 1;
        manageAppointment = new JButton("Manage Appointment");
        manageAppointment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPP);
            }
        });
        managePatient = new JButton("Manage patient");
        managePatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINPATIENT);
            }
        });
        manageStaff = new JButton("Manage Staff");
        manageStaff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINSTAFF);
            }
        });
        admin.add(backBtn, g);
        admin.add(managePatient, g);
        admin.add(manageAppointment, g);
        admin.add(manageStaff, g);


        doctor = new JPanel();
        GridBagConstraints gb = new GridBagConstraints();
        doctor.setLayout(new GridBagLayout());

        patient = new JPanel();
        GridBagConstraints gbag = new GridBagConstraints();
        patient.setLayout(new GridBagLayout());


        //Admin manage patient
        adminPatient = new JPanel();
        GridBagConstraints gbag2 = new GridBagConstraints();
        adminPatient.setLayout(new GridBagLayout());
        createPatient = new JButton("Create Patient");
        createPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, CREATEPATIENT);
            }
        });
        viewPatient = new JButton("View Patient");
        viewPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, VIEWPATIENT);
            }
        });
        updatePatient = new JButton("Update a patient");
        updatePatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, UPDATEPATIENT);
            }
        });
        deletePatient = new JButton("Delete a patient");
        deletePatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, DELETEPATIENT);

            }
        });
        adminPatient.add(createPatient, gbag2);
        adminPatient.add(viewPatient,gbag2);
        adminPatient.add(updatePatient, gbag2);
        adminPatient.add(deletePatient, gbag2);
        adminPatient.add(backBtn, gbag2);






        adminAppointment = new JPanel();

        adminStaff = new JPanel();

        createPatientPnl = new JPanel();
        JTextArea dtls = new JTextArea("Please enter patients details");
        dtls.setEditable(false);
        DatePicker datePicker = new DatePicker();
        datePicker.getDateStringOrEmptyString();
        createPatientPnl.add(datePicker);
        createPatientPnl.add(dtls);




        updatePatientPnl = new JPanel();

        deletePatientPnl = new JPanel();

        viewPatientPnl = new JPanel();


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.setSize(1920,1080);
        cards.setBackground(Color.red);
        cards.add(home, HOME_PANEL);
        cards.add(type, YOU_ARE);
        cards.add(admin,ADMIN);
        cards.add(doctor,DOCTOR);
        cards.add(patient,PATIENT);
        cards.add(adminPatient,ADMINPATIENT);
        cards.add(adminAppointment,ADMINAPP);
        cards.add(adminStaff,ADMINSTAFF);
        cards.add(createPatientPnl, CREATEPATIENT);
        cards.add(updatePatientPnl, UPDATEPATIENT);
        cards.add(deletePatientPnl, DELETEPATIENT);
        cards.add(viewPatientPnl, VIEWPATIENT);

        pane.add(cards, BorderLayout.CENTER);

    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.next(cards);
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setMinimumSize(new Dimension(1000,1000));
        //Create and set up the content pane.
        surgeryGUI demo = new surgeryGUI();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}