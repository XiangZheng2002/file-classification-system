// import setANDread;
// import Operation;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComboBox;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;


public class GUI{
    public static String fileClass;
    public static void main(String[] args){
        new FrameMain();
    }
}



class FrameMain extends JFrame implements ActionListener{
    // 元件初始化
    JPanel choice, action;
    JLabel jl2;
    ButtonGroup bg;
    JRadioButton choice1, choice2, choice3, choice4;
    JButton button1, button2, button3;
    JFrame fr;

    public FrameMain(){
        // 初始化JFrame
        fr = new JFrame("Ashley文件分类拷贝系统");
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());

        // 设置标题
        JLabel title = new JLabel("Ashley文件分类拷贝系统",JLabel.CENTER);
        Font fnt = new Font("微软雅黑", Font.ITALIC+Font.BOLD, 16);
        title.setFont(fnt);
        title.setForeground(Color.red);
        // fr.add(title);
        // fr.setLocation(100,100);
        title.setPreferredSize(new Dimension(500, 100));


        // 设置文件属性单选行
        bg = new ButtonGroup();
        choice = new JPanel();
        choice.setLayout(new FlowLayout());
        jl2 = new JLabel("文件类别");
        choice1 = new JRadioButton("文档");
        choice2 = new JRadioButton("照片");
        choice3 = new JRadioButton("视频");
        choice4 = new JRadioButton("音频");
        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);
        choice.add(jl2);
        choice.add(choice1);
        choice.add(choice2);
        choice.add(choice3);
        choice.add(choice4);

        // 属性设置和文件分类3个button
        action = new JPanel();
        action.setLayout(new FlowLayout());
        button1 = new JButton("属性设置");
        button2 = new JButton("文件分类");
        button3 = new JButton("属性查看");
        action.add(button1);
        action.add(button2);
        action.add(button3);

        // 布局
        choice.setPreferredSize(new Dimension(500, 100));
        action.setPreferredSize(new Dimension(500, 100));
        fr.add(BorderLayout.NORTH, title);
        fr.add(BorderLayout.CENTER, choice);
        fr.add(BorderLayout.SOUTH, action);

        // Add actions
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        choice1.addActionListener(this);
        choice2.addActionListener(this);
        choice3.addActionListener(this);
        choice4.addActionListener(this);


        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == choice1) {
            GUI.fileClass = "TXT";
            System.out.println(GUI.fileClass);
        }
        // 单个设置 Action
        else if(e.getSource() == choice2){
            GUI.fileClass = "Pic";
        }
        // 返回 Action
        else if(e.getSource() == choice3){
            GUI.fileClass = "Video";
        }
        else if(e.getSource() == choice4){
            GUI.fileClass = "Music";
        }
        // 批量设置 Action
        else if (e.getSource() == button1) {
            FrameAttr frameAttr = new FrameAttr();
            frameAttr.setVisible(true);
            fr.setVisible(false); // 隐藏主页面
        }
        // 单个设置 Action
        else if(e.getSource() == button2){
            FrameClassifyBatch frameClassifyBatch = new FrameClassifyBatch();
            frameClassifyBatch.setVisible(true);
            fr.setVisible(false); // 隐藏主页面
        }
        // 返回 Action
        else if(e.getSource() == button3){
            FrameCheckAttr frameCheckAttr = new FrameCheckAttr();
            frameCheckAttr.setVisible(true);
            fr.setVisible(false); // 隐藏主页面
        }

    }
};



class FrameAttr extends JFrame implements ActionListener{
    JFrame fr;
    JPanel batchSet, singleSet, backSet;
    JButton batchBotton, singleBotton, back;

    FrameAttr(){
        // 变量初始化
        // set Frame
        String name = "属性设置界面";
        fr = new JFrame(name);
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());

        // set JPanel batchSet
        batchSet = new JPanel();
        batchSet.setLayout(new FlowLayout());
        batchBotton = new JButton("批量设置");
        batchSet.add(batchBotton);

        // set JPanel singleSet
        singleSet = new JPanel();
        singleSet.setLayout(new FlowLayout());
        singleBotton = new JButton("单个设置");
        singleSet.add(singleBotton);

        // set JPanel singleSet
        backSet = new JPanel();
        backSet.setLayout(new FlowLayout());
        back = new JButton("   返  回   ");
        backSet.add(back);

