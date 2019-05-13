import com.github.lgooddatepicker.components.DatePicker;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("Duplicates")
public class surgeryGUI extends JFrame implements ActionListener {

    private JPanel cards, type, home, admin, staffPanel, patientPanel, adminPatient, adminAppointment, adminStaff, createPatientPnl, updatePatientPnl,
            deletePatientPnl, viewPatientPnl, patientSelectAppPanel, addPhonePanel, addAddressPanel, addEmailPanel, staffLoggedInPnl, adminAppCreate,
            adminAppView, adminAppDelete, AdminAppUpdate, patientUpdateSelectedPanel, adminAppView2, adminAppUpdate2, updatePatientPanel2;
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
    private final static String ADMINAPPVIEW2 = "View Appointment";
    private final static String ADMINAPPUPDATE = "Update Appointments";
    private final static String ADMINSTAFF = "Manage Staff";
    private final static String CREATEPATIENT = "Create patientPanel";
    private final static String VIEWPATIENT = "View patientPanel";
    private final static String UPDATEPATIENT = "Update patient";
    private final static String UPDATEPATIENT2 = "Update patient2";
    private final static String DELETEPATIENT = "Delete Patient";
    private final static String PATIENTAPPVIEW = "View selected appointment";
    private final static String PATIENTADDRESS = "Add a patients address";
    private final static String PATIENTPHONE = "Add a patients phone number";
    private final static String PATIENTEMAIL = "Add a patients email address";
    private final static String UPDATESELECTED = "Selected patient to update";
    private final static String ADMINADDUPDATE2 = "Update an appointment";
    private JButton beginBtn, backBtn, adminBtn, doctorBtn, patientBtn, managePatientBtn, manageAppointmentBtn, manageStaffBtn, createPatientBtn, viewPatientBtn, updatePatientBtn, deletePatientBtn;
    private JTextArea DoctorType;
    SessionManager sM = new SessionManager();
    private int columnNum = 0;
    private List<Appointment> appointmentByName;
    private List<Appointment> appointmentByDate;
    private List<Appointment> common;
    private JTable appointmentTable, patientTable, patientTable2;
    private Integer staffID;
    private Boolean hasClicked = false;
    private Patient patientCreate;
    private Patient updatePatient;
    private Patient tablePatient;
    private Staff tableStaff;
    private Appointment viewAppointment;
    private Appointment appointmentTemp;


