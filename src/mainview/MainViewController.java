package mainview;

import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import daoservice.DAOHttpService;
import daoservice.DAOJsonService;
import daoservice.LagerFXModel;
import service.DataAccessObject;
import service.DataTransferObject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController {

    // Klassenvariablen (in der gesamten Klasse bekannt)(um die Klassenvariablen zu benutzen muss "this." davor stehen)
    //Hallo
    private MainView view;
    private DataAccessObject dao;
    private List<DataTransferObject> dtos;

    private class AddAddTabEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setAnzahlDaten();
        }
    }

    private class DeleteActiveTabEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTabbedPane localtabpane;
            localtabpane = view.getTabbedPane();
            int selected = localtabpane.getSelectedIndex();
            if (selected == 0)
                return;
            localtabpane.remove(selected);
            }
        }
    
    
    
    
    private class AddListViewTabEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Prüfung, ob bereits ein Tab "ListView" gibt. Wenn ja, wird in den Tab gesprungen und 'actionPerformde' verlassen
            for (int i = 0; i < view.getTabbedPane().getComponentCount(); i++) {
                if (view.getTabbedPane().getTitleAt(i).equals("ListView")) {
                    view.getTabbedPane().setSelectedIndex(i);
                    return;
                }
            }
           
            //(lokale variable; nur in actionPerformed bekannt)
            JPanel listViewTab = new JPanel();
            listViewTab.setBackground(Color.yellow);
            
            // ScrollPane erzeugen und listViewTAab hinzufügen
            JScrollPane scrollPane = new JScrollPane(listViewTab);
            
            //layout für listViewTab festlegen und scrollPane zum TabbedPane hinzufügen
            listViewTab.setLayout(new BoxLayout(listViewTab, BoxLayout.Y_AXIS));
            view.getTabbedPane().addTab("ListView", scrollPane);

            // Tabellen-Überschriften
            String[] columnNames = {"Id",
                            "UserId",
                            "Title",
                            "Body"};

            // Datenmodell für die Tabelle festlegen
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); 

            // eine Zeile zur Tabelle hinzufügen
            //model.addRow(new Object[] {1, 2, "title1", "body1"});

            // Alle Elemente der List dtos zur Tabelle hinzufügen

            /* (mit for - Schleife)
            for (int i = 0; i < dtos.size(); i++) {
                DataTransferObject buf = dtos.get(i);
                model.addRow(new Object[] {buf.getId(), buf.getUserId(), buf.getTitle(), buf.getBody()});
            }
            */

            /* (mit for each)
            for (var buf : dtos) {
                model.addRow(new Object [] {buf.getId(), buf.getUserId(), buf.getTitle(), buf.getBody()});
            }
            */
            
            // (mit Stream) WICHTIG!!! gibt EXTRAPUNKTE
            dtos.stream()
            .filter(o -> o.getId() > 0)
            .forEach(buf -> model.addRow(new Object [] {buf.getId(), buf.getUserId(), buf.getTitle(), buf.getBody()}));

            /*
            Iterator<DataTransferObject> iter = dtos.iterator();
            while (iter.hasNext()) {
                var buf = iter.next();
                model.addRow(new Object [] {buf.getId(), buf.getUserId(), buf.getTitle(), buf.getBody()});
            }
            */

            // ein JTable-Object erzeugen und dem Datenmodell übergeben
            JTable table = new JTable(model);
            table.setBackground(Color.GREEN);

            // Header und Tabelle zum Container hinzufügen
            listViewTab.add(table.getTableHeader());      
            listViewTab.add(table);

            // automatisch den Tab wechseln, Wenn Liste erzeugt wird
            view.getTabbedPane().setSelectedIndex(view.getTabbedPane().getTabCount() - 1);
            
        }
    }

    public MainViewController(MainView view, DataAccessObject dao) {
        this.view = view;
        this.dao = dao;
        this.view.startApplication("Lager aus Controller");
        setAnzahlDaten();
        if (view.getBottomPanel().getComponent(2) instanceof JLabel label) {
            label.setText(new java.text.SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        }
        initEvents();
    }

    private void initEvents() {
        if (view.getMenuBar().getComponent(0) instanceof JMenu menu) {
            if (menu.getItem(0) instanceof JMenuItem item) {
                item.addActionListener((event) -> System.exit(0));
            }
        }

        if (view.getMenuBar().getComponent(1) instanceof JMenu menu) {
            if (menu.getItem(0) instanceof JMenuItem item) {
                item.addActionListener(new DeleteActiveTabEventHandler());
            }
        }
        
        if (view.getMenuBar().getComponent(2) instanceof JMenu menu) {
            if (menu.getItem(0) instanceof JMenuItem item) {
                item.addActionListener((event) -> JOptionPane.showMessageDialog(null,
                        "LagerverwaltungSWING\n\n" +
                                "Version 1.0\n" +
                                "Copyright (c) 2023 Fachschule für Wirtschaftsinformatik",
                        "Über die Software",
                        JOptionPane.INFORMATION_MESSAGE));
            }
        }

        if (view.getStartTab().getComponent(1) instanceof JButton btn) {
            btn.addActionListener(new AddListViewTabEventHandler());
        }

        if (view.getStartTab().getComponent(0) instanceof JButton btn) {
            btn.addActionListener(new AddAddTabEventHandler());
        }
    }

    public void setAnzahlDaten() {
        if (view.getBottomPanel().getComponent(1) instanceof JLabel label) {
            label.setText("-");
        }

        Thread t1 = new Thread(() -> {
            dtos = dao.getAll();
            SwingUtilities.invokeLater(() -> {
                if (view.getBottomPanel().getComponent(1) instanceof JLabel label) {
                    label.setText(String.valueOf(dtos.size()));
                }
            });
        });
        t1.start();
    }

    public List<DataTransferObject> getListOfDTO() {
        return dtos;
    }

    public DataAccessObject getDataAccessObject() {
        return dao;
    }

    public MainView getMainView() {
        return view;
    }
}