        // 布局
        batchSet.setPreferredSize(new Dimension(500, 100));
        singleSet.setPreferredSize(new Dimension(500, 100));
        backSet.setPreferredSize(new Dimension(500, 100));
        fr.add(BorderLayout.NORTH, batchSet);
        fr.add(BorderLayout.CENTER, singleSet);
        fr.add(BorderLayout.SOUTH, backSet);

        // Add actions
        batchBotton.addActionListener(this);
        singleBotton.addActionListener(this);
        back.addActionListener(this);

        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        // 批量设置 Action
        if (e.getSource() == batchBotton) {
            FrameSetBatch frameClassifyBatch = new FrameSetBatch();
            frameClassifyBatch.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
        // 单个设置 Action
        else if(e.getSource() == singleBotton){
            FrameSetSingle frameClassifySingle = new FrameSetSingle();
            frameClassifySingle.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
        // 返回 Action
        else if(e.getSource() == back){
            JFrame frameMain = new FrameMain();
            frameMain.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
    }
};



class FrameSetSingle extends JFrame implements ActionListener{
    JFrame fr;
    SelectWindow fileSelector;
    JPanel searchSet, attrSet, nameSet, bottonSet;
    JLabel attrLabel, nameLabel;
    JTextField attrText, nameText;
    JButton back, certain;
    FrameAttr frameAttr;
    String selectedFile;
    JComboBox jcb;
    String selectedFileAddress;
    String selectedPath;
    boolean action;


    public FrameSetSingle(){
        // set Frame
        String name = "文件设置界面";
        fr  = new JFrame(name);
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());

        fileSelector = new SelectWindow();

        // set attrSet
        attrSet = new JPanel();
        attrSet.setLayout(new FlowLayout());
        attrSet.setLayout(new GridLayout(3,2));
        attrLabel = new JLabel("分类属性");
        String[] jg = { "标签", "时间", "地点", "人物" };
        jcb = new JComboBox(jg);
        // attrText = new JTextField(10);
        nameLabel = new JLabel("属性名称");
        nameText = new JTextField(10);
        back = new JButton("返回");
        certain = new JButton("确认");
        attrSet.add(attrLabel);
        attrSet.add(jcb);
        attrSet.add(nameLabel);
        attrSet.add(nameText);
        attrSet.add(back);
        attrSet.add(certain);

        // Add actions
        back.addActionListener(this);
        certain.addActionListener(this);
        fileSelector.browseButton.addActionListener(this);
        fileSelector.selectButton.addActionListener(this);


        // 布局
        fileSelector.panel.setPreferredSize(new Dimension(500, 200));
        attrSet.setPreferredSize(new Dimension(500, 100));
        fr.add(BorderLayout.NORTH, fileSelector.panel);
        fr.add(BorderLayout.CENTER, attrSet);

        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        // browseButtion Action
        if (e.getSource() == fileSelector.browseButton) {
            JFileChooser fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new File("C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file"));
//            fileChooser.setCurrentDirectory(new File("C:/"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(fileSelector.frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPath = fileChooser.getSelectedFile().getPath();
                fileSelector.pathText.setText(selectedPath);
                fileSelector.updateFileList(selectedPath);
            }
            // selection Action
        } else if (e.getSource() == fileSelector.selectButton) {
            selectedFile = (String) fileSelector.fileList.getSelectedValue();
            // selectedFileAddress = (String) fileSelector.fileList.getSelectedValue().getPath();
            File[] files = new File(selectedPath).listFiles();
            for (int i=0; i<files.length; i++) {
                String fileName = files[i].getName();
                if(fileName.equals(selectedFile)){
                    selectedFileAddress = files[i].getAbsolutePath();
                }
                // 对文件名进行处理，例如将其添加到文件列表中等等
            }
            if (selectedFile != null) {
                JOptionPane.showMessageDialog(fileSelector.frame, "You selected: " + selectedFile);
            }
        }
        // "确认" Action
        else if (e.getSource() == certain) {
            String attr = (String) jcb.getSelectedItem();
            String name = nameText.getText();
            try{
                selectedFileAddress = selectedFileAddress.replace("\\", "/");
                action = setANDread.set(selectedFileAddress, attr, name);
                if(action == true){
                    JOptionPane.showMessageDialog(null, "设置成功\n " + "属性：" +attr +"\n"+ "名称：" + name);
                }
                else{
                    JOptionPane.showMessageDialog(null, "设置失败！");
                }
            }
            catch (Exception ex) {
                // 处理异常...
            }
        }
        // "返回" Action
        else if (e.getSource() == back) {
            JFrame frameAttr = new FrameAttr();
            frameAttr.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
    }
};



