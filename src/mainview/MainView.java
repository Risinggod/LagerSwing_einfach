package mainview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainView {

    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel startTab = new JPanel(new GridLayout(2, 2));
    private JMenuBar menuBar = new JMenuBar();

    public MainView() {

        // add main-container to root-window
        frame.add(mainPanel);

        // menuBar to mainPanel
        initMenuBar();
        mainPanel.add(menuBar, BorderLayout.NORTH);

        // startTab to tabbedPane and to mainPanel
        initStartTab();
        tabbedPane.addTab("Start", startTab);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // bottomPane to mainPanel
        initBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void startApplication(String title) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(10, 10);
        frame.setVisible(true);
    }

    private void initStartTab() {
        startTab.add(new JButton(new ImageIcon("images/add_180x180.png")));
        startTab.add(new JButton(new ImageIcon("images/table_180x180.png")));
        startTab.add(new JButton(new ImageIcon("images/minus_180x180.png")));
        startTab.add(new JButton("1,1"));
    }

    public JPanel getStartTab() {
        return startTab;
    }

    private void initMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Close"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Delete"));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new JMenuItem("About"));
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private void initBottomPanel() {
        bottomPanel.add(new JLabel("Anzahl Datensätze:"));
        bottomPanel.add(new JLabel("0"));
        bottomPanel.add(new JLabel("xx.xx.xxxx"));
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }
    
    /* for tests */
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}