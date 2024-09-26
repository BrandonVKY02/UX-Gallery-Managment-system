import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
u
// 创建一个继承自 JFrame 的类
public class adminWindow extends JFrame {

    // 定义一些组件
    private JPanel sidebar; // 侧边栏
    private static ArtworkContentPanel ArtworkList; // 内容区域uUuuuu
    private static ArtistContentPanel ArtistList;
    private static CustomerContentPanel CustomerList;
    private JPanel systemTime;//系统时间
    private ImageButton HomeButton; // Home 按钮
    private ImageButton AddButton; // Add 按钮
    private ImageButton SearchButton; //Search 按钮
    private ImageButton ListButton; // List 按钮
    private JLabel helloLabel; // 欢迎标签


    //This for artwork list
    private static String[] artworkName;
    private static String[] artworkID;
    private static String[] artworkPrice;
    private static String[] artworkArtist;
    private static String[] artworkStatus;


    //This for artist list
    private static String[] artistID;
    private static String[] artistName;
    private static String[] artistSpecialty;
    private static String[] artistAlive;
    private static String[] artistRange;
    private static String[] artistCountry;

    private static String[] customerID;
    private static String[] customerName;
    private static String[] customerPhone;

    private Point buttonLocation;

    private static adminWindow instance;
    // 构造方法，初始化窗口界面
    public  adminWindow() {

        // 设置窗口标题和大小
        setTitle("Management System");
        setSize(880, 600);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // 处理异常
        }