class SelectWindow implements ListSelectionListener {
    JFrame frame;
    JTextField pathText;
    JList fileList;
    JButton browseButton;
    JButton selectButton;
    public JPanel panel;

    // 构造函数
    public SelectWindow() {
        pathText = new JTextField(20);
        fileList = new JList();
        browseButton = new JButton("Browse");
        selectButton = new JButton("Select");
        // browseButton.addActionListener(this);
        // selectButton.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(fileList);
        panel = new JPanel(new BorderLayout());
        panel.add(pathText, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(browseButton);
        buttonPanel.add(selectButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }


    // 文件列表选择事件处理程序
    public void valueChanged(ListSelectionEvent e) {
        String selectedFile = (String) fileList.getSelectedValue();
        if (selectedFile != null) {
            pathText.setText(selectedFile);
        }
    }

    //更新文件列表
    public void updateFileList(String path) {
        DefaultListModel listModel = new DefaultListModel();
        java.io.File file = new java.io.File(path);
        if (file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i]);
            }
        }
        fileList.setModel(listModel);
    }
};



class FrameSetBatch extends JFrame implements ActionListener{
    JFrame fr;
    SelectWindowBatch fileSelector;
    JPanel searchSet, attrSet, nameSet, bottonSet;
    JLabel attrLabel, nameLabel;
    JTextField attrText, nameText;
    JButton back, certain;
    FrameAttr frameAttr;
    String selectedFile;
    JComboBox jcb;
    String[] selectedFileAddress;
    String selectedPath;
    boolean action;


    public FrameSetBatch(){
        // set Frame
        String name = "文件设置界面";
        fr  = new JFrame(name);
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());
        fileSelector = new SelectWindowBatch();

        // set attrSet
        attrSet = new JPanel();
        attrSet.setLayout(new FlowLayout());
        attrSet.setLayout(new GridLayout(3,2));
        attrLabel = new JLabel("分类属性");
        String[] jg = { "标签", "时间", "地点", "人物" };
        jcb = new JComboBox(jg);
        // attrText = new JTextField(10);
        nameLabel = new JLabel("属性名称");
        nameText = new JTextField(10);
        back = new JButton("返回");
        certain = new JButton("确认");
        attrSet.add(attrLabel);
        attrSet.add(jcb);
        attrSet.add(nameLabel);
        attrSet.add(nameText);
        attrSet.add(back);
        attrSet.add(certain);

        // Add actions
        back.addActionListener(this);
        certain.addActionListener(this);
        fileSelector.browseButton.addActionListener(this);

        // 布局
        fileSelector.panel.setPreferredSize(new Dimension(500, 200));
        attrSet.setPreferredSize(new Dimension(500, 100));
        fr.add(BorderLayout.NORTH, fileSelector.panel);
        fr.add(BorderLayout.CENTER, attrSet);

        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        // browseButtion Action
        if (e.getSource() == fileSelector.browseButton) {
            JFileChooser fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new File("C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file"));
//            fileChooser.setCurrentDirectory(new File("C:/"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(fileSelector.frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPath = fileChooser.getSelectedFile().getPath();
                fileSelector.pathText.setText(selectedPath);
                fileSelector.updateFileList(selectedPath);
            }
            File[] files = new File(selectedPath).listFiles();
            selectedFileAddress = new String[files.length];
            for (int i=0; i<files.length; i++) {
                selectedFileAddress[i] = (String) files[i].getAbsolutePath().replace("\\", "/");
                // 对文件名进行处理，例如将其添加到文件列表中等等
            }
        }
        // "确认" Action
        else if (e.getSource() == certain) {
            String attr = (String) jcb.getSelectedItem();
            String name = nameText.getText();
            Boolean flag = true;
            try{
                for(int m=0; m<selectedFileAddress.length; m++){
                    action = setANDread.set(selectedFileAddress[m], attr, name);
                    if(action == false){
                        String fileAbsolutePath = selectedFileAddress[m];
                        File file = new File(fileAbsolutePath);
                        String fileName = file.getName();
                        JOptionPane.showMessageDialog(null, fileName + " 设置失败！" + "属性：" +attr +"\n"+ "名称：" + name);
                        flag = false;
                    }
                }
                if(flag == true){
                    JOptionPane.showMessageDialog(null, "所有文件设置成功\n " + "属性：" +attr +"\n"+ "名称：" + name);
                }
            }
            catch (Exception ex) {
                // 处理异常...
            }
        }
        // "返回" Action
        else if (e.getSource() == back) {
            JFrame frameAttr = new FrameAttr();
            frameAttr.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
    }
};



