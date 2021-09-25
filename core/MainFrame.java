package src.core;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel main_panel;
    
    public MainFrame() {
        setSize(300, 150);
        setTitle("Relevance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);

        main_panel = new MainPanel(this);

        add(main_panel);

        setVisible(true);
    }

}
