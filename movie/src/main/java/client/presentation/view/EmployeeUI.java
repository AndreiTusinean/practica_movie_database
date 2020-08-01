package client.presentation.view;

import client.presentation.controller.EmployeeController;

import javax.swing.*;

public class EmployeeUI {
    private JFrame frame;
    private JPanel panel;
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();
    private JPanel p7 = new JPanel();
    private JLabel l1 = new JLabel("Id");
    private JLabel l2 = new JLabel("Name");
    private JLabel l3 = new JLabel("Year");
    private JLabel l4 = new JLabel("Genre");
    private JLabel l5 = new JLabel("Status");
    private JLabel l6 = new JLabel("Search by:");
    private static JTextField t1 = new JTextField("0");
    private static JTextField t2 = new JTextField("");
    private static JTextField t3 = new JTextField("");
    private static JTextField t4 = new JTextField("");
    private static JTextField t5 = new JTextField("");
    public static JButton bname = new JButton("Name");
    public static JButton byear = new JButton("Year");
    public static JButton bgenre = new JButton("Genre");
    public static JButton bstatus = new JButton("Status");

    //CRUD stock

    public static int id, year;
    public static String name, genre, status;
    static String employeeName;

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public static String getEmployeeName(){return employeeName;}

    public static void getInputName() {
        name = t2.getText().trim();
    }

    public static void getInputYear() {
        try {
            year = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void getInputGenre() {
        genre = t4.getText().trim();
    }

    public static void getInputStatus() {
        status = t5.getText().trim();
    }


    public static void getInputs() {
        name = t2.getText().trim();
        genre = t4.getText().trim();
        status = t5.getText().trim();
        try {
            id = Integer.parseInt(t1.getText().trim());
            year = Integer.parseInt(t3.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void getInputId() {
        try {
            id = Integer.parseInt(t1.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Incorrect inputs");
        }
    }

    public static void getInputDiscount() {
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void initializeEmployee() {
        frame = new JFrame();
        panel = new JPanel();

        t1.setColumns(15);
        t2.setColumns(15);
        t3.setColumns(15);
        t4.setColumns(15);
        t5.setColumns(15);

        p1.add(l1);
        p1.add(t1);
        p2.add(l2);
        p2.add(t2);
        p3.add(l3);
        p3.add(t3);
        p4.add(l4);
        p4.add(t4);


        p6.add(l6);
        p6.add(bname);
        p6.add(byear);
        p6.add(bgenre);
        p6.add(bstatus);
        p7.add(l5);
        p7.add(t5);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p7);
        panel.add(p6);
        panel.add(p5);

        EmployeeController.EmployeeUIActionListeners();

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(430, 300);
        frame.setTitle("Employee operations");
    }

    public EmployeeUI(String employeeName) {
        this.employeeName = employeeName;
        initializeEmployee();
    }

    public static void main(String[] args){
        EmployeeUI e = new EmployeeUI("");
    }
}