class SelectWindowBatch implements ListSelectionListener {
    JFrame frame;
    JTextField pathText;
    JList fileList;
    JButton browseButton;
    public JPanel panel;

    // 构造函数
    public SelectWindowBatch() {
        pathText = new JTextField(20);
        fileList = new JList();
        browseButton = new JButton("Browse");
        // browseButton.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(fileList);
        panel = new JPanel(new BorderLayout());
        panel.add(pathText, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(browseButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

    }

    // // 浏览按钮事件处理程序
    // public void actionPerformed(ActionEvent e) {
    //     if (e.getSource() == browseButton) {
    //         JFileChooser fileChooser = new JFileChooser();
    // 		fileChooser.setCurrentDirectory(new File("C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file"));
    //         fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    //         int result = fileChooser.showOpenDialog(frame);
    //         if (result == JFileChooser.APPROVE_OPTION) {
    //             String selectedPath = fileChooser.getSelectedFile().getPath();
    //             pathText.setText(selectedPath);
    //             updateFileList(selectedPath);
    //         }
    //     }
    // }

    // 文件列表选择事件处理程序
    public void valueChanged(ListSelectionEvent e) {
        String selectedFile = (String) fileList.getSelectedValue();
        if (selectedFile != null) {
            pathText.setText(selectedFile);
        }
    }

    //更新文件列表
    public void updateFileList(String path) {
        DefaultListModel listModel = new DefaultListModel();
        java.io.File file = new java.io.File(path);
        if (file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                listModel.addElement(files[i]);
            }
        }
        fileList.setModel(listModel);
    }
};



class FrameCheckAttr extends JFrame implements ActionListener{
    JFrame fr;
    SelectWindow fileSelector;
    JPanel blankSet, bottonSet;
    JButton back, certain;
    FrameAttr frameAttr;
    String selectedFile;
    String selectedFileAddress;
    String selectedPath;
    boolean action;


    public FrameCheckAttr(){
        // set Frame
        String name = "属性查看界面";
        fr  = new JFrame(name);
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());
        // fr.setLayout(new GridLayout(2,1));
        // fr.setLayout(new FlowLayout());


        fileSelector = new SelectWindow();

        blankSet = new JPanel();

        bottonSet = new JPanel();
        bottonSet.setLayout(new FlowLayout());
        bottonSet.setLayout(new GridLayout(1,2));
        back = new JButton("返回");
        certain = new JButton("确认");

        bottonSet.add(back);
        bottonSet.add(certain);

        // Add actions
        back.addActionListener(this);
        certain.addActionListener(this);
        fileSelector.browseButton.addActionListener(this);
        fileSelector.selectButton.addActionListener(this);

        // 布局
        fileSelector.panel.setPreferredSize(new Dimension(500, 200));
        blankSet.setPreferredSize(new Dimension(500, 70));
        bottonSet.setPreferredSize(new Dimension(500, 30));
        fr.add(BorderLayout.NORTH, fileSelector.panel);
        fr.add(BorderLayout.CENTER, blankSet);
        fr.add(BorderLayout.SOUTH, bottonSet);

        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        // browseButtion Action
        // browseButtion Action
        if (e.getSource() == fileSelector.browseButton) {
            JFileChooser fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new File("C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file"));
//            fileChooser.setCurrentDirectory(new File("C:/"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(fileSelector.frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPath = fileChooser.getSelectedFile().getPath();
                fileSelector.pathText.setText(selectedPath);
                fileSelector.updateFileList(selectedPath);
            }
            // selection Action
        } else if (e.getSource() == fileSelector.selectButton) {
            selectedFile = (String) fileSelector.fileList.getSelectedValue();
            // selectedFileAddress = (String) fileSelector.fileList.getSelectedValue().getPath();
            File[] files = new File(selectedPath).listFiles();
            for (int i=0; i<files.length; i++) {
                String fileName = files[i].getName();
                if(fileName.equals(selectedFile)){
                    selectedFileAddress = files[i].getAbsolutePath();
                }
                // 对文件名进行处理，例如将其添加到文件列表中等等
            }
            if (selectedFile != null) {
                JOptionPane.showMessageDialog(fileSelector.frame, "You selected: " + selectedFile);
            }
        }
        // "确认" Action
        else if (e.getSource() == certain) {
            try{
                System.out.println("GO INTO THIS LOOP!!!");
                Map attr = setANDread.check(selectedFileAddress);
                // attr.forEach((key, value) -> System.out.println(key + " : " + value));
                System.out.println("遍历结束！");
                // JOptionPane.showMessageDialog(null, "设置成功\n " + "属性：" +attr +"\n"+ "名称：" + name);
                new MapTable(attr);
            }
            catch (Exception ex) {
                // 处理异常...
            }
        }
        // "返回" Action
        else if (e.getSource() == back) {
            JFrame frameMain = new FrameMain();
            frameMain.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
    }
};

