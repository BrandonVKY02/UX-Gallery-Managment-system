import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAddWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JButton closeButton = new JButton("Exit");//关闭按钮
    private JButton addButton = new JButton("ADD");//编辑按钮

    private JLabel backgroundLabel;

    private JTextField customerNameField;
    private JTextField customerPhoneField;
    private DefaultListModel<String> customerPurchasesListModel;
    private JList<String> customerPurchasesList;

    private DefaultListModel<String> customerPreferencesListModel;
    private JList<String> customerPreferencesList;



    int ID;
    private Point pressedPoint = null; // 鼠标按下时的坐标

    public CustomerAddWindow() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        layeredPane = getLayeredPane();
        setSize(710, 470);



        closeButton.setBounds(640, 10, 60, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });

        addButton.setBounds(710 - (100 + 10), 470 - 40, 100, 30);
        addButton.setUI((ButtonUI) UIManager.getUI(addButton));


        backgroundLabel = new JLabel(new ImageIcon(config.CustomerInfoWindowBackgroundPath));
        backgroundLabel.setSize(710, 480);

        Filereader fileReader = new Filereader("customer");
        ID = fileReader.returnCount();



        customerNameField = new JTextField();
        customerNameField.setBounds(260, 73, 350, 50);
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 30));
        customerNameField.setForeground(Color.WHITE);
        customerNameField.setOpaque(false);
        customerNameField.setBorder(null);

        customerPhoneField = new JTextField();
        customerPhoneField.setBounds(260, 148, 350, 50);
        customerPhoneField.setFont(new Font("Arial", Font.PLAIN, 30));
        customerPhoneField.setForeground(Color.WHITE);
        customerPhoneField.setOpaque(false);
        customerPhoneField.setBorder(null);



        //Create Purchases List
        customerPurchasesListModel = new DefaultListModel<>();
        customerPurchasesList = new JList<>(customerPurchasesListModel);
        JPanel customerPurchasesListPanel = new JPanel(new BorderLayout());
        customerPurchasesListPanel.setBounds(90, 260, 305 - 90, 375 - 260);
        customerPurchasesListPanel.add(new JScrollPane(customerPurchasesList), BorderLayout.CENTER);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newOption = JOptionPane.showInputDialog(CustomerAddWindow.this,
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


        // Create Preferences List
        customerPreferencesListModel = new DefaultListModel<>();
        customerPreferencesList = new JList<>(customerPreferencesListModel);
        JPanel customerPreferencesListPanel = new JPanel(new BorderLayout());
        customerPreferencesListPanel.setBounds(400, 260, 305 - 90, 375 - 260);
        customerPreferencesListPanel.add(new JScrollPane(customerPreferencesList), BorderLayout.CENTER);
        JButton addButton2 = new JButton("Add");
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newOption = JOptionPane.showInputDialog(CustomerAddWindow.this,
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

        this.addButton.addActionListener(new ActionListener() {
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
                customerManager.addCustomer(ID, newCustomerName, newCustomerPhone, newCustomerPurchases, newCustomerPreferences);
                JOptionPane.showMessageDialog(CustomerAddWindow.this, "Edit Successful！");
                dispose();
                adminWindow.refreshCardPanel();
                config.EditWindowEnabled = false;
            }
        });





        layeredPane.add(closeButton, 1);
        layeredPane.add(this.addButton, 1);
        layeredPane.add(customerNameField, 1);
        layeredPane.add(customerPhoneField, 1);
        layeredPane.add(customerPurchasesListPanel, 1);
        layeredPane.add(customerPreferencesListPanel, 1);
        layeredPane.add(backgroundLabel, -1);

        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
