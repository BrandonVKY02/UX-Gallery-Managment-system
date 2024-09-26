import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerContentPanel extends JPanel {

    public static JPanel Instance;
    public CustomerContentPanel(String[] customerID, String[] customerName, String[] customerPhone){
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
        gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
        gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
        gbc.weightx = 1.0; // 充满水平空间
        listPanel listHead = new listPanel("ID","Customer Name","Customer PhoneNumber");
        listHead.setBackground(new Color(0,0,0,100));
        listHead.setPreferredSize(new Dimension(0, 50)); // set the size of the list head
        listHead.setLayout(new BoxLayout(listHead, BoxLayout.X_AXIS));
        gbc.gridx = 0; // 每行只有一个card
        gbc.gridy = 0;
        gbc.ipady = 1; // 让每个card的高度随窗口改变
        add(listHead,gbc);
        for(int i = 0; i < customerID.length; i++){
            listPanel cardPage = new listPanel(customerID[i],customerName[i],customerPhone[i]);
            cardPage.setBackground(new Color(0,0,0,100));
            cardPage.setPreferredSize(new Dimension(0, 50)); // 设置小窗口的大小
            cardPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int finalI = i;
            cardPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!config.EditWindowEnabled) {
                        super.mouseClicked(e);
                        new CustomerEditWindow(customerID[finalI]);
                        config.EditWindowEnabled = true;

                    }else {
                        JOptionPane.showMessageDialog(null, "Please close the edit window first!");
                    }
                }
            });
            gbc.gridx = 0; // 每行只有一个card
            gbc.gridy = i+1;
            gbc.ipady = 1; // 让每个card的高度随窗口改变
            add(cardPage,gbc);
        }
        Instance = this;


    }

    public static void Panelupdate(String[] customerID, String[] customerName, String[] customerPhone){

        Instance.removeAll();

        GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
        gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
        gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
        gbc.weightx = 1.0; // 充满水平空间
        listPanel listHead = new listPanel("ID","Customer Name","Customer PhoneNumber");
        listHead.setBackground(new Color(0,0,0,100));
        listHead.setPreferredSize(new Dimension(0, 50)); // set the size of the list head
        listHead.setLayout(new BoxLayout(listHead, BoxLayout.X_AXIS));
        gbc.gridx = 0; // 每行只有一个card
        gbc.gridy = 0;
        gbc.ipady = 1; // 让每个card的高度随窗口改变
        Instance.add(listHead,gbc);
        for(int i = 0; i < customerID.length; i++){
            listPanel cardPage = new listPanel(customerID[i],customerName[i],customerPhone[i]);
            cardPage.setBackground(new Color(0,0,0,100));
            cardPage.setPreferredSize(new Dimension(0, 50)); // 设置小窗口的大小
            cardPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            int finalI = i;
            cardPage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!config.EditWindowEnabled) {
                        super.mouseClicked(e);
                        new CustomerEditWindow(customerID[finalI]);
                        config.EditWindowEnabled = true;

                    }else {
                        JOptionPane.showMessageDialog(null, "Please close the edit window first!");
                    }
                }
            });
            gbc.gridx = 0; // 每行只有一个card
            gbc.gridy = i+1;
            gbc.ipady = 1; // 让每个card的高度随窗口改变
            Instance.add(cardPage,gbc);
        }
        Instance.revalidate();
        Instance.repaint();
    }
}
