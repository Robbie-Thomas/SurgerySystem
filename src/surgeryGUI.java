import com.github.lgooddatepicker.components.DatePicker;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import static java.util.stream.Collectors.toList;

public class surgeryGUI extends JFrame implements ActionListener{

     private JPanel cards, type, home, admin, staffPanel, patientPanel, adminPatient, adminAppointment, adminStaff, createPatientPnl, updatePatientPnl,
            deletePatientPnl, viewPatientPnl,  patientSelectAppPanel,  addPhonePanel, addAddressPanel, addEmailPanel, staffLoggedInPnl, adminAppCreate,
    adminAppView, adminAppDelete, AdminAppUpdate;
    private JScrollPane scrollPane, scrollPane1, scrollPane2, scrollPane3;
    private final static String HOME_PANEL = "Home Panel";
    private final static String YOU_ARE = "You are?";
    private final static String DOCTOR = "Doctor";
    private final static String ADMIN = "Admin";
    private final static String STAFF_LOGGED_IN = "Staff area";
    private final static String PATIENT = "Patient";
    private final static String ADMINPATIENT = "Manage patients";
    private final static String ADMINAPP = "Manage Appointments";
    private final static String ADMINAPPCREATE = "Create Appointments";
    private final static String ADMINAPPDELETE = "Delete Appointments";
    private final static String ADMINAPPVIEW = "View Appointments";
    private final static String ADMINAPPUPDATE = "Update Appointments";
    private final static String ADMINSTAFF = "Manage Staff";
    private final static String CREATEPATIENT = "Create patientPanel";
    private final static String VIEWPATIENT = "View patientPanel";
    private final static String UPDATEPATIENT = "Update patientPanel";
    private final static String DELETEPATIENT = "Delete Patient";
    private final static String PATIENTAPPVIEW = "View selected appointment";
    private final static String PATIENTADDRESS = "Add a patients address";
    private final static String PATIENTPHONE = "Add a patients phone number";
    private final static String PATIENTEMAIL = "Add a patients email address";
    private JButton beginBtn, backBtn, adminBtn, doctorBtn, patientBtn, managePatientBtn, manageAppointmentBtn, manageStaffBtn, createPatientBtn, viewPatientBtn, updatePatientBtn, deletePatientBtn;
    private JTextArea DoctorType;
    SessionManager sM = new SessionManager();
    private int columnNum= 0;
    private List<Appointment> appointmentByName;
    private List<Appointment> appointmentByDate;
    private List<Appointment> common;
    private JTable appointmentTable, patientTable, patientTable2;
    private Integer staffID;
    private Boolean hasClicked = false;



