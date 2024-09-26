import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class searchBar extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton cancelButton;
    private ImagePanel searchPanel;

    public searchBar() {

        setSize(300, 80);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setUndecorated(true);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        ImageIcon searchIconNormal = new ImageIcon("./Ico/search_normal.png");
        Image temp1 = searchIconNormal.getImage().getScaledInstance(50, 50, searchIconNormal.getImage().SCALE_DEFAULT);
        searchIconNormal = new ImageIcon(temp1);
        ImageIcon searchIconHover = new ImageIcon("./Ico/search_hover.png");
        Image temp2 = searchIconHover.getImage().getScaledInstance(50, 50, searchIconHover.getImage().SCALE_DEFAULT);
        searchIconHover = new ImageIcon(temp2);
        ImageIcon searchIconPressed = new ImageIcon("./Ico/search_pressed.png");
        Image temp3 = searchIconPressed.getImage().getScaledInstance(50, 50, searchIconPressed.getImage().SCALE_DEFAULT);
        searchIconPressed = new ImageIcon(temp3);
        ImageIcon cancelIconNormal = new ImageIcon("./Ico/search_cancel_normal.png");
        Image temp4 = cancelIconNormal.getImage().getScaledInstance(50, 50, cancelIconNormal.getImage().SCALE_DEFAULT);
        cancelIconNormal = new ImageIcon(temp4);
        ImageIcon cancelIconHover = new ImageIcon("./Ico/search_cancel_hover.png");
        Image temp5 = cancelIconHover.getImage().getScaledInstance(50, 50, cancelIconHover.getImage().SCALE_DEFAULT);
        cancelIconHover = new ImageIcon(temp5);
        ImageIcon cancelIconPressed = new ImageIcon("./Ico/search_cancel_pressed.png");
        Image temp6 = cancelIconPressed.getImage().getScaledInstance(50, 50, cancelIconPressed.getImage().SCALE_DEFAULT);
        cancelIconPressed = new ImageIcon(temp6);

        searchPanel = new ImagePanel("./ico/search_bar.png");
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setOpaque(false);
        searchPanel.setBounds(0, 0, 300, 50);


        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(140, 50));
        searchField.setFont(new Font("Arial", Font.PLAIN, 25));
        searchField.setBorder(null);
        searchField.setBackground(new Color(214, 211, 233));



        searchButton = new ImageButton(searchIconNormal, searchIconHover, searchIconPressed);
        searchButton.setPreferredSize(new Dimension(50, 50));
        cancelButton = new ImageButton(cancelIconNormal, cancelIconHover, cancelIconPressed);
        cancelButton.setPreferredSize(new Dimension(50, 50));

        searchPanel.add(cancelButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                config.IsSearchBarEnabled = false;
            }
        });//dispose the window when cancel button is clicked



        searchField.addKeyListener(new KeyAdapter() {//search when enter is pressed
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    String search = searchField.getText();
                    if (search.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter a search term");
                    } else {
                            adminWindow.handleSearchResults(search);
                    }
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {//search when search button is clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                if (search.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a search term");
                } else {
                        adminWindow.handleSearchResults(search);//call the handleSearchResults method in the adminWindow class

            }
        }
    });



        add(searchPanel);

    }
}