class MapTable extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public MapTable(Map<String, String> map) {
        super("文件属性查看ing");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 创建表格模型
        tableModel = new DefaultTableModel();
        tableModel.addColumn("属性名称");
        tableModel.addColumn("属性值");

        // 将Map中的数据添加到表格中
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Object[] row = {entry.getKey(), entry.getValue()};
            tableModel.addRow(row);
        }

        // 创建表格组件，并将表格模型设置为其数据模型
        table = new JTable(tableModel);

        // 将表格组件添加到窗口中
        getContentPane().add(new JScrollPane(table));

        setVisible(true);
    }

}

class FrameClassifyBatch extends JFrame implements ActionListener{
    JFrame fr;
    SelectWindowBatch fileSelector;
    JPanel searchSet, attrSet, nameSet, bottonSet;
    JLabel attrLabel, nameLabel;
    JTextField attrText, nameText;
    JButton back, certain;
    FrameAttr frameAttr;
    String selectedFile;
    JComboBox jcb;
    String[] selectedFileAddress;
    String selectedPath;
    boolean action;


    public FrameClassifyBatch(){
        // set Frame
        String name = "文件分类界面";
        fr  = new JFrame(name);
        fr.setBounds(200, 200, 5000,500);
        fr.setLayout(new BorderLayout());
        fileSelector = new SelectWindowBatch();

        // set attrSet
        attrSet = new JPanel();
        attrSet.setLayout(new FlowLayout());
        attrSet.setLayout(new GridLayout(3,2));
        attrLabel = new JLabel("分类属性");
        String[] jg = { "标签", "时间", "地点", "人物" };
        jcb = new JComboBox(jg);
        // attrText = new JTextField(10);
        nameLabel = new JLabel("属性名称");
        nameText = new JTextField(10);
        back = new JButton("返回");
        certain = new JButton("确认");
        attrSet.add(attrLabel);
        attrSet.add(jcb);
        attrSet.add(nameLabel);
        attrSet.add(nameText);
        attrSet.add(back);
        attrSet.add(certain);

        // Add actions
        back.addActionListener(this);
        certain.addActionListener(this);
        fileSelector.browseButton.addActionListener(this);

        // 布局
        fileSelector.panel.setPreferredSize(new Dimension(500, 200));
        attrSet.setPreferredSize(new Dimension(500, 100));
        fr.add(BorderLayout.NORTH, fileSelector.panel);
        fr.add(BorderLayout.CENTER, attrSet);

        // Frame调整
        fr.pack();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Actions
    public void actionPerformed(ActionEvent e){
        // browseButtion Action
        if (e.getSource() == fileSelector.browseButton) {
            JFileChooser fileChooser = new JFileChooser();
             fileChooser.setCurrentDirectory(new File("C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file"));
//            fileChooser.setCurrentDirectory(new File("C:/"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int result = fileChooser.showOpenDialog(fileSelector.frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedPath = fileChooser.getSelectedFile().getPath();
                fileSelector.pathText.setText(selectedPath);
                fileSelector.updateFileList(selectedPath);
            }
            File[] files = new File(selectedPath).listFiles();
            selectedFileAddress = new String[files.length];
            for (int i=0; i<files.length; i++) {
                selectedFileAddress[i] = (String) files[i].getAbsolutePath();
            }
        }
        // "确认" Action
        else if (e.getSource() == certain) {
            String attr = (String) jcb.getSelectedItem();
            String name = nameText.getText();
            Boolean flag = true;
            try{
                Operation.classify(selectedPath, attr, name);
            }
            catch (Exception ex) {
                // 处理异常...
            }
        }
        // "返回" Action
        else if (e.getSource() == back) {
            FrameMain frameMain = new FrameMain();
            frameMain.setVisible(true);
            this.fr.setVisible(false); // 隐藏主页面
        }
    }
};