    @SuppressWarnings("Duplicates")
    public void addComponentToPane(Container pane) {

       sM.setUpFactory();
       sM.setUpSession();

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

     //Admin manage patientPanel



        adminStaff = new JPanel();

        createTypePanel();
        createAdminPanel();
        createAdminPatientPanel();
        adminCreatePatientPnl();
        createPatientPanelFindPatient();
        createStaffPanel();
        createViewPatientPanel();
        addAddressPanel();
        addPhonePanel();
        addEmailPanel();
        createAdminAppointmentPanel();
        createAdminAppointmentCreatePanel();
        createAdminAppointmentViewPanel();
        createAdminAppointmentUpdatePanel();
        createAdminAppointmentDeletePanel();


        updatePatientPnl = new JPanel();

        deletePatientPnl = new JPanel();




        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.setSize(1920,1080);
        cards.setBackground(Color.red);
        cards.add(home, HOME_PANEL);
        cards.add(type, YOU_ARE);
        cards.add(admin,ADMIN);
        cards.add(staffPanel,DOCTOR);
        cards.add(patientPanel,PATIENT);
        cards.add(adminPatient,ADMINPATIENT);
        cards.add(adminAppointment,ADMINAPP);
        cards.add(adminStaff,ADMINSTAFF);
        cards.add(createPatientPnl, CREATEPATIENT);
        cards.add(updatePatientPnl, UPDATEPATIENT);
        cards.add(deletePatientPnl, DELETEPATIENT);
        cards.add(viewPatientPnl, VIEWPATIENT);
        cards.add(addAddressPanel, PATIENTADDRESS);
        cards.add(addPhonePanel, PATIENTPHONE);
        cards.add(addEmailPanel, PATIENTEMAIL);
        cards.add(adminAppCreate, ADMINAPPCREATE);
        cards.add(adminAppDelete, ADMINAPPDELETE);
        cards.add(adminAppView, ADMINAPPVIEW);
        cards.add(AdminAppUpdate, ADMINAPPUPDATE);


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


    @SuppressWarnings("Duplicates")
    public void createAdminAppointmentCreatePanel(){
        adminAppCreate = new JPanel();
        adminAppCreate.add(new JTextArea("Create appointment"));
        JButton existingPatient = new JButton("Existing Patient");
        JButton newPatient = new JButton("New Patient");
        JTextArea first = new JTextArea("First name",1,10);
        JTextArea last = new JTextArea("Last name",1,10);
        JButton fetchPatient = new JButton("Fetch Patient");
        first.setVisible(false);
        last.setVisible(false);
        fetchPatient.setVisible(false);
        createPatientTable(fetchPatient, adminAppCreate, first,last);
        existingPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                newPatient.setVisible(false);
                first.setVisible(true);
                last.setVisible(true);
                fetchPatient.setVisible(true);
                adminAppCreate.add(first);
                adminAppCreate.add(last);
                adminAppCreate.add(fetchPatient);
            }
        });
        newPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, CREATEPATIENT);

            }
        });
        /*DefaultTableModel listTableModel;
        Object[] columnNames = {"First Name", "Last name", "Sex", "Email address", "DOB", "Medical Information", "Select Patient"};
        Object[][] data = {};
        listTableModel = new DefaultTableModel(data, columnNames);

        fetchPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
                System.out.println(patients.size());
                List<PatientEmailAddress> patientEmailAddresses = sM.getEmailsByName(first.getText(), last.getText());
                String emailAddress = "";
                for (int i = 0; i < patientEmailAddresses.size(); i++) {
                    emailAddress += "\n" + patientEmailAddresses.get(i).getEmailAddress();
                }
                for (int i = 0; i < patients.size(); i++) {
                    Patient patient = patients.get(i);
                    String isMale = "Male";
                    if (patient.isMale() == false) {
                        isMale = "Female";
                    }
                    listTableModel.addRow(new Object[]{patient.getFirstName(), patient.getLastName(), isMale, emailAddress, patient.getDob(), patient.getMedicalInformation(), "Select Patient"});
                    System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                // scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("test");
                    }
                };

                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                adminAppCreate.add(scrollPane2);
            }

        });*/
        adminAppCreate.add(existingPatient);
        adminAppCreate.add(newPatient);
    }

    public void createAdminAppointmentViewPanel(){
        adminAppView = new JPanel();
        JButton button = new JButton("Get Appointment for Patient");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        adminAppView.setLayout(new GridBagLayout());
        JTextArea first = new JTextArea("First name",1,10);
        JTextArea last = new JTextArea("Last name",1,10);
        adminAppView.add(first);
        adminAppView.add(last);
        adminAppView.add(button);
        createAppointmentTable(button,first, last, gridBagConstraints, adminAppView);

    }
    public void createAdminAppointmentUpdatePanel(){
        AdminAppUpdate = new JPanel();
    }
    public void createAdminAppointmentDeletePanel(){
        adminAppDelete = new JPanel();
    }

    @SuppressWarnings("Duplicates")
    public void createAdminAppointmentPanel(){
        adminAppointment = new JPanel();
        GridBagConstraints gbag2 = new GridBagConstraints();
        adminAppointment.setLayout(new GridBagLayout());
         JButton createAppBtn = new JButton("Create Appointment");
        createAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPPCREATE);
            }
        });
        JButton viewAppBtn = new JButton("View Appointment");
        viewAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPPVIEW);
            }
        });
        JButton UpdateAppBtn = new JButton("Update an Appointment");
        UpdateAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPPUPDATE);
            }
        });
        JButton deleteAppBtn = new JButton("Delete an Appointment");
        deleteAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPPDELETE);

            }
        });
        adminAppointment.add(createAppBtn, gbag2);
        adminAppointment.add(viewAppBtn,gbag2);
        adminAppointment.add(UpdateAppBtn, gbag2);
        adminAppointment.add(deleteAppBtn, gbag2);
        adminAppointment.add(backBtn, gbag2);
    }

    @SuppressWarnings("Duplicates")
    public void createViewPatientPanel(){
        GridBagConstraints gbag = new GridBagConstraints();
        viewPatientPnl = new JPanel();
        viewPatientPnl.setLayout(new GridBagLayout());
        JTextArea enter = new JTextArea("Please enter your first and last name");
        enter.setEditable(false);
        JTextArea first = new JTextArea("firstName", 1, 20);
        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");
            }
        });
        JTextArea last = new JTextArea("LastName", 1,20);
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        JButton button = new JButton("Fetch patients");
        createPatientTable(button, viewPatientPnl, first, last);
        /*DefaultTableModel listTableModel1;
        Object[] columnNames= {"First Name", "Middle Names", "Last name", "Sex", "DOB","Email ", "Medical Information", "Select Patient"};
        Object[][] data = {};
        listTableModel1 = new DefaultTableModel(data, columnNames);
        patientTable = new JTable(listTableModel1);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
                List<PatientEmailAddress> patientEmailAddresses = sM.getEmailsByName(first.getText(), last.getText());
                String emailAddress= "";
                for(int i = 0; i < patientEmailAddresses.size(); i++){
                    emailAddress += patientEmailAddresses.get(i).getEmailAddress();
                }
                for(int i = 0; i < patients.size(); i++){
                    Patient patient= patients.get(i);
                    String isMale = "Male";
                    if(patient.isMale() != false)
                    {
                        isMale= "Female";
                    }
                    String middle = "";
                    if(patient.getMiddleName() != null)
                    {
                        middle = patient.getMiddleName();
                    }
                    listTableModel1.addRow(new Object[]{patient.getFirstName(), middle , patient.getLastName(), isMale , patient.getDob(), emailAddress , patient.getMedicalInformation()  , "Select Patient"});
                    System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel1);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setVisible(true);
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("test");
                    }
                };

                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, action, 7);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                viewPatientPnl.add(scrollPane2);
            }
        });*/

        gbag.gridy = 0;
        viewPatientPnl.add(first, gbag);
        gbag.gridy = 1;
        viewPatientPnl.add(last, gbag);
        gbag.gridx = 2;
        viewPatientPnl.add(button, gbag);



    }

    @SuppressWarnings("Duplicates")
    public void createPatientPanelFindPatient(){
        patientPanel = new JPanel();
        GridBagConstraints gbag = new GridBagConstraints();

        patientPanel.setLayout(new GridBagLayout());
        JTextArea enter = new JTextArea("Please enter your first and last name");
        enter.setEditable(false);
        JTextArea first = new JTextArea("firstName",1,10);


        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");

            }
        });

        JTextArea last = new JTextArea("LastName",1,10);
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        JButton getApp = new JButton("getApp");
        gbag.gridx = 0;
        gbag.gridy = 0;
        patientPanel.add(enter, gbag);
        gbag.gridy = 1;
        patientPanel.add(first, gbag);
        gbag.gridy = 2;
        patientPanel.add(last, gbag);
        gbag.gridy = 3;
        patientPanel.add(getApp, gbag);
        getApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                appointmentByName = sM.getAppointmentByName(first.getText(), last.getText());
                appointmentByDate = sM.getAppointmentByDate(LocalDate.now());System.out.println(appointmentByName);
                common = appointmentByDate.stream().filter(appointmentByName::contains).collect(toList());
                //List<Appointment> collect = appointmentByName.stream().filter(appointment -> appointmentByDate.contains(appointment)).collect(toList());
                Object[] columnNames= {"First Name", "Last name","Title", "Staff's Name", "Time", "Date", "Select"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);

                for(int i = 0; i < common.size(); i++){
                    String time = common.get(i).getAppointmentTime().toLocalTime().toString();
                    System.out.println(time);
                    String nurseDocName = "";
                    String Title = "Nurse";
                    //if(common.get(i).getStaff().getDoctor() == null){System.out.println(common.get(i).getStaff().getNurse().getLastName());}
                    if(common.get(i).getStaff().getDoctor() == null) nurseDocName = common.get(i).getStaff().getNurse().getLastName();
                    else{
                        nurseDocName = common.get(i).getStaff().getDoctor().getLastName();
                        Title = "Doctor";
                    }
                        listTableModel.addRow(new Object[]{common.get(i).getPatient().getFirstName(), common.get(i).getPatient().getLastName(), Title, nurseDocName, common.get(i).getAppointmentTime().toLocalTime().minusHours(1), common.get(i).getAppointmentDate().toString(), "Select Appointment"});

                    //patientPanel.add(appointmentTable, gbag);
                    patientPanel.revalidate();
                }
                appointmentTable = new JTable(listTableModel);
                //appointmentTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRender());

                gbag.gridx = 7;
                gbag.gridy = 0;
                JScrollPane scrollPane = new JScrollPane(appointmentTable);
                scrollPane.setBounds(1000,1000,1500,1000);
                scrollPane.setVisible(true);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("test");
                        JTable table = (JTable)e.getSource();
                        int modelRow = Integer.valueOf( e.getActionCommand() );
                        ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1);
                       //System.out.println(((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1));
                        columnNum = table.getSelectedRow();
                        System.out.println(table.getSelectedRow());
                        CreatePatientSelectAppPanel();
                        cards.add(patientSelectAppPanel, PATIENTAPPVIEW);
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.show(cards, PATIENTAPPVIEW);
                        patientPanel.remove(appointmentTable);
                        patientPanel.revalidate();
                        patientPanel.repaint();
                        JTextArea jTextArea = new JTextArea();
                        jTextArea.setVisible(false);
                        scrollPane.setViewportView(jTextArea);
                        scrollPane.setVisible(false);
                        first.setText("First name");
                        last.setText("Last name");

                    }
                };


                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                if(common.size() == 0){
                    JOptionPane.showMessageDialog(null, "Patient " +first.getText() + " " + last.getText() + " Does not have an appointment today","No appointment", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    patientPanel.add(scrollPane, gbag);
                }
            }
        });


    }

    @SuppressWarnings("Duplicates")
    public void CreatePatientSelectAppPanel(){
        patientSelectAppPanel = new JPanel();
        Appointment appointment = common.get(columnNum);
        Patient patient = appointment.getPatient();
        String nurseDocName = "";
        String title = "Nurse ";
        if(common.get(columnNum).getStaff().getDoctor() == null) nurseDocName = common.get(columnNum).getStaff().getNurse().getLastName();
        else{
            nurseDocName = common.get(columnNum).getStaff().getDoctor().getLastName();
            title = "Doctor ";
        }
        JTextArea appDetails = new JTextArea("Patient " + patient.getFirstName() +" "+ patient.getLastName() +" with "+ title + nurseDocName + " on "+appointment.getAppointmentDate() + " at " +appointment.getAppointmentTime().toLocalTime().minusHours(1));
        JButton checkIn = new JButton("Check In");
        checkIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Staff staff = appointment.getStaff();
                String nurseDocName = "";
                String title = "Nurse ";
                if(staff.getDoctor() == null) nurseDocName = staff.getNurse().getLastName();
                else{
                    nurseDocName = staff.getDoctor().getLastName();
                    title = "Doctor ";
                }
                int input = JOptionPane.showOptionDialog(null,"Checked in for your appointment at " + appointment.getAppointmentTime().toLocalTime().minusHours(1) + " with " + title + nurseDocName,"check in",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,null, null,null);
                if(input == JOptionPane.OK_OPTION)
                {
                    sM.updateAppointmentCheckedIn(appointment.getId(),true);
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, PATIENT);
                    patientPanel.remove(appointmentTable);
                    patientPanel.revalidate();
                }
                else{
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.show(cards, PATIENT);
                    patientPanel.remove(appointmentTable);
                    patientPanel.revalidate();
                    JOptionPane.showMessageDialog(null,"Returning you to patient area without checking in");
                }
            }
        });
        patientSelectAppPanel.add(appDetails);
        patientSelectAppPanel.add(checkIn);
    }



    public void createStaffPanel(){
        JTextField staffId = new JTextField("Please enter your staff Id number");
        JTextArea staffIdIn = new JTextArea("Enter here",1,20);

        staffPanel = new JPanel();
        staffPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                staffIdIn.setText("Enter here");
            }
        });
        GridBagConstraints gb = new GridBagConstraints();
        staffPanel.setLayout(new GridBagLayout());

        staffIdIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                staffIdIn.setText("");
            }
        });
        JButton go = new JButton("Go");
        go.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Staff staff = new Staff();
                staff = sM.getStaff(Integer.parseInt(staffIdIn.getText()));
                if (staff == null) {
                    JOptionPane.showMessageDialog(null, "No staff with that ID");

                }else{
                System.out.println(staff.getId());
                staffID = Integer.parseInt(staffIdIn.getText());
                createStaffLoggedInPanel();
                cards.add(staffLoggedInPnl, STAFF_LOGGED_IN);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, STAFF_LOGGED_IN);
                }
            }

    });
        staffPanel.add(staffId);
        staffPanel.add(staffIdIn);
        staffPanel.add(go);
    }


    @SuppressWarnings("Duplicates")
    public void createStaffLoggedInPanel(){
        Staff staff = sM.getStaff(staffID);
        String nurseDocName = "";
        String title = "Nurse ";
        if(staff.getDoctor() == null) nurseDocName = staff.getNurse().getLastName();
        else{
            nurseDocName = staff.getDoctor().getLastName();
            title = "Doctor ";
        }
        staffLoggedInPnl = new JPanel();
        GridBagConstraints gbag = new GridBagConstraints();
        staffLoggedInPnl.setLayout(new GridBagLayout());
        JTextArea name = new JTextArea(title + nurseDocName);
        name.setEditable(false);
        JButton viewApps = new JButton("View today's appointments");
        JButton viewApps2 = new JButton("View week's appointments");
        JButton viewApps3 = new JButton("Manage Appointment");

        viewApps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] columnNames= {"First Name", "Last name", "Medical Information", "Time", "Date",};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);
                List<Appointment> appointments = sM.getAppointmentFromStaffId(staffID);
                List<Appointment> appointmentlistToday = sM.getAppointmentByDate(LocalDate.now());
                common = appointmentlistToday.stream().filter(appointments::contains).collect(toList());
                    for(int i = 0; i < common.size(); i++)
                    {
                        listTableModel.addRow(new Object[]{common.get(i).getPatient().getFirstName(), common.get(i).getPatient().getLastName(), common.get(i).getPatient().getMedicalInformation(),
                                common.get(i).getAppointmentTime().toLocalTime().minusHours(1), common.get(i).getAppointmentDate().toString()});
                    }
                appointmentTable = new JTable(listTableModel);
                scrollPane2 = new JScrollPane(appointmentTable);
                scrollPane2.setSize(1000,1000);
                scrollPane2.setVisible(true);

                gbag.gridy = 3;
                staffLoggedInPnl.add(scrollPane2, gbag);
                staffLoggedInPnl.repaint();
                staffLoggedInPnl.revalidate();
                scrollPane3.removeAll();
                if(hasClicked)staffLoggedInPnl.remove(scrollPane3);
                viewApps.setEnabled(false);
                viewApps2.setEnabled(true);
                hasClicked = true;

            }
        });


        viewApps2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] columnNames= {"First Name", "Last name", "Medical Information", "Time", "Date",};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);
                List<Appointment> appointments = sM.getAppointmentFromStaffId(staffID);
                List<Appointment> appointmentListToday = sM.getAppointmentByDateWeek(LocalDate.now());
                common = appointmentListToday.stream().filter(appointments::contains).collect(toList());
                for(int i = 0; i < common.size(); i++)
                {
                    listTableModel.addRow(new Object[]{common.get(i).getPatient().getFirstName(), common.get(i).getPatient().getLastName(), common.get(i).getPatient().getMedicalInformation(),
                            common.get(i).getAppointmentTime().toLocalTime().minusHours(1), common.get(i).getAppointmentDate().toString()});
                }
                appointmentTable = new JTable(listTableModel);
                appointmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                appointmentTable.getColumnModel().getColumn(2).setWidth(30);
                scrollPane3 = new JScrollPane(appointmentTable);
                scrollPane3.setSize(1000,1000);
                scrollPane3.setVisible(true);
                gbag.gridy = 3;
                staffLoggedInPnl.add(scrollPane3, gbag);
                viewApps2.setEnabled(false);
                viewApps.setEnabled(true);
                scrollPane2.removeAll();
                staffLoggedInPnl.repaint();
                staffLoggedInPnl.revalidate();
                if(hasClicked)staffLoggedInPnl.remove(scrollPane2);
                hasClicked = true;

            }
        });
        staffLoggedInPnl.add(name);
        staffLoggedInPnl.add(viewApps);
        staffLoggedInPnl.add(viewApps2);
        staffLoggedInPnl.add(viewApps3);
    }

    @SuppressWarnings("Duplicates")
    public void createTypePanel(){
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
    }

    @SuppressWarnings("Duplicates")
    public void createAdminPanel(){
        admin = new JPanel();

        GridBagConstraints g = new GridBagConstraints();
        admin.setLayout(new GridBagLayout());

        g.anchor = GridBagConstraints.NORTH;
        g.weighty = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 4;
        g.weightx = 1;
        manageAppointmentBtn = new JButton("Manage Appointment");
        manageAppointmentBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINAPP);
            }
        });
        managePatientBtn = new JButton("Manage patientPanel");
        managePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINPATIENT);
            }
        });
        manageStaffBtn = new JButton("Manage Staff");
        manageStaffBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINSTAFF);
            }
        });
        admin.add(backBtn, g);
        admin.add(managePatientBtn, g);
        admin.add(manageAppointmentBtn, g);
        admin.add(manageStaffBtn, g);
    }

    @SuppressWarnings("Duplicates")
    public void createAdminPatientPanel(){
        adminPatient = new JPanel();
        GridBagConstraints gbag2 = new GridBagConstraints();
        adminPatient.setLayout(new GridBagLayout());
        createPatientBtn = new JButton("Create Patient");
        createPatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, CREATEPATIENT);
            }
        });
        viewPatientBtn = new JButton("View Patient");
        viewPatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, VIEWPATIENT);
            }
        });
        updatePatientBtn = new JButton("Update a patientPanel");
        updatePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, UPDATEPATIENT);
            }
        });
        deletePatientBtn = new JButton("Delete a patientPanel");
        deletePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, DELETEPATIENT);

            }
        });
        adminPatient.add(createPatientBtn, gbag2);
        adminPatient.add(viewPatientBtn,gbag2);
        adminPatient.add(updatePatientBtn, gbag2);
        adminPatient.add(deletePatientBtn, gbag2);
        adminPatient.add(backBtn, gbag2);
    }


    public void adminCreatePatientPnl(){
        createPatientPnl = new JPanel();
        GridBagConstraints gbag3 = new GridBagConstraints();
        createPatientPnl.setLayout(new GridBagLayout());
        JTextArea dtls = new JTextArea("Please enter patients details!");
        dtls.setEditable(false);
        JTextArea fstName = new JTextArea("first Name:");
        fstName.setEditable(false);
        fstName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JTextArea fstNameIn = new JTextArea("");
        JTextArea lstName = new JTextArea("Last Name:");
        lstName.setEditable(false);
        JTextArea lstNameIn = new JTextArea("");
        JTextArea mdlName = new JTextArea("Middle names:");
        mdlName.setEditable(false);
        JTextArea mdlNameIn = new JTextArea("");
        JTextArea sex = new JTextArea("Is male:");
        sex.setEditable(false);
        //JTextArea sexIn = new JTextArea("");
        JCheckBox sexInBox = new JCheckBox();
        JTextArea dob = new JTextArea("Date of Birth:");
        dob.setEditable(false);
        DatePicker datePicker = new DatePicker();
        JTextArea medicalInfo = new JTextArea("Medical Information:");
        medicalInfo.setEditable(false);
        JTextArea medicalInfoIn = new JTextArea("");

        JButton addPatient = new JButton("Add Patient");
        addPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(sexInBox.isSelected());
                System.out.println(datePicker.getDate());
                int input = JOptionPane.showOptionDialog(null,"Add Patient " + fstNameIn.getText() +" "+ lstNameIn.getText(), "Add Patient",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if(input == JOptionPane.OK_OPTION){
                    if(mdlNameIn.getText().isEmpty()) {
                        sM.addPatient(datePicker.getDate(), fstNameIn.getText(), lstNameIn.getText(), medicalInfoIn.getText(), sexInBox.isSelected());
                    }else{
                        sM.addPatient(datePicker.getDate(), fstNameIn.getText(),mdlNameIn.getText(), lstNameIn.getText(), medicalInfoIn.getText(), sexInBox.isSelected());
                    }
                }
            }
        });
        JButton addAddressBtn = new JButton("Add Address");
        addAddressBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, PATIENTADDRESS);

            }
        });
        JButton addPhoneBtn = new JButton("Add Phone number");
        addPhoneBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, PATIENTPHONE);
            }
        });
        JButton addEmailBtn = new JButton("Add email address");
        addEmailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, PATIENTEMAIL);
            }
        });

        //Putting the items into the layout
        gbag3.gridx = 2;
        gbag3.fill = 1;
        gbag3.gridy = 0;
        createPatientPnl.add(dtls, gbag3);
        gbag3.gridx = 0;
        gbag3.gridy = 1;
        createPatientPnl.add(fstName, gbag3);
        gbag3.gridy = 2;
        createPatientPnl.add(lstName, gbag3);
        gbag3.gridy = 3;
        createPatientPnl.add(mdlName, gbag3);
        gbag3.gridy = 4;
        createPatientPnl.add(sex, gbag3);
        gbag3.gridy = 5;
        createPatientPnl.add(dob, gbag3);
        gbag3.gridy = 6;
        createPatientPnl.add(medicalInfo, gbag3);
        gbag3.gridy = 1;
        gbag3.gridx = 2;
        createPatientPnl.add(fstNameIn, gbag3);
        gbag3.gridy = 2;
        createPatientPnl.add(lstNameIn, gbag3);
        gbag3.gridy = 3;
        createPatientPnl.add(mdlNameIn, gbag3);
        gbag3.gridy = 4;
        createPatientPnl.add(sexInBox, gbag3);
        gbag3.gridy = 5;
        createPatientPnl.add(datePicker, gbag3);
        gbag3.gridy = 6;

        createPatientPnl.add(medicalInfoIn, gbag3);
        gbag3.gridx = 0;
        gbag3.gridy = 10;
        createPatientPnl.add(addPatient, gbag3);
        gbag3.gridy = 11;
        createPatientPnl.add(addAddressBtn, gbag3);
        gbag3.gridx = 1;
        createPatientPnl.add(addEmailBtn, gbag3);
        gbag3.gridx = 2;
        createPatientPnl.add(addPhoneBtn, gbag3);



    }

    public void addAddressPanel(){
        addAddressPanel = new JPanel();
        JTextArea houseNum = new JTextArea("house number:");
        houseNum.setEditable(false);
        JTextArea houseNumIn = new JTextArea("");
        JTextArea houseName = new JTextArea("house Name:");
        houseName.setEditable(false);
        JTextArea houseNameIn = new JTextArea("");
        JTextArea street = new JTextArea("Street:");
        street.setEditable(false);
        JTextArea streetIn = new JTextArea("");
        JTextArea city = new JTextArea("City:");
        city.setEditable(false);
        JTextArea cityIn = new JTextArea("");
        JTextArea postcode = new JTextArea("Postcode:");
        postcode.setEditable(false);
        JTextArea postcodeIn = new JTextArea("");
        addAddressPanel.add(houseNum);
        addAddressPanel.add(houseNumIn);
        addAddressPanel.add(houseName);
        addAddressPanel.add(houseNameIn);
        addAddressPanel.add(street);
        addAddressPanel.add(streetIn);
        addAddressPanel.add(city);
        addAddressPanel.add(cityIn);
        addAddressPanel.add(postcode);
        addAddressPanel.add(postcodeIn);
        addAddressPanel.add(backBtn);


    }

    public void addPhonePanel(){
        addPhonePanel = new JPanel();
        JTextArea mblPhone = new JTextArea("Mobile Phone Number:");
        mblPhone.setEditable(false);
        JTextArea mblPhoneIn = new JTextArea("");
        JTextArea hmPhone = new JTextArea("Home Phone Number:");
        hmPhone.setEditable(false);
        JTextArea hmPhoneIn = new JTextArea("");
        addPhonePanel.add(mblPhone);
        addPhonePanel.add(mblPhoneIn);
        addPhonePanel.add(hmPhone);
        addPhonePanel.add(hmPhoneIn);

    }

    public void addEmailPanel(){
        addEmailPanel = new JPanel();
        JTextArea email = new JTextArea("Email Address:");
        email.setEditable(false);
        JTextArea emailIn = new JTextArea("");
        addEmailPanel.add(email);
        addEmailPanel.add(emailIn);

    }

    @SuppressWarnings("Duplicates")
    public void createPatientTable(JButton newButton, JPanel newPanel, JTextArea first, JTextArea last){
        DefaultTableModel listTableModel;
        Object[] columnNames= {"First Name", "Last name", "Sex","Email address", "Phone number", "DOB", "Medical Information", "Select Patient"};
        Object[][] data = {};
        listTableModel = new DefaultTableModel(data, columnNames);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
                System.out.println(patients.size());
                List<PatientEmailAddress> patientEmailAddresses = sM.getEmailsByName(first.getText(), last.getText());
                List<PatientPhone> patientPhones = sM.getPhoneByName(first.getText(), last.getText());
                String emailAddress= "";
                String phone= "";
                for(int i = 0; i < patientEmailAddresses.size(); i++){
                    emailAddress += "\n," + patientEmailAddresses.get(i).getEmailAddress();
                }
                for(int i = 0; i < patientPhones.size(); i++){
                    phone +=   patientPhones.get(i).getPhoneNumber() + "\n,";
                }

                for(int i = 0; i < patients.size(); i++){
                    Patient patient= patients.get(i);
                    String isMale = "Male";
                    if(patient.isMale() == false)
                    {
                        isMale= "Female";
                    }
                    listTableModel.addRow(new Object[]{patient.getFirstName() , patient.getLastName(), isMale, emailAddress , phone, patient.getDob() , patient.getMedicalInformation()  , "Select Patient"});
                    System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                // scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setSize(1000,1000);
                scrollPane2.setVisible(true);


                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, createAction(), 7);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                viewPatientPnl.add(scrollPane2);
                newPanel.add(scrollPane2);
    }
        });
    }

   @SuppressWarnings("Duplicates")
    public void createAppointmentTable(JButton jButton,JTextArea first, JTextArea last, GridBagConstraints gbag, JPanel jPanel){
       jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                appointmentByName = sM.getAppointmentByName(first.getText(), last.getText());
                Object[] columnNames = {"First Name", "Last name", "Title", "Staff's Name", "Time", "Date", "Select"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);

                for (int i = 0; i < appointmentByName.size(); i++) {
                    String time = appointmentByName.get(i).getAppointmentTime().toLocalTime().toString();
                    System.out.println(time);
                    String nurseDocName = "";
                    String Title = "Nurse";
                    if (appointmentByName.get(i).getStaff().getDoctor() == null)
                        nurseDocName = appointmentByName.get(i).getStaff().getNurse().getLastName();
                    else {
                        nurseDocName = appointmentByName.get(i).getStaff().getDoctor().getLastName();
                        Title = "Doctor";
                    }
                    listTableModel.addRow(new Object[]{appointmentByName.get(i).getPatient().getFirstName(), appointmentByName.get(i).getPatient().getLastName(), Title, nurseDocName, appointmentByName.get(i).getAppointmentTime().toLocalTime().minusHours(1), appointmentByName.get(i).getAppointmentDate().toString(), "Select Appointment"});
                    jPanel.revalidate();
                }
                appointmentTable = new JTable(listTableModel);

                gbag.gridx = 7;
                gbag.gridy = 0;
                JScrollPane scrollPane = new JScrollPane(appointmentTable);
                scrollPane.setBounds(1000, 1000, 1500, 1000);
                scrollPane.setSize(1000,200);
                scrollPane.setVisible(true);
                jPanel.add(scrollPane);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        System.out.println("Test action");

                    }
                };


                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
            }

        });

    }

    public Action createAction(){
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        };
        return action;
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Surgery appointment sign-in");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setMinimumSize(new Dimension(1000,1000));
        //Create and set up the content pane.
        surgeryGUI GUI = new surgeryGUI();
        GUI.addComponentToPane(frame.getContentPane());

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