package src.views;
import src.core.*;
import src.components.*;
import src.system.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class MainView extends SubPanel implements ActionListener {
    private JButton jbResult;
    private JTextArea jtIgnore;

    public MainView(MainPanel parent, String panel_name) {
        super(parent, panel_name);
        setLayout(new BorderLayout());
        setVisible(false);
        setBackground(new Color(30, 30, 30));

        // CENTER
        jbResult = new CButton(this, "Scan Dataset", Color.white, new Color(30, 30, 30));
        add(jbResult, BorderLayout.WEST);
        jtIgnore = new JTextArea();
        jtIgnore.setBackground(new Color(15, 15, 15));
        jtIgnore.setForeground(Color.white);
        jtIgnore.setBorder(new EmptyBorder(10,10,10,10));
        jtIgnore.setLineWrap(true);
        add(jtIgnore, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbResult) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();

            String data = "";
            String[] ignore_these = jtIgnore.getText().split(", ");

            try {
                data = (String) clipboard.getData(DataFlavor.stringFlavor);
                data = data.toLowerCase().replace(" ", "_").replace("\n", "_");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            String[] data_split = data.split("_");

            ArrayList<Pair> result = new ArrayList<Pair>();

            for (String word: data_split) {
                if (Arrays.asList(ignore_these).contains(word)) {continue;}

                boolean found = false;
                for (Pair pair: result) {
                    if (word.equals(pair.word)) {
                        found = true;
                        pair.count++;
                        break;
                    }
                }
                if (!found) {result.add(new Pair(word));}
            }

            ArrayList<Pair> sorted = new ArrayList<Pair>();

            while (result.size() > 0) {
                int heighest_index = 0;
                int heighest_count = 0;
                int i = 0;

                for (Pair pair: result) {
                    if (pair.count>heighest_count) {
                        heighest_index = i;
                        heighest_count = pair.count;
                    }
                    i++;
                }

                sorted.add(result.get(heighest_index));
                result.remove(heighest_index);
            }

            ArrayList<String> lastone = new ArrayList<String>();

            int i = 1;
            for (Pair pair: sorted) {
                if (!pair.word.equals("")) {
                    lastone.add("  [" + i + "] " + pair.word + ": " + pair.count);
                    i++;
                }
            }

            Data.sorted = lastone.toArray(new String[0]);

            SortedResult d = new SortedResult();
        }
    }

    @Override
    public void onFocus() {
        
    }

}
