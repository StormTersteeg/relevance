package src.views;
import src.system.Data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Container;

public class SortedResult extends JFrame {
    
    public SortedResult() {
        setTitle("Result");
        setSize(new Dimension(600, 300));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        setBackground(new Color(30, 30, 30));
        setVisible(true);

        JList list = new JList(Data.sorted);
        list.setForeground(Color.white);
        list.setBackground(new Color(30, 30, 30));
        list.setFixedCellHeight(26);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBackground(new Color(30,30,30));

        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(30,30,30));
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

}
