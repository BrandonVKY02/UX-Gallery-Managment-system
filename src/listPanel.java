import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class listPanel extends JPanel {
    public listPanel(String List1, String List2, String List3, String List4,String List5) {

        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        if(config.listType.equals("Artwork")){
            addComponentListener(new ComponentAdapter() {//This is the listener that will resize the panel when the window is resized
                @Override
                public void componentResized(ComponentEvent e) {

                    removeAll();//This removes all the components from the panel so that they can be readded with the new size
                    repaint();//This repaints the panel so that the components are removed

                    int width = (getWidth()-(4*5));//This gets the width of the panel and subtracts the width of the delimiters

                    JPanel delimiter1 = new JPanel();//This creates the first delimiter
                    delimiter1.setBounds(0, 0, 5, 50);
                    delimiter1.setPreferredSize(new Dimension(5, 50));
                    delimiter1.setMaximumSize(getPreferredSize());
                    delimiter1.setMinimumSize(getPreferredSize());
                    delimiter1.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter2 = new JPanel();//This creates the second delimiter
                    delimiter2.setBounds(0, 0, 5, 50);
                    delimiter2.setPreferredSize(new Dimension(5, 50));
                    delimiter2.setMaximumSize(getPreferredSize());
                    delimiter2.setMinimumSize(getPreferredSize());
                    delimiter2.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter3 = new JPanel();//This creates the third delimiter
                    delimiter3.setBounds(0, 0, 5, 50);
                    delimiter3.setMaximumSize(getPreferredSize());
                    delimiter3.setMinimumSize(getPreferredSize());
                    delimiter3.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter4 = new JPanel();
                    delimiter4.setBounds(0, 0, 5, 50);
                    delimiter4.setMaximumSize(getPreferredSize());
                    delimiter4.setMinimumSize(getPreferredSize());
                    delimiter4.setBackground(new Color(180, 180, 180, 255));



                    JPanel IDPanel = new JPanel();//This creates the panel that will hold the ID
                    IDPanel.setPreferredSize(new Dimension(width/10, 50));
                    IDPanel.setMaximumSize(getPreferredSize());
                    IDPanel.setMinimumSize(getPreferredSize());
                    IDPanel.setBackground(new Color(0, 0, 0, 150));
                    IDPanel.setLayout(new BorderLayout());
                    JLabel IDLabel = new JLabel(List1);
                    IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    IDLabel.setForeground(Color.WHITE);
                    IDLabel.setHorizontalAlignment(JLabel.CENTER);
                    IDPanel.add(IDLabel, BorderLayout.CENTER);

                    JPanel NamePanel = new JPanel();//This creates the panel that will hold the name
                    NamePanel.setPreferredSize(new Dimension((width*4)/10, 50));
                    NamePanel.setMaximumSize(getPreferredSize());
                    NamePanel.setMinimumSize(getPreferredSize());
                    NamePanel.setBackground(new Color(0, 0, 0, 150));
                    NamePanel.setLayout(new BorderLayout());
                    JLabel NameLabel = new JLabel(List2);
                    NameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    NameLabel.setForeground(Color.WHITE);
                    NameLabel.setHorizontalAlignment(JLabel.CENTER);
                    NamePanel.add(NameLabel, BorderLayout.CENTER);

                    JPanel ArtistPanel = new JPanel();//This creates the panel that will hold the artist
                    ArtistPanel.setPreferredSize(new Dimension((width*2)/10, 50));
                    ArtistPanel.setMaximumSize(getPreferredSize());
                    ArtistPanel.setMinimumSize(getPreferredSize());
                    ArtistPanel.setBackground(new Color(0, 0, 0, 150));
                    ArtistPanel.setLayout(new BorderLayout());
                    JLabel ArtistLabel = new JLabel(List4);
                    ArtistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    ArtistLabel.setForeground(Color.WHITE);
                    ArtistLabel.setHorizontalAlignment(JLabel.CENTER);
                    ArtistPanel.add(ArtistLabel, BorderLayout.CENTER);

                    JPanel PricePanel = new JPanel();//This creates the panel that will hold the price
                    PricePanel.setPreferredSize(new Dimension((width*2)/10, 50));
                    PricePanel.setMaximumSize(getPreferredSize());
                    PricePanel.setMinimumSize(getPreferredSize());
                    PricePanel.setBackground(new Color(0, 0, 0, 150));
                    PricePanel.setLayout(new BorderLayout());
                    JLabel PriceLabel = new JLabel(List3);
                    PriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    PriceLabel.setForeground(Color.WHITE);
                    PriceLabel.setHorizontalAlignment(JLabel.CENTER);
                    PricePanel.add(PriceLabel, BorderLayout.CENTER);

                    JPanel StatusPanel = new JPanel();//This creates the panel that will hold the status
                    StatusPanel.setPreferredSize(new Dimension((width*1)/10, 50));
                    StatusPanel.setMaximumSize(getPreferredSize());
                    StatusPanel.setMinimumSize(getPreferredSize());
                    StatusPanel.setBackground(new Color(0, 0, 0, 150));
                    StatusPanel.setLayout(new BorderLayout());
                    JLabel SellOrOntLabel = new JLabel(List5);
                    SellOrOntLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    SellOrOntLabel.setForeground(Color.WHITE);
                    SellOrOntLabel.setHorizontalAlignment(JLabel.CENTER);
                    StatusPanel.add(SellOrOntLabel, BorderLayout.CENTER);


                    //This adds all the components to the panel
                    add(IDPanel);
                    add(delimiter1);
                    add(NamePanel);
                    add(delimiter2);
                    add(ArtistPanel);
                    add(delimiter3);
                    add(PricePanel);
                    add(delimiter4);
                    add(StatusPanel);
                }
            });
        }
        if(config.listType.equals("Artist")){
            addComponentListener(new ComponentAdapter() {//This is the listener that will resize the panel when the window is resized
                @Override
                public void componentResized(ComponentEvent e) {

                    removeAll();//This removes all the components from the panel so that they can be readded with the new size
                    repaint();//This repaints the panel so that the components are removed

                    int width = (getWidth()-(4*5));//This gets the width of the panel and subtracts the width of the delimiters

                    JPanel delimiter1 = new JPanel();//This creates the first delimiter
                    delimiter1.setBounds(0, 0, 5, 50);
                    delimiter1.setPreferredSize(new Dimension(5, 50));
                    delimiter1.setMaximumSize(getPreferredSize());
                    delimiter1.setMinimumSize(getPreferredSize());
                    delimiter1.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter2 = new JPanel();//This creates the second delimiter
                    delimiter2.setBounds(0, 0, 5, 50);
                    delimiter2.setPreferredSize(new Dimension(5, 50));
                    delimiter2.setMaximumSize(getPreferredSize());
                    delimiter2.setMinimumSize(getPreferredSize());
                    delimiter2.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter3 = new JPanel();//This creates the third delimiter
                    delimiter3.setBounds(0, 0, 5, 50);
                    delimiter3.setMaximumSize(getPreferredSize());
                    delimiter3.setMinimumSize(getPreferredSize());
                    delimiter3.setBackground(new Color(180, 180, 180, 255));

                    JPanel delimiter4 = new JPanel();
                    delimiter4.setBounds(0, 0, 5, 50);
                    delimiter4.setMaximumSize(getPreferredSize());
                    delimiter4.setMinimumSize(getPreferredSize());
                    delimiter4.setBackground(new Color(180, 180, 180, 255));



                    JPanel List1Panel = new JPanel();//This creates the panel that will hold the ID
                    List1Panel.setPreferredSize(new Dimension((width*3)/10, 50));
                    List1Panel.setMaximumSize(getPreferredSize());
                    List1Panel.setMinimumSize(getPreferredSize());
                    List1Panel.setBackground(new Color(0, 0, 0, 150));
                    List1Panel.setLayout(new BorderLayout());
                    JLabel IDLabel = new JLabel(List1);
                    IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    IDLabel.setForeground(Color.WHITE);
                    IDLabel.setHorizontalAlignment(JLabel.CENTER);
                    List1Panel.add(IDLabel, BorderLayout.CENTER);

                    JPanel List2Panel = new JPanel();//This creates the panel that will hold the name
                    List2Panel.setPreferredSize(new Dimension((width*2)/10, 50));
                    List2Panel.setMaximumSize(getPreferredSize());
                    List2Panel.setMinimumSize(getPreferredSize());
                    List2Panel.setBackground(new Color(0, 0, 0, 150));
                    List2Panel.setLayout(new BorderLayout());
                    JLabel NameLabel = new JLabel(List2);
                    NameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    NameLabel.setForeground(Color.WHITE);
                    NameLabel.setHorizontalAlignment(JLabel.CENTER);
                    List2Panel.add(NameLabel, BorderLayout.CENTER);

                    JPanel List3Panel = new JPanel();//This creates the panel that will hold the artist
                    List3Panel.setPreferredSize(new Dimension((width*2)/10, 50));
                    List3Panel.setMaximumSize(getPreferredSize());
                    List3Panel.setMinimumSize(getPreferredSize());
                    List3Panel.setBackground(new Color(0, 0, 0, 150));
                    List3Panel.setLayout(new BorderLayout());
                    JLabel ArtistLabel = new JLabel(List4);
                    ArtistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    ArtistLabel.setForeground(Color.WHITE);
                    ArtistLabel.setHorizontalAlignment(JLabel.CENTER);
                    List3Panel.add(ArtistLabel, BorderLayout.CENTER);

                    JPanel List4Panel = new JPanel();//This creates the panel that will hold the price
                    List4Panel.setPreferredSize(new Dimension((width*2)/10, 50));
                    List4Panel.setMaximumSize(getPreferredSize());
                    List4Panel.setMinimumSize(getPreferredSize());
                    List4Panel.setBackground(new Color(0, 0, 0, 150));
                    List4Panel.setLayout(new BorderLayout());
                    JLabel PriceLabel = new JLabel(List3);
                    PriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    PriceLabel.setForeground(Color.WHITE);
                    PriceLabel.setHorizontalAlignment(JLabel.CENTER);
                    List4Panel.add(PriceLabel, BorderLayout.CENTER);

                    JPanel List5Panel = new JPanel();//This creates the panel that will hold the status
                    List5Panel.setPreferredSize(new Dimension((width*1)/10, 50));
                    List5Panel.setMaximumSize(getPreferredSize());
                    List5Panel.setMinimumSize(getPreferredSize());
                    List5Panel.setBackground(new Color(0, 0, 0, 150));
                    List5Panel.setLayout(new BorderLayout());
                    JLabel SellOrOntLabel = new JLabel(List5);
                    SellOrOntLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    SellOrOntLabel.setForeground(Color.WHITE);
                    SellOrOntLabel.setHorizontalAlignment(JLabel.CENTER);
                    List5Panel.add(SellOrOntLabel, BorderLayout.CENTER);


                    //This adds all the components to the panel
                    add(List1Panel);
                    add(delimiter1);
                    add(List2Panel);
                    add(delimiter2);
                    add(List3Panel);
                    add(delimiter3);
                    add(List4Panel);
                    add(delimiter4);
                    add(List5Panel);
                }
            });
        }




        setOpaque(false);
        setVisible(true);
    }


    public listPanel(String List1,String List2,String List3){
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addComponentListener(new ComponentAdapter() {//This is the listener that will resize the panel when the window is resized
            @Override
            public void componentResized(ComponentEvent e) {

                removeAll();//This removes all the components from the panel so that they can be readded with the new size
                repaint();//This repaints the panel so that the components are removed

                int width = (getWidth()-(2*5));//This gets the width of the panel and subtracts the width of the delimiters

                JPanel delimiter1 = new JPanel();//This creates the first delimiter
                delimiter1.setBounds(0, 0, 5, 50);
                delimiter1.setPreferredSize(new Dimension(5, 50));
                delimiter1.setMaximumSize(getPreferredSize());
                delimiter1.setMinimumSize(getPreferredSize());
                delimiter1.setBackground(new Color(180, 180, 180, 255));

                JPanel delimiter2 = new JPanel();//This creates the second delimiter
                delimiter2.setBounds(0, 0, 5, 50);
                delimiter2.setPreferredSize(new Dimension(5, 50));
                delimiter2.setMaximumSize(getPreferredSize());
                delimiter2.setMinimumSize(getPreferredSize());
                delimiter2.setBackground(new Color(180, 180, 180, 255));





                JPanel List1Panel = new JPanel();//This creates the panel that will hold the ID
                List1Panel.setPreferredSize(new Dimension((width*1)/10, 50));
                List1Panel.setMaximumSize(getPreferredSize());
                List1Panel.setMinimumSize(getPreferredSize());
                List1Panel.setBackground(new Color(0, 0, 0, 150));
                List1Panel.setLayout(new BorderLayout());
                JLabel IDLabel = new JLabel(List1);
                IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                IDLabel.setForeground(Color.WHITE);
                IDLabel.setHorizontalAlignment(JLabel.CENTER);
                List1Panel.add(IDLabel, BorderLayout.CENTER);

                JPanel List2Panel = new JPanel();//This creates the panel that will hold the name
                List2Panel.setPreferredSize(new Dimension((width*3)/10, 50));
                List2Panel.setMaximumSize(getPreferredSize());
                List2Panel.setMinimumSize(getPreferredSize());
                List2Panel.setBackground(new Color(0, 0, 0, 150));
                List2Panel.setLayout(new BorderLayout());
                JLabel NameLabel = new JLabel(List2);
                NameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                NameLabel.setForeground(Color.WHITE);
                NameLabel.setHorizontalAlignment(JLabel.CENTER);
                List2Panel.add(NameLabel, BorderLayout.CENTER);

                JPanel List3Panel = new JPanel();//This creates the panel that will hold the artist

                List3Panel.setBackground(new Color(0, 0, 0, 150));
                List3Panel.setLayout(new BorderLayout());
                JLabel ArtistLabel = new JLabel(List3);
                ArtistLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                ArtistLabel.setForeground(Color.WHITE);
                ArtistLabel.setHorizontalAlignment(JLabel.CENTER);
                List3Panel.add(ArtistLabel, BorderLayout.CENTER);




                //This adds all the components to the panel
                add(List1Panel);
                add(delimiter1);
                add(List2Panel);
                add(delimiter2);
                add(List3Panel);


            }
        });
        setOpaque(false);
        setVisible(true);
    }

}