        // 设置窗口居中显示和关闭时退出程序
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 创建侧边栏，并设置背景颜色和布局方式为垂直排列的 BoxLayout
        sidebar = new JPanel();
        sidebar.setBackground(Color.LIGHT_GRAY);
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));



        systemTime = new JPanel();
        systemTime.setBackground(Color.darkGray);
        systemTime.setLayout(new BorderLayout());


        //创建List按钮，并添加到侧边栏中，设置对齐方式为居中对齐
        ListButton = new ImageButton(Icon.LIST_ICON_ARTWORK_NORMAL,Icon.LIST_ICON_ARTWORK_HOVER,Icon.LIST_ICON_ARTWORK_PRESSED);
        ListButton.setBorderPainted(false);
        ListButton.setBorder(null);
        ListButton.setContentAreaFilled(false);
        ListButton.setFocusPainted(false);
        sidebar.add(ListButton);
        ListButton.setAlignmentX(CENTER_ALIGNMENT);



        // 创建HOME按钮，并添加到侧边栏中，设置对齐方式为居中对齐，
        HomeButton = new ImageButton(Icon.HOME_ICON_NORMAL,Icon.HOME_ICON_HOVER,Icon.HOME_ICON_PRESSED);
        HomeButton.setBorderPainted(false);
        HomeButton.setBorder(null);
        HomeButton.setContentAreaFilled(false);
        HomeButton.setFocusPainted(false);
        sidebar.add(HomeButton);
        HomeButton.setAlignmentX(CENTER_ALIGNMENT);

        // 创建ADD按钮，并添加到侧边栏中，设置对齐方式为居中对齐
        AddButton = new ImageButton(Icon.ADD_ICON_NORMAL,Icon.ADD_ICON_HOVER,Icon.ADD_ICON_PRESSED);
        AddButton.setBorderPainted(false);
        AddButton.setBorder(null);
        AddButton.setContentAreaFilled(false);
        AddButton.setFocusPainted(false);
        sidebar.add(AddButton);
        AddButton.setAlignmentX(CENTER_ALIGNMENT);



        //创建SEARCH按钮，并添加到侧边栏中，设置对齐方式为居中对齐
        SearchButton = new ImageButton(Icon.SEARCH_ICON_NORMAL,Icon.SEARCH_ICON_HOVER,Icon.SEARCH_ICON_PRESSED);
        SearchButton.setBorderPainted(false);
        SearchButton.setBorder(null);
        SearchButton.setContentAreaFilled(false);
        SearchButton.setFocusPainted(false);
        sidebar.add(SearchButton);
        SearchButton.setAlignmentX(CENTER_ALIGNMENT);


        // 创建标签，并添加到内容区域中，设置字体大小和水平对齐方式为居中对齐
        helloLabel = new JLabel("Welcome to Management System, Administrator !");
        helloLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        systemTime.add(helloLabel,BorderLayout.WEST);



        //调用artworkFileReader方法，读取文件
        Filereader fileReader = new Filereader("artwork");
        int count = fileReader.returnCount();
        artworkName = new String[count];
        artworkID = new String[count];
        artworkPrice = new String[count];
        artworkArtist = new String[count];
        artworkStatus = new String[count];

        for(int i = 0;i < count;i ++){
            artworkName[i] = fileReader.returnArtworkName(i);
            artworkID[i] = fileReader.returnArtworkID(i);
            artworkPrice[i] = fileReader.returnArtworkPrice(i);
            artworkArtist[i] = fileReader.returnArtist(i);
            artworkStatus[i] = fileReader.returnStatus(i);
        }

        Filereader fileReader2 = new Filereader("artist");
        int count2 = fileReader2.returnCount();
        artistID = new String[count2];
        artistName = new String[count2];
        artistSpecialty = new String[count2];
        artistAlive = new String[count2];
        artistRange = new String[count2];
        artistCountry = new String[count2];

        for(int i = 0;i < count2;i ++){
            artistID[i] = fileReader2.returnArtistID(i);
            artistName[i] = fileReader2.returnArtistName(i);
            artistSpecialty[i] = fileReader2.returnArtistSpecialty(i);
            artistAlive[i] = fileReader2.returnArtistAlive(i);
            artistRange[i] = fileReader2.returnArtistRange(i);
            artistCountry[i] = fileReader2.returnArtistCountry(i);
        }

        Filereader fileReader3 = new Filereader("customer");
        int count3 = fileReader3.returnCount();
        customerID = new String[count3];
        customerName = new String[count3];
        customerPhone = new String[count3];

        for(int i = 0;i < count3;i ++){
            customerID[i] = fileReader3.returnCustomerID(i);
            customerName[i] = fileReader3.returnCustomerName(i);
            customerPhone[i] = fileReader3.returnCustomerPhone(i);
        }





        // 创建内容区域，并设置背景颜色
        ArtworkList = new ArtworkContentPanel(artworkID,artworkName,artworkArtist,artworkPrice,artworkStatus);
        ArtworkList.setBackground(Color.DARK_GRAY);

        ArtistList = new ArtistContentPanel(artistID,artistName,artistSpecialty,artistAlive,artistRange,artistCountry);
        ArtistList.setBackground(Color.DARK_GRAY);

        CustomerList = new CustomerContentPanel(customerID,customerName,customerPhone);
        CustomerList.setBackground(Color.DARK_GRAY);

        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建一个新的窗口
                if(config.AddWindowEnabled){
                    JOptionPane.showMessageDialog(null, "Please close the Add window first!");
                    return;
                }
                if(config.listType.equals("Artwork")){
                    new ArtworkAddWindow();
                    config.AddWindowEnabled = true;
                }
                else if(config.listType.equals("Artist")){
                    new ArtistAddWindow();
                    config.AddWindowEnabled = true;
                }
                else if(config.listType.equals("Customer")){
                    new CustomerAddWindow();
                    config.AddWindowEnabled = true;
                }

            }
        });


        //检测执行事件，按下HOME时，清空窗口并重绘
        HomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshCardPanel();
            }
        });

        SearchButton.addActionListener(new ActionListener() {//为搜索按钮添加监听器
            @Override
            public void actionPerformed(ActionEvent e) {//当按钮被点击时

                buttonLocation = SearchButton.getLocationOnScreen();//获取按钮相对于屏幕的位置

                if(!config.IsSearchBarEnabled) {//如果搜索栏没有打开
                    searchBar searchWindow = new searchBar();//创建一个搜索栏
                    searchWindow.setVisible(true);
                    searchWindow.setLocation(buttonLocation.x + 77,buttonLocation.y+5);//根据按钮的位置设置搜索栏的位置
                    config.IsSearchBarEnabled = true;
                }//如果搜索栏已经打开则跳过这段代码

            }
        });



        add(systemTime,BorderLayout.NORTH);
        systemTime.setPreferredSize(new Dimension(0,20));



        // 将侧边栏添加到窗口的西部（左侧），并设置宽度为100像素
        add(sidebar, BorderLayout.WEST);
        sidebar.setPreferredSize(new Dimension( 77, 0));





        // 将内容区域添加到窗口的中心



        JScrollPane artistScrollPane = new JScrollPane(ArtistList);
        JScrollPane artworkScrollPane = new JScrollPane(ArtworkList);
        JScrollPane customerScrollPane = new JScrollPane(CustomerList);
        add(artworkScrollPane, BorderLayout.CENTER);
        ListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(config.listType.equalsIgnoreCase("Artwork")){
                    config.listType = "Artist";
                    ListButton.setImageIcon(Icon.LIST_ICON_ARTIST_NORMAL,Icon.LIST_ICON_ARTIST_HOVER,Icon.LIST_ICON_ARTIST_PRESSED);
                    remove(artworkScrollPane);
                    add(artistScrollPane, BorderLayout.CENTER);
            }
              else  if(config.listType.equalsIgnoreCase("Artist")){
                    config.listType = "Customer";
                    ListButton.setImageIcon(Icon.LIST_ICON_CUSTOMER_NORMAL,Icon.LIST_ICON_CUSTOMER_HOVER,Icon.LIST_ICON_CUSTOMER_PRESSED);
                    remove(artistScrollPane);
                    add(customerScrollPane, BorderLayout.CENTER);
                }
              else if(config.listType.equalsIgnoreCase("Customer")){
                    config.listType = "Artwork";
                    ListButton.setImageIcon(Icon.LIST_ICON_ARTWORK_NORMAL,Icon.LIST_ICON_ARTWORK_HOVER,Icon.LIST_ICON_ARTWORK_PRESSED);
                    remove(customerScrollPane);
                    add(artworkScrollPane, BorderLayout.CENTER);

                }
                System.out.println(config.listType);

                refreshCardPanel();
        }
        });





        // 设置窗口可见
        setVisible(true);
        instance = this;

    }

    public static void handleSearchResults(String results) {//处理搜索结果

        if(config.listType.equalsIgnoreCase("Artwork")){
            ArtworkList.removeAll();
            ArtworkList.repaint();
            ArtworkList.validate();

            GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
            gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
            gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
            gbc.weightx = 1.0; // 充满水平空间
            gbc.gridy = 0;


            int n = 0;//记录搜索结果的数量
            int[] x = new int[artworkID.length];//记录搜索结果的位置
            for(int i = 0; i < artworkID.length; i++){//遍历所有的艺术品
                if(artworkName[i].toLowerCase().contains(results.toLowerCase())){//如果搜索结果包含搜索内容
                    x[n] = i;
                    ++n;

                }
            }
            String[] searchResultID = new String[n];
            String[] searchResultName = new String[n];
            String[] searchResultArtist = new String[n];
            String[] searchResultPrice = new String[n];
            String[] searchResultStatus = new String[n];

            for(int i = 0; i < n; i++){
                searchResultID[i] = artworkID[x[i]];
                searchResultName[i] = artworkName[x[i]];
                searchResultArtist[i] = artworkArtist[x[i]];
                searchResultPrice[i] = artworkPrice[x[i]];
                searchResultStatus[i] = artworkStatus[x[i]];

                ArtworkList.Panelupdate(searchResultID,searchResultName,searchResultArtist,searchResultPrice,searchResultStatus);

            }
            instance.setSize(instance.getWidth()+1,instance.getHeight());
        }
        else if(config.listType.equalsIgnoreCase("Artist")){
            ArtistList.removeAll();
            ArtistList.repaint();
            ArtistList.validate();

            GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
            gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
            gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
            gbc.weightx = 1.0; // 充满水平空间
            gbc.gridy = 0;


            int n = 0;//记录搜索结果的数量
            int[] x = new int[artistID.length];
            for(int i = 0; i < artistID.length; i++){//遍历所有的艺术品
                if(artistName[i].toLowerCase().contains(results.toLowerCase())){//如果搜索结果包含搜索内容
                    x[n] = i;
                    ++n;

                }
            }
            String[] searchResultID = new String[n];
            String[] searchResultName = new String[n];
            String[] searchResultSpecialty = new String[n];
            String[] searchResultAlive = new String[n];
            String[] searchResultRange = new String[n];
            String[] searchResultCountry = new String[n];


            for(int i = 0; i < n; i++){

                searchResultID[i] = artistID[x[i]];
                searchResultName[i] = artistName[x[i]];
                searchResultSpecialty[i] = artistSpecialty[x[i]];
                searchResultAlive[i] = artistAlive[x[i]];
                searchResultRange[i] = artistRange[x[i]];
                searchResultCountry[i] = artistCountry[x[i]];

                ArtistList.Panelupdate(searchResultID,searchResultName,searchResultSpecialty,searchResultAlive,searchResultRange,searchResultCountry);


            }
            instance.setSize(instance.getWidth()+1,instance.getHeight());
        }else if(config.listType.equalsIgnoreCase("Customer")){
            CustomerList.removeAll();
            CustomerList.repaint();
            CustomerList.validate();

            GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包约束
            gbc.fill = GridBagConstraints.BOTH; // 设置组件在水平和垂直方向上都铺满父容器
            gbc.insets = new Insets(5, 10, 5, 10); // 添加内边距
            gbc.weightx = 1.0; // 充满水平空间
            gbc.gridy = 0;

            int n = 0;//记录搜索结果的数量
            int[] x = new int[customerID.length];
            for(int i = 0; i < customerID.length; i++){//遍历所有的艺术品
                if(customerName[i].toLowerCase().contains(results.toLowerCase())){//如果搜索结果包含搜索内容
                    x[n] = i;
                    ++n;

                }
            }
            String[] searchResultID = new String[n];
            String[] searchResultName = new String[n];
            String[] searchResultPhone = new String[n];

            for(int i = 0; i < n; i++){

                searchResultID[i] = customerID[x[i]];
                searchResultName[i] = customerName[x[i]];
                searchResultPhone[i] = customerPhone[x[i]];

                CustomerList.Panelupdate(searchResultID,searchResultName,searchResultPhone);
            }
            instance.setSize(instance.getWidth()+1,instance.getHeight());




    }}

    public static void refreshCardPanel() {
        if(config.listType.equals("Artwork")){
        Filereader fileReader = new Filereader("artwork");
        int count = fileReader.returnCount();
        String[] artworkNameRefresh = new String[count];
        String[] artworkIDRefresh = new String[count];
        String[] artworkPriceRefresh = new String[count];
        String[] artworkArtistRefresh = new String[count];
        String[] artworkStatusRefresh = new String[count];

        for(int i = 0;i < count;i ++){
            artworkNameRefresh[i] = fileReader.returnArtworkName(i);
            artworkIDRefresh[i] = fileReader.returnArtworkID(i);
            artworkPriceRefresh[i] = fileReader.returnArtworkPrice(i);
            artworkArtistRefresh[i] = fileReader.returnArtist(i);
            artworkStatusRefresh[i] = fileReader.returnStatus(i);
        }
        ArtworkList.Panelupdate(artworkIDRefresh,artworkNameRefresh,artworkArtistRefresh,artworkPriceRefresh,artworkStatusRefresh);
        instance.setSize(instance.getWidth()+1,instance.getHeight());
        }
        if(config.listType.equals("Artist")){
            Filereader fileReader = new Filereader("artist");
            int count2 = fileReader.returnCount();
            String[] artistIDRefresh = new String[count2];
            String[] artistNameRefresh = new String[count2];
            String[] artistSpecialtyRefresh = new String[count2];
            String[] artistAliveRefresh = new String[count2];
            String[] artistRangeRefresh = new String[count2];
            String[] artistCountryRefresh = new String[count2];

            for(int i = 0;i < count2;i ++){
                artistIDRefresh[i] = fileReader.returnArtistID(i);
                artistNameRefresh[i] = fileReader.returnArtistName(i);
                artistSpecialtyRefresh[i] = fileReader.returnArtistSpecialty(i);
                artistAliveRefresh[i] = fileReader.returnArtistAlive(i);
                artistRangeRefresh[i] = fileReader.returnArtistRange(i);
                artistCountryRefresh[i] = fileReader.returnArtistCountry(i);
            }
            ArtistList.Panelupdate(artistIDRefresh,artistNameRefresh,artistSpecialtyRefresh,artistAliveRefresh,artistRangeRefresh,artistCountryRefresh);

            instance.setSize(instance.getWidth()+1,instance.getHeight());
        }
        if(config.listType.equals("Customer")){
            Filereader fileReader = new Filereader("customer");
            int count3 = fileReader.returnCount();
            String[] customerIDRefresh = new String[count3];
            String[] customerNameRefresh = new String[count3];
            String[] customerPhoneRefresh = new String[count3];


            for(int i = 0;i < count3;i ++){
                customerIDRefresh[i] = fileReader.returnCustomerID(i);
                customerNameRefresh[i] = fileReader.returnCustomerName(i);
                customerPhoneRefresh[i] = fileReader.returnCustomerPhone(i);
            }

            CustomerList.Panelupdate(customerIDRefresh,customerNameRefresh,customerPhoneRefresh);

            instance.setSize(instance.getWidth()+1,instance.getHeight());
        }

}
}