package JAVA;

import java.awt.*;
import javax.swing.*;

public class AppMessenger {
    static JPanel selectedUser = null;
    static JPanel chatBoxGlobal = null;
    static JLabel headerGlobal = null;
    public static void main(String[] args) {
    JFrame frame = createFrame();

    CardLayout layout = new CardLayout();
    JPanel mainPanel = new JPanel(layout);

    JPanel loginPanel = createMainPanel(layout, mainPanel);
    JPanel chatPanel = createChatUI();
    mainPanel.add(loginPanel, "login");
    mainPanel.add(chatPanel, "chat");
    frame.add(mainPanel);
    frame.setVisible(true);
}

    // JFrame
   public static JFrame createFrame(){
    JFrame frame = new JFrame();
    ImageIcon image = new ImageIcon("iconMessenger.jpg");

    frame.setIconImage(image.getImage());
    frame.setTitle("Messenger");

    frame.setSize(1000,700); 
    frame.setResizable(true); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setBackground(Color.white);

    frame.setLocationRelativeTo(null);

    return frame;
}

    //  PANEL CHÍNH
    public static JPanel createMainPanel(CardLayout layout, JPanel mainPanel){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // ICON
        ImageIcon image = new ImageIcon("Messengerv1.png");
        Image img = image.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel icon = new JLabel(new ImageIcon(img));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TITLE
        JLabel title = new JLabel("Welcome to Messenger");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //  DESCRIPTION
        JLabel desc = new JLabel("The simple way to text, call and video chat right from your desktop.");
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        desc.setForeground(Color.GRAY);
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        //  spacing
        panel.add(Box.createVerticalStrut(150));
        panel.add(icon);
        panel.add(Box.createVerticalStrut(20));
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(desc);

        JButton fbBtn = createFacebookButton();
        JButton emailBtn = createEmailButton();
        fbBtn.addActionListener(e -> layout.show(mainPanel, "chat"));
        emailBtn.addActionListener(e -> layout.show(mainPanel, "chat"));

        panel.add(Box.createVerticalStrut(40));
        panel.add(fbBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(emailBtn);

        return panel;
    }
    // Button Facebook
    public static JButton createFacebookButton(){
    JButton btn = new JButton("Log in with Facebook");

    btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    btn.setBackground(new Color(24, 119, 242));
    btn.setForeground(Color.WHITE);

    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setMaximumSize(new Dimension(250, 40));
    btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Hover
    btn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(20, 100, 210));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(24, 119, 242));
        }
    });

    return btn;
}

// Button Email
    public static JButton createEmailButton(){
    JButton btn = new JButton("Log in with phone or email");

    btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    btn.setBackground(new Color(240, 240, 240));
    btn.setForeground(Color.BLACK);

    btn.setFocusPainted(false);
    btn.setMaximumSize(new Dimension(250, 40));
    btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Hover
    btn.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(220, 220, 220));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            btn.setBackground(new Color(240, 240, 240));
        }
    });
    
    return btn;
}
// FULL CHAT UI
public static JPanel createChatUI(){
    JPanel panel = new JPanel(new BorderLayout());

    JPanel sidebar = createSidebar();

    //  PANEL CHUYỂN ĐỔI 
    CardLayout leftLayout = new CardLayout();
    JPanel leftPanel = new JPanel(leftLayout);

    JPanel chatList = createChatList();
    JPanel peoplePanel = createPeoplePanel();

    leftPanel.add(chatList, "chat");
    leftPanel.add(peoplePanel, "people");

    // chia layout
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, createChatArea());
    split.setDividerLocation(300);

    panel.add(sidebar, BorderLayout.WEST);
    panel.add(split, BorderLayout.CENTER);

    // truyền layout để click đổi
    setupMenuActions(sidebar, leftLayout, leftPanel);

    return panel;
}
public static JPanel createPeoplePanel(){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(new JLabel("People List"));
    panel.add(new JLabel("User A"));
    panel.add(new JLabel("User B"));

    return panel;
}
public static void setupMenuActions(JPanel sidebar, CardLayout layout, JPanel panel){
    for(Component comp : sidebar.getComponents()){
        if(comp instanceof JPanel){
            JPanel item = (JPanel) comp;

            for(Component c : item.getComponents()){
                if(c instanceof JLabel){
                    JLabel label = (JLabel) c;

                    String text = label.getText();

                    if("Chat".equals(text)){
                        item.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent e) {
                                layout.show(panel, "chat");
                            }
                        });
                    }

                    if("People".equals(text)){
                        item.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent e) {
                                layout.show(panel, "people");
                            }
                        });
                    }
                }
            }
        }
    }
}
// SIDEBAR
public static JPanel createSidebar(){
    JPanel sidebar = new JPanel();
    sidebar.setBackground(new Color(245,245,245));
    sidebar.setLayout(new BorderLayout());

    // TOP - Icon Messenger
    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(245,245,245));

    ImageIcon iconImg = new ImageIcon("Messengerv1.png");
    Image img = iconImg.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    JLabel icon = new JLabel(new ImageIcon(img));

    topPanel.add(icon);
    sidebar.add(topPanel, BorderLayout.NORTH);

    // CENTER - Menu
    JPanel menuPanel = new JPanel();
    menuPanel.setBackground(new Color(245,245,245));
    menuPanel.setLayout(new GridLayout(6, 1));

    menuPanel.add(new JLabel()); // khoảng trống trên

    menuPanel.add(createMenuItem("Chat", "iconchat.png")); 
    menuPanel.add(createMenuItem("People", "crowd.png"));
    menuPanel.add(createMenuItem("Shop", "shop.png"));
    menuPanel.add(createMenuItem("Box", "box.png"));

    menuPanel.add(new JLabel()); // khoảng trống dưới

    sidebar.add(menuPanel, BorderLayout.CENTER);
    
    JPanel bottomPanel = new JPanel();
    sidebar.add(bottomPanel, BorderLayout.SOUTH);
    bottomPanel.setBackground(new Color(245,245,245));
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
    bottomPanel.add(Box.createVerticalGlue());
    JPanel settingItem = createMenuItem("Settings", "setting.png");

