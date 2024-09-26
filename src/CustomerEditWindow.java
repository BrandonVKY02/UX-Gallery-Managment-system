import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerEditWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton editButton = new JButton("Save Edit");//编辑按钮
    private JButton deleteButton = new JButton("Delete");//删除按钮

    private JLabel backgroundLabel;

    private JTextField customerNameField;
    private JTextField customerPhoneField;
    private DefaultListModel<String> customerPurchasesListModel;
    private JList<String> customerPurchasesList;

    private DefaultListModel<String> customerPreferencesListModel;
    private JList<String> customerPreferencesList;



    private String customerName;
    private String customerPhone;
    private String customerPurchases;
    private String customerPreferences;

    int ID;
    private Point pressedPoint = null; // 鼠标按下时的坐标
    public CustomerEditWindow(String CustomerID){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        layeredPane = getLayeredPane();
        setSize(710,470);

        ID = Integer.parseInt(CustomerID);

        closeButton.setBounds(640, 10, 60, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });

        editButton.setBounds(710-(100+10), 470-40, 100, 30);
        editButton.setUI((ButtonUI) UIManager.getUI(editButton));

        deleteButton.setBounds(710 - ((100+10)+10+100), 470-40, 100, 30);
        deleteButton.setUI((ButtonUI) UIManager.getUI(deleteButton));

        backgroundLabel = new JLabel(new ImageIcon(config.CustomerInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);

        Filereader fileReader = new Filereader("customer");
        customerName = fileReader.returnCustomerName(ID);
        customerPhone = fileReader.returnCustomerPhone(ID);
        customerPurchases = fileReader.returnCustomerPurchases(ID);
        customerPreferences = fileReader.returnCustomerPreferences(ID);

        customerNameField = new JTextField(customerName);
        customerNameField.setBounds(260, 73, 350, 50);
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 30));
        customerNameField.setForeground(Color.WHITE);
        customerNameField.setOpaque(false);
        customerNameField.setBorder(null);

        customerPhoneField = new JTextField(customerPhone);
        customerPhoneField.setBounds(260, 148, 350, 50);
        customerPhoneField.setFont(new Font("Arial", Font.PLAIN, 30));
        customerPhoneField.setForeground(Color.WHITE);
        customerPhoneField.setOpaque(false);
        customerPhoneField.setBorder(null);

        customerPurchasesListModel = new DefaultListModel<>();
        String[] customerPurchasesArray = customerPurchases.split(",");
            for (String s : customerPurchasesArray) {
            customerPurchasesListModel.addElement(s);
            }
        customerPurchasesList = new JList<>(customerPurchasesListModel);
        JPanel customerPurchasesListPanel = new JPanel(new BorderLayout());
        customerPurchasesListPanel.setBounds(90, 260, 305-90, 375-260);
        customerPurchasesListPanel.add(new JScrollPane(customerPurchasesList), BorderLayout.CENTER);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newOption = JOptionPane.showInputDialog(CustomerEditWindow.this,
                        "Please input the new data:");
                if (newOption != null && !newOption.isEmpty()) {
                    customerPurchasesListModel.addElement(newOption);
                }
            }
        });

        // 创建删除按钮
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = customerPurchasesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    customerPurchasesListModel.remove(selectedIndex);
                }
            }
        });

        // 将按钮添加到界面上
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        customerPurchasesListPanel.add(buttonPanel, "South");

        customerPreferencesListModel = new DefaultListModel<>();
        String[] customerPreferencesArray = customerPreferences.split(",");
        for (String s : customerPreferencesArray) {
               customerPreferencesListModel.addElement(s);
        }
        customerPreferencesList = new JList<>(customerPreferencesListModel);
        JPanel customerPreferencesListPanel = new JPanel(new BorderLayout());
        customerPreferencesListPanel.setBounds(400, 260, 305-90, 375-260);
        customerPreferencesListPanel.add(new JScrollPane(customerPreferencesList) , BorderLayout.CENTER);
        JButton addButton2 = new JButton("Add");
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newOption = JOptionPane.showInputDialog(CustomerEditWindow.this,
                        "Please input the new data:");
                if (newOption != null && !newOption.isEmpty()) {
                    customerPreferencesListModel.addElement(newOption);
                }
            }
        });
        JButton deleteButton2 = new JButton("Delete");
        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = customerPreferencesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    customerPreferencesListModel.remove(selectedIndex);
                }
            }
        });
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(addButton2);
        buttonPanel2.add(deleteButton2);
        customerPreferencesListPanel.add(buttonPanel2, "South");


        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCustomerName = customerNameField.getText();
                String newCustomerPhone = customerPhoneField.getText();
                String newCustomerPurchases = "";
                String newCustomerPreferences = "";
                for (int i = 0; i < customerPurchasesListModel.size(); i++) {
                    newCustomerPurchases += customerPurchasesListModel.get(i) + ",";
                }
                for (int i = 0; i < customerPreferencesListModel.size(); i++) {
                    newCustomerPreferences += customerPreferencesListModel.get(i) + ",";
                }
                newCustomerPurchases = newCustomerPurchases.substring(0, newCustomerPurchases.length() - 1);
                newCustomerPreferences = newCustomerPreferences.substring(0, newCustomerPreferences.length() - 1);
                CustomerManager customerManager = new CustomerManager();
                customerManager.updateCustomer(ID, newCustomerName, newCustomerPhone, newCustomerPurchases, newCustomerPreferences);
                JOptionPane.showMessageDialog(CustomerEditWindow.this, "Edit Successful！");
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(CustomerEditWindow.this, "Are you sure to delete this customer?", "Delete", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    delete(ID);
                    JOptionPane.showMessageDialog(CustomerEditWindow.this, "Delete Successful！");
                    dispose();
                    adminWindow.refreshCardPanel();
                    config.EditWindowEnabled = false;
                }
            }
        });




        layeredPane.add(closeButton, 1);
        layeredPane.add(editButton, 1);
        layeredPane.add(this.deleteButton, 1);
        layeredPane.add(customerNameField, 1);
        layeredPane.add(customerPhoneField, 1);
        layeredPane.add(customerPurchasesListPanel, 1);
        layeredPane.add(customerPreferencesListPanel, 1);
        layeredPane.add(backgroundLabel, -1);

        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);



    }
    private void delete(int ID){
        ArrayList<String> data = new ArrayList<String>(); // 存储文件内容的数组列表

        try {
            File file = new File(config.customerDataPath);
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);

            // 将文件内容读入数组列表中
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }

            // 寻找要删除的艺术品ID并进行删除
            for (int i = 0; i < data.size(); i++) {
                String line = data.get(i);
                if (line.startsWith("ID:") && Integer.parseInt(line.split(":")[1]) == ID) {
                    // 删除要删除的行和它后面的数据行
                    data.subList(i, i+5).clear();
                    break;
                }
            }

            // 将修改后的内容写回文件中
            FileWriter writer = new FileWriter(file);
            for (String line : data) {
                writer.write(line + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