    @SuppressWarnings("Duplicates")
    public void addComponentToPane(Container pane) {

        sM.setUpFactory();
        sM.setUpSession();

        backBtn = new JButton("Back");
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.previous(cards);
            }
        });

        home = new JPanel();
        home.setLayout(new GridBagLayout());
        home.setMinimumSize(new Dimension(1000, 1000));
        beginBtn = new JButton("Click to begin");
        beginBtn.setFont(new Font("Arial", Font.BOLD, 48));
        beginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });
        beginBtn.setMargin(new Insets(500, 500, 500, 500));
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
        createDeletePatientPanel();
        createUpdatePatientPanel();
        // createUpdatePatientPanel2();


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.setSize(1920, 1080);
        cards.setBackground(Color.red);
        cards.add(home, HOME_PANEL);
        cards.add(type, YOU_ARE);
        cards.add(admin, ADMIN);
        cards.add(staffPanel, DOCTOR);
        cards.add(patientPanel, PATIENT);
        cards.add(adminPatient, ADMINPATIENT);
        cards.add(adminAppointment, ADMINAPP);
        cards.add(adminStaff, ADMINSTAFF);
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
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.next(cards);
    }


    public void createUpdatePatientPanel() {
        Object[] columnNames = {"First Name", "Last name", "Sex", "Email address", "DOB", "Medical Information", "Update Patient"};
        Object[][] data = {};
        DefaultTableModel listTableModel;
        listTableModel = new DefaultTableModel(data, columnNames);
        updatePatientPnl = new JPanel();
        GridBagConstraints gbag = new GridBagConstraints();
        updatePatientPnl.setLayout(new GridBagLayout());
        JTextArea first = new JTextArea("First Name", 0, 20);
        JTextArea last = new JTextArea("Last Name", 0, 20);
        JButton fetchPatient = new JButton("Fetch Patient");

        updatePatientPnl.add(first, gbag);
        updatePatientPnl.add(last, gbag);
        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");
            }
        });
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        updatePatientPnl.add(fetchPatient, gbag);
        fetchPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fetchPatient.setEnabled(false);
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
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
                    listTableModel.addRow(new Object[]{patient.getFirstName(), patient.getLastName(), isMale, emailAddress, patient.getDob(), patient.getMedicalInformation(), "Update"});
                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                // scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updatePatient = patients.get(patientTable2.getSelectedRow());
                        UpdateSelectedPatientPanel();
                        cards.add(patientUpdateSelectedPanel, UPDATESELECTED);
                        first.setText("First Name");
                        last.setText("Last Name");
                        System.out.println(updatePatient.getFirstName());
                        CardLayout cl = (CardLayout) (cards.getLayout());
                        cl.show(cards, UPDATESELECTED);
                        if (listTableModel.getRowCount() > 0) {
                            for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                listTableModel.removeRow(i);
                            }
                        }
                        scrollPane2.removeAll();
                        updatePatientPnl.remove(scrollPane2);


                    }
                };
                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                updatePatientPnl.add(scrollPane2);
                updatePatientPnl.revalidate();
            }

        });
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
                fetchPatient.setEnabled(true);
                if (listTableModel.getRowCount() > 0) {
                    for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                        listTableModel.removeRow(i);
                    }
                }
                scrollPane2.removeAll();
                updatePatientPnl.remove(scrollPane2);
                first.setText("First Name");
                last.setText("Last Name");
            }
        });
        updatePatientPnl.add(back);
    }

    public void UpdateSelectedPatientPanel() {
        patientUpdateSelectedPanel = new JPanel();

        JTextArea fstName = new JTextArea("first Name:");
        fstName.setEditable(false);

        JTextArea fstNameIn = new JTextArea(updatePatient.getFirstName(), 0, 20);
        JTextArea lstName = new JTextArea("Last Name:");
        lstName.setEditable(false);
        JTextArea lstNameIn = new JTextArea(updatePatient.getLastName(), 0, 20);
        JTextArea mdlName = new JTextArea("Middle names:");
        mdlName.setEditable(false);
        if (updatePatient.getMiddleName() == null) {
            JTextArea mdlNameIn = new JTextArea("", 0, 20);
        }
        JTextArea mdlNameIn = new JTextArea(updatePatient.getMiddleName(), 0, 20);
        JTextArea sex = new JTextArea("Is male:");
        sex.setEditable(false);
        JTextArea sexIn = new JTextArea("");
        JCheckBox sexInBox = new JCheckBox();
        sexInBox.setSelected(updatePatient.isMale());
        JTextArea dob = new JTextArea("Date of Birth:");
        dob.setEditable(false);
        DatePicker datePicker = new DatePicker();
        datePicker.setDate(updatePatient.getDob());
        JTextArea medicalInfo = new JTextArea("Medical Information:");
        medicalInfo.setEditable(false);
        JTextArea medicalInfoIn = new JTextArea(updatePatient.getMedicalInformation(), 0, 20);
        patientUpdateSelectedPanel.add(fstName);
        patientUpdateSelectedPanel.add(fstNameIn);
        patientUpdateSelectedPanel.add(lstName);
        patientUpdateSelectedPanel.add(lstNameIn);
        patientUpdateSelectedPanel.add(mdlName);
        patientUpdateSelectedPanel.add(mdlNameIn);
        patientUpdateSelectedPanel.add(sex);
        patientUpdateSelectedPanel.add(sexInBox);
        patientUpdateSelectedPanel.add(dob);
        patientUpdateSelectedPanel.add(datePicker);
        patientUpdateSelectedPanel.add(medicalInfo);
        patientUpdateSelectedPanel.add(mdlNameIn);
        JButton updatePatientBtn = new JButton("Update Patient");
        patientUpdateSelectedPanel.add(updatePatientBtn);
        updatePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer id = updatePatient.getId();
                System.out.println(fstNameIn.getText());

                sM.updateFirstName(id, fstNameIn.getText());
                sM.updateLastName(id, lstNameIn.getText());
                sM.updateMiddleName(id, mdlNameIn.getText());
                sM.updateDateOfBirth(id, datePicker.getDate());
                sM.updateMedicalInformation(id, medicalInfoIn.getText());
                sM.updateSex(id, sexInBox.isSelected());
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);


            }
        });
    }

    public void createDeletePatientPanel() {
        deletePatientPnl = new JPanel();
        JTextArea first = new JTextArea("First Name");
        JTextArea last = new JTextArea("Last Name");
        JButton fetchPatient = new JButton("Fetch Patient");
        deletePatientPnl.add(first);
        deletePatientPnl.add(last);
        deletePatientPnl.add(fetchPatient);
        DefaultTableModel listTableModel;
        Object[] columnNames = {"First Name", "Last name", "Sex", "Email address", "DOB", "Medical Information", "Delete Patient"};
        Object[][] data = {};
        listTableModel = new DefaultTableModel(data, columnNames);

        fetchPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fetchPatient.setEnabled(false);
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
                //System.out.println(patients.size());
                List<PatientEmailAddress> patientEmailAddresses = sM.getEmailsByName(first.getText(), last.getText());
                String emailAddress = "";
                for (int i = 0; i < patientEmailAddresses.size(); i++) {
                    emailAddress += "\n" + patientEmailAddresses.get(i).getEmailAddress();
                }
                for (int i = 0; i < patients.size(); i++) {
                    Patient patient = patients.get(i);
                    String isMale = "Male";
                    if (!patient.isMale()) {
                        isMale = "Female";
                    }
                    listTableModel.addRow(new Object[]{patient.getFirstName(), patient.getLastName(), isMale, emailAddress, patient.getDob(), patient.getMedicalInformation(), "Delete"});
                    //System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                // scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Patient patient = patients.get(patientTable2.getSelectedRow());
                        int option = JOptionPane.showOptionDialog(null, "Are you sure you want to delete patient " + patient.getFirstName() + " " + patient.getLastName(), "Delete Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if (option == JOptionPane.OK_OPTION) {
                            sM.deletePatient2(patient.getId());
                            patientTable2.revalidate();
                            scrollPane2.removeAll();
                            scrollPane2.revalidate();
                            deletePatientPnl.remove(scrollPane2);
                            deletePatientPnl.revalidate();
                            deletePatientPnl.repaint();
                            fetchPatient.setEnabled(true);
                        }
                    }
                };
                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                deletePatientPnl.add(scrollPane2);
            }

        });
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        deletePatientPnl.add(back);
    }

    @SuppressWarnings("Duplicates")
    public void createAdminAppointmentCreatePanel() {
        adminAppCreate = new JPanel();
        adminAppCreate.add(new JTextArea("Create appointment"));

        JTextArea first = new JTextArea("First name", 1, 10);
        JTextArea last = new JTextArea("Last name", 1, 10);
        JButton fetchPatient = new JButton("Fetch Patient");
        adminAppCreate.add(first);
        adminAppCreate.add(last);
        adminAppCreate.add(fetchPatient);
        //createPatientTable(fetchPatient, adminAppCreate, first,last);
        DefaultTableModel listTableModel;
        Object[] columnNames = {"First Name", "Last name", "Sex", "Email address", "DOB", "Medical Information", "Select Patient"};
        Object[][] data = {};
        listTableModel = new DefaultTableModel(data, columnNames);

        fetchPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());
                //System.out.println(patients.size());
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
                    //System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                // scrollPane2.setBounds(1000,1000,1500,1000);
                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);
                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tablePatient = patients.get(patientTable2.getSelectedRow());
                        adminAppCreate.remove(scrollPane2);
                        scrollPane2.remove(patientTable2);
                        scrollPane2.revalidate();
                        scrollPane2.setVisible(false);
                        adminAppCreate.revalidate();
                        adminAppCreate.repaint();
                        first.setVisible(false);
                        first.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                first.setText("");
                            }
                        });
                        last.setVisible(false);
                        last.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                last.setText("");
                            }
                        });
                        fetchPatient.setVisible(false);
                        JTextArea jTextArea = new JTextArea("Select date & Time");
                        adminAppCreate.add(jTextArea);
                        JTextArea jTextAreaTime = new JTextArea("Enter Time here", 0, 20);
                        jTextAreaTime.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                jTextAreaTime.setText("");
                            }
                        });
                        adminAppCreate.add(jTextAreaTime);
                        DatePicker datePicker = new DatePicker();
                        adminAppCreate.add(datePicker);
                        JButton dateSelect = new JButton("Select time & date");
                        adminAppCreate.add(dateSelect);
                        scrollPane2.setVisible(true);
                        dateSelect.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
                                datePicker.setVisible(false);
                                jTextArea.setVisible(false);
                                dateSelect.setVisible(false);
                                jTextAreaTime.setVisible(false);
                                JTextArea jTextArea1 = new JTextArea("Select health care Staff");
                                adminAppCreate.add(jTextArea1);
                                jTextArea1.setVisible(true);
                                List<Staff> staff = sM.staffCanWork(Date.valueOf(datePicker.getDate()), Time.valueOf(LocalTime.parse(jTextAreaTime.getText())));
                                ArrayList<String> strings = new ArrayList<String>();
                                for (int i = 0; i < staff.size(); i++) {
                                    String nurseDocName = "";
                                    String Title = "Nurse ";
                                    if (staff.get(i).getDoctor() == null)
                                        nurseDocName = staff.get(i).getNurse().getLastName();
                                    else {
                                        nurseDocName = staff.get(i).getDoctor().getLastName();
                                        Title = "Doctor ";
                                    }
                                    strings.add(Title + nurseDocName);

                                }

                                JComboBox jComboBox = new JComboBox(strings.toArray());
                                jComboBox.setVisible(true);
                                JButton selectStaffBtn = new JButton("Select Staff");
                                selectStaffBtn.setVisible(true);

                                adminAppCreate.add(selectStaffBtn);
                                JButton back = new JButton("Back");
                                selectStaffBtn.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        super.mouseClicked(e);
                                        selectStaffBtn.setVisible(false);
                                        back.setVisible(true);
                                        tableStaff = staff.get(jComboBox.getSelectedIndex());
                                        jComboBox.setVisible(false);

                                        JButton createApp = new JButton("Create Appointment");
                                        adminAppCreate.add(createApp);
                                        createApp.setVisible(true);
                                        createApp.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                super.mouseClicked(e);
                                                int options = JOptionPane.showConfirmDialog(null, "Create Appointment", "Create Appointment", JOptionPane.OK_CANCEL_OPTION);
                                                if (options == JOptionPane.OK_OPTION) {
                                                    sM.addAppointment(Time.valueOf(LocalTime.parse(jTextAreaTime.getText()).plusHours(1)), datePicker.getDate(), tablePatient.getId(), tableStaff.getId(), 1, false, false);

                                                }

                                            }
                                        });

                                        back.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                super.mouseClicked(e);
                                                CardLayout cl = (CardLayout) (cards.getLayout());
                                                cl.show(cards, ADMIN);
                                                jComboBox.removeAllItems();
                                                jTextAreaTime.setText("Enter Time here");
                                                first.setVisible(true);
                                                first.setText("First name");
                                                last.setVisible(true);
                                                last.setText("Last name");
                                                fetchPatient.setVisible(true);
                                                datePicker.setText("");
                                                createApp.setVisible(false);

                                                selectStaffBtn.setVisible(false);
                                                jTextArea1.setVisible(false);
                                                back.setVisible(false);
                                                patients.clear();
                                                patientEmailAddresses.clear();
                                                staff.clear();
                                                if (listTableModel.getRowCount() > 0) {
                                                    for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                                        listTableModel.removeRow(i);
                                                    }
                                                }


                                            }
                                        });
                                        adminAppCreate.add(back);
                                    }
                                });

                                adminAppCreate.add(jComboBox);
                                adminAppCreate.revalidate();
                                //System.out.println(jComboBox.getSelectedIndex());
                            }
                        });

                    }
                };

                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                adminAppCreate.add(scrollPane2);
            }

        });

    }

    public void createAdminAppointmentViewPanel() {
        adminAppView = new JPanel();
        JButton button = new JButton("Get Appointment for Patient");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        adminAppView.setLayout(new GridBagLayout());
        JTextArea first = new JTextArea("First name", 1, 10);
        JTextArea last = new JTextArea("Last name", 1, 10);
        adminAppView.add(first);
        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");
            }
        });
        adminAppView.add(last);
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        adminAppView.add(button);

        button.addMouseListener(new MouseAdapter() {
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
                    ////System.out.println(time);
                    String nurseDocName = "";
                    String Title = "Nurse";
                    if (appointmentByName.get(i).getStaff().getDoctor() == null)
                        nurseDocName = appointmentByName.get(i).getStaff().getNurse().getLastName();
                    else {
                        nurseDocName = appointmentByName.get(i).getStaff().getDoctor().getLastName();
                        Title = "Doctor";
                    }
                    listTableModel.addRow(new Object[]{appointmentByName.get(i).getPatient().getFirstName(), appointmentByName.get(i).getPatient().getLastName(), Title, nurseDocName, appointmentByName.get(i).getAppointmentTime().toLocalTime().minusHours(1), appointmentByName.get(i).getAppointmentDate().toString(), "Select Appointment"});
                    adminAppView.revalidate();
                }
                appointmentTable = new JTable(listTableModel);

                gridBagConstraints.gridx = 7;
                gridBagConstraints.gridy = 0;
                JScrollPane scrollPane = new JScrollPane(appointmentTable);
                scrollPane.setBounds(1000, 1000, 1500, 1000);
                scrollPane.setSize(1000, 200);
                scrollPane.setVisible(true);
                adminAppView.add(scrollPane);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewAppointment = appointmentByName.get(appointmentTable.getSelectedRow());
                        createAppointmentView2Panel();
                        cards.add(adminAppView2, ADMINAPPVIEW2);
                        CardLayout cl = (CardLayout) (cards.getLayout());
                        cl.show(cards, ADMINAPPVIEW2);
                        if (listTableModel.getRowCount() > 0) {
                            for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                listTableModel.removeRow(i);
                            }
                        }
                        first.setText("First Name");
                        last.setText("Last Name");


                    }
                };


                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
            }

        });

        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
                scrollPane.removeAll();
                adminAppView.remove(scrollPane);


            }
        });
        adminAppView.add(back);


    }


    public void createAppointmentView2Panel() {
        adminAppView2 = new JPanel();
        JTextArea details = new JTextArea("test");
        adminAppView2.add(details);
        JButton back = new JButton("Back");
        Patient patient = viewAppointment.getPatient();
        String nurseDocName = "";
        String Title = "Nurse ";
        if (viewAppointment.getStaff().getDoctor() == null)
            nurseDocName = viewAppointment.getStaff().getNurse().getLastName();
        else {
            nurseDocName = viewAppointment.getStaff().getDoctor().getLastName();
            Title = "Doctor ";
        }
        details.setText(patient.getFirstName() + " " + patient.getLastName() + "\n" + viewAppointment.getAppointmentTime().toLocalTime().minusHours(1) +
                "\n" + viewAppointment.getAppointmentDate() + "\n" + Title + nurseDocName + "\n"
                + "Room:" + viewAppointment.getRoom().getRoomName() + viewAppointment.getRoom().getRoomNumber()
                + "\n" + "Checked in: " + viewAppointment.getCheckedIn());

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        adminAppView2.add(back);

    }


    public void createAdminAppointmentUpdatePanel() {
        {
            AdminAppUpdate = new JPanel();
            JButton button = new JButton("Get Appointment for Patient");
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            AdminAppUpdate.setLayout(new GridBagLayout());
            JTextArea first = new JTextArea("First name", 1, 10);
            JTextArea last = new JTextArea("Last name", 1, 10);
            AdminAppUpdate.add(first);
            first.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    first.setText("");
                }
            });
            AdminAppUpdate.add(last);
            last.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    last.setText("");
                }
            });
            AdminAppUpdate.add(button);
            button.addMouseListener(new MouseAdapter() {
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
                        ////System.out.println(time);
                        String nurseDocName = "";
                        String Title = "Nurse";
                        if (appointmentByName.get(i).getStaff().getDoctor() == null)
                            nurseDocName = appointmentByName.get(i).getStaff().getNurse().getLastName();
                        else {
                            nurseDocName = appointmentByName.get(i).getStaff().getDoctor().getLastName();
                            Title = "Doctor";
                        }
                        listTableModel.addRow(new Object[]{appointmentByName.get(i).getPatient().getFirstName(), appointmentByName.get(i).getPatient().getLastName(), Title, nurseDocName, appointmentByName.get(i).getAppointmentTime().toLocalTime().minusHours(1), appointmentByName.get(i).getAppointmentDate().toString(), "Select Appointment"});
                        AdminAppUpdate.revalidate();
                    }
                    appointmentTable = new JTable(listTableModel);

                    gridBagConstraints.gridx = 7;
                    gridBagConstraints.gridy = 0;
                    JScrollPane scrollPane = new JScrollPane(appointmentTable);
                    scrollPane.setBounds(1000, 1000, 1500, 1000);
                    scrollPane.setSize(1000, 200);
                    scrollPane.setVisible(true);
                    AdminAppUpdate.add(scrollPane);

                    Action action = new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            viewAppointment = appointmentByName.get(appointmentTable.getSelectedRow());
                            createAppointmentUpdate2Panel();
                            cards.add(adminAppUpdate2, ADMINADDUPDATE2);
                            CardLayout cl = (CardLayout) (cards.getLayout());
                            cl.show(cards, ADMINADDUPDATE2);
                            if (listTableModel.getRowCount() > 0) {
                                for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                    listTableModel.removeRow(i);
                                }
                            }
                            AdminAppUpdate.remove(scrollPane);
                            AdminAppUpdate.revalidate();
                            first.setText("First Name");
                            last.setText("Last Name");


                        }
                    };


                    ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                    buttonColumn.setMnemonic(KeyEvent.VK_D);
                }

            });

            JButton back = new JButton("Back");
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, ADMIN);
                }
            });
            AdminAppUpdate.add(back);


        }

    }

    public void createAppointmentUpdate2Panel() {
        adminAppUpdate2 = new JPanel();
        JTextArea details = new JTextArea("test");
        adminAppUpdate2.add(details);
        JButton back = new JButton("Back");
        Patient patient = viewAppointment.getPatient();
        String nurseDocName = "";
        String Title = "Nurse ";
        if (viewAppointment.getStaff().getDoctor() == null)
            nurseDocName = viewAppointment.getStaff().getNurse().getLastName();
        else {
            nurseDocName = viewAppointment.getStaff().getDoctor().getLastName();
            Title = "Doctor ";
        }
        details.setText(patient.getFirstName() + " " + patient.getLastName() + "\n" + viewAppointment.getAppointmentTime().toLocalTime().minusHours(1) +
                "\n" + viewAppointment.getAppointmentDate() + "\n" + Title + nurseDocName + "\n"
                + "Room:" + viewAppointment.getRoom().getRoomName() + viewAppointment.getRoom().getRoomNumber()
                + "\n" + "Checked in: " + viewAppointment.getCheckedIn());


        JTextArea firstName = new JTextArea(patient.getFirstName() + " " + patient.getLastName(), 0, 20);

        JTextArea appointmentTime = new JTextArea(viewAppointment.getAppointmentTime().toLocalTime().minusHours(1).toString(), 0, 20);
        DatePicker datePicker = new DatePicker();
        datePicker.setText(viewAppointment.getAppointmentDate().toString());
        JTextArea healthCareStaff = new JTextArea(viewAppointment.getStaff().getId().toString(), 0, 20);
        JTextArea roomNum = new JTextArea(viewAppointment.getRoom().getId().toString(), 0, 20);
        adminAppUpdate2.add(firstName);
        adminAppUpdate2.add(appointmentTime);
        adminAppUpdate2.add(datePicker);
        adminAppUpdate2.add(healthCareStaff);
        adminAppUpdate2.add(roomNum);

        JButton updateAppointment = new JButton("Update Appointment");
        updateAppointment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer appId = viewAppointment.getId();
                sM.updateAppointmentDate(appId, datePicker.getDate());
                sM.updateAppointmentRoom(appId, Integer.parseInt(roomNum.getText()));
                sM.updateAppointmentTime(appId, Time.valueOf(LocalTime.parse(appointmentTime.getText())));
                //sM.updateAppointmentStaff(appId, Integer.parseInt(healthCareStaff.toString()));
                System.out.println(appId);
                updateAppointment.setEnabled(false);

            }
        });
        adminAppUpdate2.add(updateAppointment);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
                updateAppointment.setEnabled(true);
            }
        });
        adminAppUpdate2.add(back);

    }


    @SuppressWarnings("Duplicates")
    public void createAdminAppointmentDeletePanel() {
        adminAppDelete = new JPanel();
        JButton button = new JButton("Get Appointment for Patient");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        adminAppDelete.setLayout(new GridBagLayout());
        JTextArea first = new JTextArea("First name", 1, 10);
        JTextArea last = new JTextArea("Last name", 1, 10);
        adminAppDelete.add(first);
        adminAppDelete.add(last);
        adminAppDelete.add(button);
        //createAppointmentTable(button,first, last, gridBagConstraints, adminAppDelete);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                button.setEnabled(false);
                appointmentByName = sM.getAppointmentByName(first.getText(), last.getText());
                Object[] columnNames = {"First Name", "Last name", "Title", "Staff's Name", "Time", "Date", "Delete"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);

                for (int i = 0; i < appointmentByName.size(); i++) {
                    String time = appointmentByName.get(i).getAppointmentTime().toLocalTime().toString();
                    //System.out.println(time);
                    String nurseDocName = "";
                    String Title = "Nurse";
                    if (appointmentByName.get(i).getStaff().getDoctor() == null)
                        nurseDocName = appointmentByName.get(i).getStaff().getNurse().getLastName();
                    else {
                        nurseDocName = appointmentByName.get(i).getStaff().getDoctor().getLastName();
                        Title = "Doctor";
                    }
                    listTableModel.addRow(new Object[]{appointmentByName.get(i).getPatient().getFirstName(), appointmentByName.get(i).getPatient().getLastName(), Title, nurseDocName, appointmentByName.get(i).getAppointmentTime().toLocalTime().minusHours(1), appointmentByName.get(i).getAppointmentDate().toString(), "Select Appointment"});
                    adminAppDelete.revalidate();
                }
                appointmentTable = new JTable(listTableModel);

                gridBagConstraints.gridx = 7;
                gridBagConstraints.gridy = 0;
                JScrollPane scrollPane = new JScrollPane(appointmentTable);
                scrollPane.setBounds(1000, 1000, 1500, 1000);
                scrollPane.setSize(1000, 200);
                scrollPane.setVisible(true);
                adminAppDelete.add(scrollPane);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Appointment appointment = appointmentByName.get(appointmentTable.getSelectedRow());
                        int option = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the appointment for " + appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName() + " on" + appointment.getAppointmentDate() + "At " + appointment.getAppointmentTime().toLocalTime().minusHours(1).toString(), "Delete appointment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        if (option == JOptionPane.OK_OPTION) {
                            sM.deleteAppointment(appointment.getId());
                            //System.out.println(appointment.getId());

                            appointmentTable.revalidate();
                            scrollPane.removeAll();
                            scrollPane.revalidate();
                            if (listTableModel.getRowCount() > 0) {
                                for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                    listTableModel.removeRow(i);
                                }
                            }
                            adminAppDelete.remove(scrollPane);
                            adminAppDelete.revalidate();
                            adminAppDelete.repaint();
                            //System.out.println("delete");
                            button.setEnabled(true);
                        }


                    }
                };


                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
            }

        });
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);

            }
        });
        adminAppDelete.add(back);
    }

    @SuppressWarnings("Duplicates")
    public void createAdminAppointmentPanel() {
        adminAppointment = new JPanel();
        GridBagConstraints gbag2 = new GridBagConstraints();
        adminAppointment.setLayout(new GridBagLayout());
        JButton createAppBtn = new JButton("Create Appointment");
        createAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINAPPCREATE);
            }
        });
        JButton viewAppBtn = new JButton("View Appointment");
        viewAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINAPPVIEW);
            }
        });
        JButton UpdateAppBtn = new JButton("Update an Appointment");
        UpdateAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINAPPUPDATE);
            }
        });
        JButton deleteAppBtn = new JButton("Delete an Appointment");
        deleteAppBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINAPPDELETE);

            }
        });
        adminAppointment.add(createAppBtn, gbag2);
        adminAppointment.add(viewAppBtn, gbag2);
        adminAppointment.add(UpdateAppBtn, gbag2);
        adminAppointment.add(deleteAppBtn, gbag2);
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        adminAppointment.add(back);
    }

    @SuppressWarnings("Duplicates")
    private void createViewPatientPanel() {
        GridBagConstraints gbag = new GridBagConstraints();
        viewPatientPnl = new JPanel();
        viewPatientPnl.setLayout(new GridBagLayout());
        JTextArea enter = new JTextArea("Please enter first and last name");
        enter.setEditable(false);
        JTextArea first = new JTextArea("firstName", 1, 20);
        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");
            }
        });
        JTextArea last = new JTextArea("LastName", 1, 20);
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        JButton button = new JButton("Fetch patients");
        //createPatientTable(button, viewPatientPnl, first, last);
        DefaultTableModel listTableModel1;
        Object[] columnNames = {"First Name", "Middle Names", "Last name", "Sex", "DOB", "Email ", "Medical Information"};
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
                String emailAddress = "";
                for (PatientEmailAddress patientEmailAddress : patientEmailAddresses) {
                    emailAddress += patientEmailAddress.getEmailAddress();
                }
                for (Patient patient : patients) {
                    String isMale = "Male";
                    if (patient.isMale() != false) {
                        isMale = "Female";
                    }
                    String middle = "";
                    if (patient.getMiddleName() != null) {
                        middle = patient.getMiddleName();
                    }
                    listTableModel1.addRow(new Object[]{patient.getFirstName(), middle, patient.getLastName(), isMale, patient.getDob(), emailAddress, patient.getMedicalInformation()});
                    //System.out.println(patients.get(i).getFirstName());
                }
                patientTable2 = new JTable(listTableModel1);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);
                scrollPane2.setBounds(1000, 1000, 1500, 1000);
                scrollPane2.setVisible(true);

                viewPatientPnl.add(scrollPane2, gbag);
            }
        });

        gbag.gridy = 0;
        viewPatientPnl.add(first, gbag);
        gbag.gridy = 1;
        viewPatientPnl.add(last, gbag);
        gbag.gridx = 2;
        viewPatientPnl.add(button, gbag);
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);

                if (listTableModel1.getRowCount() > 0) {
                    for (int i = listTableModel1.getRowCount() - 1; i > -1; i--) {
                        listTableModel1.removeRow(i);
                    }
                }

                first.setText("First Name");
                last.setText("Last Name");
            }
        });
        viewPatientPnl.add(back);


    }

    @SuppressWarnings("Duplicates")
    public void createPatientPanelFindPatient() {
        patientPanel = new JPanel();
        GridBagConstraints gbag = new GridBagConstraints();

        patientPanel.setLayout(new GridBagLayout());
        JTextArea enter = new JTextArea("Please enter your first and last name");
        enter.setEditable(false);
        JTextArea first = new JTextArea("firstName", 1, 10);


        first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                first.setText("");
            }
        });

        JTextArea last = new JTextArea("LastName", 1, 10);
        last.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                last.setText("");
            }
        });
        JButton getApp = new JButton("get Appointment");
        gbag.gridx = 0;
        gbag.gridy = 0;
        patientPanel.add(enter, gbag);
        gbag.gridy = 1;
        patientPanel.add(first, gbag);
        gbag.gridy = 2;
        patientPanel.add(last, gbag);
        gbag.gridy = 3;
        patientPanel.add(getApp, gbag);
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });
        gbag.gridy = 4;
        patientPanel.add(back, gbag);
        getApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                appointmentByName = sM.getAppointmentByName(first.getText(), last.getText());
                appointmentByDate = sM.getAppointmentByDate(LocalDate.now());//System.out.println(appointmentByName);
                common = appointmentByDate.stream().filter(appointmentByName::contains).collect(toList());
                //List<Appointment> collect = appointmentByName.stream().filter(appointment -> appointmentByDate.contains(appointment)).collect(toList());
                Object[] columnNames = {"First Name", "Last name", "Title", "Staff's Name", "Time", "Date", "Select"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);

                for (Appointment appointment : common) {
                    String time = appointment.getAppointmentTime().toLocalTime().toString();
                    //System.out.println(time);
                    String nurseDocName = "";
                    String Title = "Nurse";
                    //if(common.get(i).getStaff().getDoctor() == null){//System.out.println(common.get(i).getStaff().getNurse().getLastName());}
                    if (appointment.getStaff().getDoctor() == null)
                        nurseDocName = appointment.getStaff().getNurse().getLastName();
                    else {
                        nurseDocName = appointment.getStaff().getDoctor().getLastName();
                        Title = "Doctor";
                    }
                    listTableModel.addRow(new Object[]{appointment.getPatient().getFirstName(), appointment.getPatient().getLastName(), Title, nurseDocName, appointment.getAppointmentTime().toLocalTime().minusHours(1), appointment.getAppointmentDate().toString(), "Select Appointment"});

                    //patientPanel.add(appointmentTable, gbag);
                    patientPanel.revalidate();
                }
                appointmentTable = new JTable(listTableModel);
                //appointmentTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRender());

                gbag.gridx = 7;
                gbag.gridy = 0;
                JScrollPane scrollPane = new JScrollPane(appointmentTable);
                scrollPane.setBounds(1000, 1000, 1500, 1000);
                scrollPane.setVisible(true);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //System.out.println("test");
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 1);

                        columnNum = table.getSelectedRow();

                        //System.out.println(table.getSelectedRow());
                        CreatePatientSelectAppPanel();
                        cards.add(patientSelectAppPanel, PATIENTAPPVIEW);
                        CardLayout cl = (CardLayout) (cards.getLayout());
                        cl.show(cards, PATIENTAPPVIEW);
                        patientPanel.remove(appointmentTable);

                        if (listTableModel.getRowCount() > 0) {
                            for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                listTableModel.removeRow(i);
                            }
                        }
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
                if (common.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Patient " + first.getText() + " " + last.getText() + " Does not have an appointment today", "No appointment", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    patientPanel.add(scrollPane, gbag);
                }
            }
        });


    }

    @SuppressWarnings("Duplicates")
    public void CreatePatientSelectAppPanel() {
        patientSelectAppPanel = new JPanel();
        Appointment appointment = common.get(columnNum);
        Patient patient = appointment.getPatient();
        String nurseDocName = "";
        String title = "Nurse ";
        if (common.get(columnNum).getStaff().getDoctor() == null)
            nurseDocName = common.get(columnNum).getStaff().getNurse().getLastName();
        else {
            nurseDocName = common.get(columnNum).getStaff().getDoctor().getLastName();
            title = "Doctor ";
        }
        JTextArea appDetails = new JTextArea("Patient " + patient.getFirstName() + " " + patient.getLastName() + " with " + title + nurseDocName + " on " + appointment.getAppointmentDate() + " at " + appointment.getAppointmentTime().toLocalTime().minusHours(1));
        JButton checkIn = new JButton("Check In");
        checkIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Staff staff = appointment.getStaff();
                String nurseDocName = "";
                String title = "Nurse ";
                if (staff.getDoctor() == null) nurseDocName = staff.getNurse().getLastName();
                else {
                    nurseDocName = staff.getDoctor().getLastName();
                    title = "Doctor ";
                }
                int input = JOptionPane.showOptionDialog(null, "Checked in for your appointment at " + appointment.getAppointmentTime().toLocalTime().minusHours(1) + " with " + title + nurseDocName, "check in", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    //sM.updateAppointmentCheckedIn(appointment.getId(),true);
                    sM.updateAppointmentCheckedIn2(appointment.getId(), true);

                    patientPanel.remove(appointmentTable);
                    patientPanel.revalidate();
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, PATIENT);
                } else {

                    patientPanel.remove(appointmentTable);
                    patientPanel.revalidate();
                    JOptionPane.showMessageDialog(null, "Returning you to patient area without checking in");
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, PATIENT);
                }
            }
        });
        patientSelectAppPanel.add(appDetails);
        patientSelectAppPanel.add(checkIn);


    }


    public void createStaffPanel() {
        JTextField staffId = new JTextField("Please enter your staff Id number");
        JTextArea staffIdIn = new JTextArea("Enter here", 1, 20);

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

                } else {

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
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });
        staffPanel.add(back);
    }


    @SuppressWarnings("Duplicates")
    public void createStaffLoggedInPanel() {
        Staff staff = sM.getStaff(staffID);
        String nurseDocName = "";
        String title = "Nurse ";
        if (staff.getDoctor() == null) nurseDocName = staff.getNurse().getLastName();
        else {
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


        viewApps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] columnNames = {"First Name", "Last name", "Medical Information", "Time", "Date", "Start Appointment"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);
                List<Appointment> appointments = sM.getAppointmentFromStaffId(staffID);
                List<Appointment> appointmentListToday = sM.getAppointmentByDate(LocalDate.now());
                common = appointmentListToday.stream().filter(appointments::contains).collect(toList());
                for (int i = 0; i < common.size(); i++) {
                    listTableModel.addRow(new Object[]{common.get(i).getPatient().getFirstName(), common.get(i).getPatient().getLastName(), common.get(i).getPatient().getMedicalInformation(),
                            common.get(i).getAppointmentTime().toLocalTime().minusHours(1), common.get(i).getAppointmentDate().toString(), "Start Appointment"});
                }

                Action action2 = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Appointment appointment = common.get(appointmentTable.getSelectedRow());
                        sM.updateAppointmentCheckedIn2(appointment.getId(), true);
                        if (LocalTime.now().isBefore(appointment.getAppointmentTime().toLocalTime().minusHours(1))) {
                            int input = JOptionPane.showOptionDialog(null, "Start Appointment", "Start Appointment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                            if (input == JOptionPane.YES_OPTION) {
                                sM.updateAppointmentOnTime2(appointment.getId(), true);
                                if (listTableModel.getRowCount() > 0) {
                                    for (int i = listTableModel.getRowCount() - 1; i > -1; i--) {
                                        listTableModel.removeRow(i);
                                    }
                                }
                                staffLoggedInPnl.remove(scrollPane2);
                                scrollPane3.removeAll();
                                scrollPane2.setVisible(false);
                            }
                        } else {
                            sM.updateAppointmentOnTime2(appointment.getId(), false);
                            staffLoggedInPnl.remove(scrollPane2);
                            scrollPane3.removeAll();
                            scrollPane2.setVisible(false);
                        }

                    }
                };

                appointmentTable = new JTable(listTableModel);
                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action2, 5);
                buttonColumn.setMnemonic(KeyEvent.VK_D);


                scrollPane2 = new JScrollPane(appointmentTable);
                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);

                gbag.gridy = 3;
                staffLoggedInPnl.add(scrollPane2, gbag);
                staffLoggedInPnl.repaint();
                staffLoggedInPnl.revalidate();
                scrollPane3.removeAll();
                if (hasClicked) staffLoggedInPnl.remove(scrollPane3);
                viewApps.setEnabled(false);
                viewApps2.setEnabled(true);
                hasClicked = true;

            }
        });


        viewApps2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                Object[] columnNames = {"First Name", "Last name", "Medical Information", "Time", "Date"};
                Object[][] data = {};
                DefaultTableModel listTableModel;
                listTableModel = new DefaultTableModel(data, columnNames);
                List<Appointment> appointments = sM.getAppointmentFromStaffId(staffID);
                List<Appointment> appointmentListToday = sM.getAppointmentByDateWeek(LocalDate.now());
                common = appointmentListToday.stream().filter(appointments::contains).collect(toList());
                for (int i = 0; i < common.size(); i++) {
                    listTableModel.addRow(new Object[]{common.get(i).getPatient().getFirstName(), common.get(i).getPatient().getLastName(), common.get(i).getPatient().getMedicalInformation(),
                            common.get(i).getAppointmentTime().toLocalTime().minusHours(1), common.get(i).getAppointmentDate().toString()});
                }
                appointmentTable = new JTable(listTableModel);
                appointmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                appointmentTable.getColumnModel().getColumn(2).setWidth(30);
                scrollPane3 = new JScrollPane(appointmentTable);
                scrollPane3.setSize(1000, 1000);
                scrollPane3.setVisible(true);
                gbag.gridy = 3;
                staffLoggedInPnl.add(scrollPane3, gbag);
                viewApps2.setEnabled(false);
                viewApps.setEnabled(true);
                scrollPane2.removeAll();
                staffLoggedInPnl.repaint();
                staffLoggedInPnl.revalidate();
                if (hasClicked) staffLoggedInPnl.remove(scrollPane2);
                hasClicked = true;

            }
        });


        staffLoggedInPnl.add(name);
        staffLoggedInPnl.add(viewApps);
        staffLoggedInPnl.add(viewApps2);

        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });
        staffLoggedInPnl.add(back);
    }


    public void createCurrentAppointmentPanel() {

    }

    @SuppressWarnings("Duplicates")
    public void createTypePanel() {
        type = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        type.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        DoctorType = new JTextArea("You are?");
        DoctorType.setMargin(new Insets(100, 500, 100, 500));
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
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        doctorBtn = new JButton("HealthCare Staff");
        doctorBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, DOCTOR);
            }
        });
        patientBtn = new JButton("Patient");
        patientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, PATIENT);
            }
        });
        type.add(adminBtn, c);
        type.add(doctorBtn, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        type.add(patientBtn, c);
    }

    @SuppressWarnings("Duplicates")
    public void createAdminPanel() {
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
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINAPP);
            }
        });
        managePatientBtn = new JButton("Manage Patients");
        managePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMINPATIENT);
            }
        });
        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, YOU_ARE);
            }
        });

       /* manageStaffBtn = new JButton("Manage Staff");
        manageStaffBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, ADMINSTAFF);
            }
        });*/

        admin.add(managePatientBtn, g);
        admin.add(manageAppointmentBtn, g);
        admin.add(back, g);
        //admin.add(manageStaffBtn, g);

    }

    @SuppressWarnings("Duplicates")
    public void createAdminPatientPanel() {
        adminPatient = new JPanel();
        GridBagConstraints gbag2 = new GridBagConstraints();
        adminPatient.setLayout(new GridBagLayout());
        createPatientBtn = new JButton("Create Patient");
        createPatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, CREATEPATIENT);
            }
        });
        viewPatientBtn = new JButton("View Patient");
        viewPatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, VIEWPATIENT);
            }
        });
        updatePatientBtn = new JButton("Update a patient");
        updatePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, UPDATEPATIENT);

            }
        });
        deletePatientBtn = new JButton("Delete a patient");
        deletePatientBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, DELETEPATIENT);

            }
        });
        adminPatient.add(createPatientBtn, gbag2);
        adminPatient.add(viewPatientBtn, gbag2);
        adminPatient.add(updatePatientBtn, gbag2);
        adminPatient.add(deletePatientBtn, gbag2);

        JButton back = new JButton("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
            }
        });
        adminPatient.add(back, gbag2);
    }


    public void adminCreatePatientPnl() {
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
        JButton addAddressBtn = new JButton("Add Patient_Address");
        addAddressBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, PATIENTADDRESS);

            }
        });
        JButton addPhoneBtn = new JButton("Add Phone number");
        addPhoneBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, PATIENTPHONE);
            }
        });
        JButton addEmailBtn = new JButton("Add email address");
        addEmailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, PATIENTEMAIL);
            }
        });
        JButton addPatient = new JButton("Add Patient");
        addPatient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //System.out.println(sexInBox.isSelected());
                //System.out.println(datePicker.getDate());
                int input = JOptionPane.showOptionDialog(null, "Add Patient " + fstNameIn.getText() + " " + lstNameIn.getText(), "Add Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    if (mdlNameIn.getText().isEmpty()) {
                        patientCreate = sM.addPatient2(datePicker.getDate(), fstNameIn.getText(), lstNameIn.getText(), medicalInfoIn.getText(), sexInBox.isSelected());
                    } else {
                        patientCreate = sM.addPatient2(datePicker.getDate(), fstNameIn.getText(), mdlNameIn.getText(), lstNameIn.getText(), medicalInfoIn.getText(), sexInBox.isSelected());
                    }
                }
                addAddressBtn.setEnabled(true);
                addEmailBtn.setEnabled(true);
                addPhoneBtn.setEnabled(true);
            }
        });

        addAddressBtn.setEnabled(false);
        addEmailBtn.setEnabled(false);
        addPhoneBtn.setEnabled(false);

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
        gbag3.gridy = 12;
        gbag3.gridx = 0;
        JButton finish = new JButton("Finish");
        finish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ADMIN);
                datePicker.setText("");
                fstNameIn.setText("");
                lstNameIn.setText("");
                sexInBox.setSelected(false);
                mdlNameIn.setText("");
                medicalInfoIn.setText("");

            }
        });
        createPatientPnl.add(finish);


    }

    public void addAddressPanel() {
        addAddressPanel = new JPanel();
        JTextArea houseNum = new JTextArea("house number:");
        houseNum.setEditable(false);
        JTextArea houseNumIn = new JTextArea("", 0, 20);
        JTextArea houseName = new JTextArea("house Name:");
        houseName.setEditable(false);
        JTextArea houseNameIn = new JTextArea("", 0, 20);
        JTextArea street = new JTextArea("Street:", 0, 20);
        street.setEditable(false);
        JTextArea streetIn = new JTextArea("", 0, 20);
        JTextArea city = new JTextArea("City:");
        city.setEditable(false);
        JTextArea cityIn = new JTextArea("", 0, 20);
        JTextArea postcode = new JTextArea("Postcode:");
        postcode.setEditable(false);
        JTextArea postcodeIn = new JTextArea("", 0, 20);
        JTextArea county = new JTextArea("County:");
        postcode.setEditable(false);
        JTextArea countyIn = new JTextArea("", 0, 20);
        JTextArea country = new JTextArea("Country:");
        postcode.setEditable(false);
        JTextArea countryIn = new JTextArea("", 0, 20);
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
        addAddressPanel.add(county);
        addAddressPanel.add(countyIn);
        addAddressPanel.add(country);
        addAddressPanel.add(countryIn);
        JButton jButton = new JButton("Add address");
        addAddressPanel.add(jButton);
        addAddressPanel.add(backBtn);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Patient_Address patient_address = sM.addPatientAddress2(patientCreate.getId(), houseNameIn.getText(), houseNumIn.getText(), streetIn.getText(), cityIn.getText(), postcodeIn.getText(), countyIn.getText(), countryIn.getText());
                patientCreate.addAddress(patient_address);
                int confirm = JOptionPane.showConfirmDialog(null, "Add another?", "Add email", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    houseNumIn.setText("");
                    houseName.setText("");
                    streetIn.setText("");
                    cityIn.setText("");
                    postcodeIn.setText("");
                    countyIn.setText("");
                    countryIn.setText("");
                } else {
                    houseNumIn.setText("");
                    houseName.setText("");
                    streetIn.setText("");
                    cityIn.setText("");
                    postcodeIn.setText("");
                    countyIn.setText("");
                    countryIn.setText("");
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, CREATEPATIENT);
                }
            }
        });


    }

    @SuppressWarnings("Duplicates")
    public void addPhonePanel() {
        addPhonePanel = new JPanel();
        JTextArea mblPhone = new JTextArea("Phone Number:");
        mblPhone.setEditable(false);
        JTextArea phoneIn = new JTextArea("");
        addPhonePanel.add(mblPhone);
        addPhonePanel.add(phoneIn);

        JButton button = new JButton("Add Phone number");
        addPhonePanel.add(button);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sM.addPatientPhone(phoneIn.getText(), patientCreate.getId());
                int confirm = JOptionPane.showConfirmDialog(null, "Add another?", "Add Phone", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    phoneIn.setText("");
                } else {
                    phoneIn.setText("");
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, CREATEPATIENT);
                }

            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void addEmailPanel() {
        addEmailPanel = new JPanel();
        JTextArea email = new JTextArea("Email Patient_Address:");
        email.setEditable(false);
        JTextArea emailIn = new JTextArea("", 0, 20);
        addEmailPanel.add(email);
        addEmailPanel.add(emailIn);
        JButton button = new JButton("Add Email");
        addEmailPanel.add(button);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sM.addPatientEmailAddress(emailIn.getText(), patientCreate.getId());
                int confirm = JOptionPane.showConfirmDialog(null, "Add another?", "Add email", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    emailIn.setText("");
                } else {
                    emailIn.setText("");
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, CREATEPATIENT);
                }

                //PatientEmailAddress  patientEmailAddress = sM.addPatientEmailAddress2(emailIn.getText(), patientCreate.getId());
                //patientCreate.addEmail(patientEmailAddress);


            }
        });

    }

    @SuppressWarnings("Duplicates")
    public void createPatientTable(JButton newButton, JPanel newPanel, JTextArea first, JTextArea last) {
        DefaultTableModel listTableModel;
        Object[] columnNames = {"First Name", "Last name", "Sex", "Email address", "Phone number", "DOB", "Medical Information", "Select Patient"};
        Object[][] data = {};
        listTableModel = new DefaultTableModel(data, columnNames);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                List<Patient> patients = sM.getPatientByFullName(first.getText(), last.getText());

                List<PatientEmailAddress> patientEmailAddresses = sM.getEmailsByName(first.getText(), last.getText());
                List<PatientPhone> patientPhones = sM.getPhoneByName(first.getText(), last.getText());
                String emailAddress = "";
                String phone = "";
                for (int i = 0; i < patientEmailAddresses.size(); i++) {
                    emailAddress += "\n," + patientEmailAddresses.get(i).getEmailAddress();
                }
                for (int i = 0; i < patientPhones.size(); i++) {
                    phone += patientPhones.get(i).getPhoneNumber() + "\n,";
                }

                for (int i = 0; i < patients.size(); i++) {
                    Patient patient = patients.get(i);
                    String isMale = "Male";
                    if (patient.isMale() == false) {
                        isMale = "Female";
                    }
                    listTableModel.addRow(new Object[]{patient.getFirstName(), patient.getLastName(), isMale, emailAddress, phone, patient.getDob(), patient.getMedicalInformation(), "Select Patient"});

                }
                patientTable2 = new JTable(listTableModel);
                JScrollPane scrollPane2 = new JScrollPane(patientTable2);

                scrollPane2.setSize(1000, 1000);
                scrollPane2.setVisible(true);
                ButtonColumn buttonColumn = new ButtonColumn(patientTable2, createAction(), 7);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
                newPanel.add(scrollPane2);
                newPanel.add(scrollPane2);
            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void createAppointmentTable(JButton jButton, JTextArea first, JTextArea last, GridBagConstraints gbag, JPanel jPanel) {
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
                    ////System.out.println(time);
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
                scrollPane.setSize(1000, 200);
                scrollPane.setVisible(true);
                jPanel.add(scrollPane);

                Action action = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ////System.out.println("Test action");

                    }
                };


                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
            }

        });

    }

    public void createAppointmentTableWithAction(JButton jButton, JTextArea first, JTextArea last, GridBagConstraints gbag, Action action, JPanel jPanel) {
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
                scrollPane.setSize(1000, 200);
                scrollPane.setVisible(true);
                jPanel.add(scrollPane);
                ButtonColumn buttonColumn = new ButtonColumn(appointmentTable, action, 6);
                buttonColumn.setMnemonic(KeyEvent.VK_D);
            }

        });

    }


    public Action createAction() {
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ////System.out.println("test");
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
        frame.setSize(1000, 1000);
        frame.setMinimumSize(new Dimension(1000, 1000));
        //Create and set up the content pane.
        surgeryGUI GUI = new surgeryGUI();
        GUI.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}