// căn giữa
settingItem.setAlignmentX(Component.CENTER_ALIGNMENT);

bottomPanel.add(settingItem);
bottomPanel.add(Box.createVerticalStrut(15));

    return sidebar;
}
public static JPanel createMenuItem(String text, String iconPath){
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    panel.setBackground(new Color(245,245,245));
    panel.setMaximumSize(new Dimension(100, 50));

    // ICON
    ImageIcon iconImg = new ImageIcon(iconPath);
    Image img = iconImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    JLabel icon = new JLabel(new ImageIcon(img));

    //  TEXT
    JLabel label = new JLabel(text);
    label.setFont(new Font("Segoe UI", Font.BOLD, 14));

    if(text.equals("Chat")){
        label.setForeground(new Color(24,119,242)); // xanh
    } else {
        label.setForeground(new Color(80,80,80));
    }

    panel.add(icon);
    panel.add(label);

    return panel;
}

//  LIST CHAT
public static JPanel createChatList(){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.WHITE);
    

    //  HEADER
    JPanel header = new JPanel(new BorderLayout());
    header.setBackground(Color.WHITE);
    header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

    JLabel title = new JLabel("Chats");
    title.setFont(new Font("Segoe UI", Font.BOLD, 20));

    JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    actions.setBackground(Color.WHITE);

    JButton videoBtn = new JButton();
    JButton addBtn = new JButton();


    ImageIcon videoIcon = new ImageIcon("video.png");
    Image vImg = videoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    videoBtn.setIcon(new ImageIcon(vImg));

    ImageIcon addIcon = new ImageIcon("plus.png");
    Image aImg = addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    addBtn.setIcon(new ImageIcon(aImg));

    for(JButton btn : new JButton[]{videoBtn, addBtn}){
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        videoBtn.setPreferredSize(new Dimension(35, 35));
        addBtn.setPreferredSize(new Dimension(35, 35));
    }

    actions.add(videoBtn);
    actions.add(addBtn);

    header.add(title, BorderLayout.WEST);
    header.add(actions, BorderLayout.EAST);

    // SEARCH BAR
    JPanel searchPanel = new JPanel(new BorderLayout()){
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(240,240,240));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // bo tròn
    }
};
    searchPanel.setOpaque(false);
    searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    searchPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
    ImageIcon searchIcon = new ImageIcon("search.png");
    Image sImg = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    JLabel icon = new JLabel(new ImageIcon(sImg));
    JTextField searchField = new JTextField("Search messenger...");
    searchField.setBorder(null);
    searchField.setOpaque(false);
    searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    searchField.setForeground(Color.GRAY);
    searchPanel.add(icon, BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);

    // LIST USER
    JPanel list = new JPanel();
    list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
    list.setBackground(Color.WHITE);

    list.add(createUserItem("Đô mixue", "Chưa tày đâu", "chua tay dau.jpg", true));
    list.add(Box.createVerticalStrut(5));
    list.add(createUserItem("Poor Vũ", "Không phải anh ơi", "matkhoga.jpg", false));
    list.add(Box.createVerticalStrut(5));
    list.add(createUserItem("Quỷ Đỏ", "Anh sắp chet roi em ơi", "quydo.jpg", false));
    list.add(Box.createVerticalStrut(5));
    list.add(createUserItem("Siêu Bợ", "Em fan anh", "kho ga.jpg", false));
    list.add(Box.createVerticalStrut(5));
    list.add(createUserItem("Alo Vu a Vu", "Đừng có chối em ơi", "ech.jpg", false));
    list.add(Box.createVerticalStrut(5));

    panel.add(Box.createVerticalStrut(10));
    panel.add(header);
    panel.add(Box.createVerticalStrut(10));
    panel.add(Box.createVerticalStrut(10));
    panel.add(searchPanel);
    panel.add(list);
    
    

    return panel;
}
public static JPanel createUserItem(String name, String message, String avatarPath, boolean isSelected){
    
    JPanel wrapper = new JPanel(new BorderLayout());
    wrapper.setOpaque(false);
    wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
    wrapper.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

    JPanel panel = new JPanel(new BorderLayout()){
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(this == selectedUser){
            g2.setColor(new Color(64,93,230)); // xanh
        } else {
            g2.setColor(Color.WHITE); // luôn trắng
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();

        
    }
};
   wrapper.addMouseListener(new java.awt.event.MouseAdapter() {

    public void mouseClicked(java.awt.event.MouseEvent e) {
    if(selectedUser == panel){
        selectedUser = null;
        panel.repaint();

        // reset chat
        chatBoxGlobal.removeAll();
        headerGlobal.setText("Select a user");
        chatBoxGlobal.revalidate();
        chatBoxGlobal.repaint();
        return;
    }
    if(selectedUser != null){
        selectedUser.repaint();
    }

    selectedUser = panel;
    panel.repaint();

    loadChat(name);
}

    public void mouseEntered(java.awt.event.MouseEvent e) {
        if(panel != selectedUser){
            panel.setBackground(new Color(240,240,240));
            panel.repaint();
        }
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
        if(panel != selectedUser){
            panel.setBackground(Color.WHITE);
            panel.repaint();
        }
    }
});

    
    //  AVATAR
    ImageIcon avatarImg = new ImageIcon(avatarPath);
    Image img = avatarImg.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
    JLabel avatar = new JLabel(new ImageIcon(img)){
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
        super.paintComponent(g2);
        g2.dispose();
    }
};

    // TEXT
    JPanel textPanel = new JPanel();
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
    textPanel.setOpaque(false);

    JLabel nameLabel = new JLabel(name);
    nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

    JLabel msgLabel = new JLabel(message);
    msgLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

    
        nameLabel.setForeground(Color.BLACK);
        msgLabel.setForeground(Color.GRAY);

    textPanel.add(nameLabel);
    textPanel.add(msgLabel);

    // layout
    panel.setLayout(new BorderLayout(10, 0));
    panel.add(avatar, BorderLayout.WEST);
    panel.add(textPanel, BorderLayout.CENTER);
    wrapper.add(panel);



    return wrapper;
}

public static JPanel createChatArea(){
    JPanel panel = new JPanel(new BorderLayout());
    headerGlobal = new JLabel("Select a user");
    headerGlobal.setFont(new Font("Segoe UI", Font.BOLD, 18));
    headerGlobal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    panel.add(headerGlobal, BorderLayout.NORTH);

    //  chat box
    chatBoxGlobal = new JPanel();
    chatBoxGlobal.setLayout(new BoxLayout(chatBoxGlobal, BoxLayout.Y_AXIS));

    JScrollPane scroll = new JScrollPane(chatBoxGlobal);
    panel.add(scroll, BorderLayout.CENTER);

    //  input
    JPanel input = new JPanel(new BorderLayout());
    input.add(new JTextField(), BorderLayout.CENTER);
    input.add(new JButton("Send"), BorderLayout.EAST);

    panel.add(input, BorderLayout.SOUTH);

    return panel;
}


// MESSAGE
public static JPanel createMessage(String text, boolean isSender){
    JPanel panel = new JPanel(new FlowLayout(isSender ? FlowLayout.RIGHT : FlowLayout.LEFT));

    JLabel label = new JLabel(text);
    label.setOpaque(true);
    label.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

    if(isSender){
        label.setBackground(new Color(64,93,230));
        label.setForeground(Color.WHITE);
    } else {
        label.setBackground(Color.LIGHT_GRAY);
    }

    panel.add(label);
    return panel;
}
// LOAD CHAT
public static void loadChat(String userName){
    chatBoxGlobal.removeAll();
    headerGlobal.setText(userName);

    if(userName.equals("Đô mixue")){
        chatBoxGlobal.add(createMessage("Chưa tày đâu", false));

    }
    else if(userName.equals("Poor Vũ")){
        chatBoxGlobal.add(createMessage("Không phải anh ơi", false));
    }
    else if(userName.equals("Quỷ Đỏ")){
        chatBoxGlobal.add(createMessage("Anh sắp chết rồi em ơi", false));
    }
    else if(userName.equals("Siêu Bợ")){
        chatBoxGlobal.add(createMessage("Em fan anh", false));
    }
    else if(userName.equals("Alo Vu a Vu")){
        chatBoxGlobal.add(createMessage("Đừng có chối em ơi", false));
    }

    chatBoxGlobal.revalidate();
    chatBoxGlobal.repaint();
}
}
