import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class BuildWindow extends JFrame {

    private JFileChooser fileChooser;
    private TextArea textArea;
    private XmlParser xmlParser;
    private MySaxParser saxParser;

    public BuildWindow () {
        prepeareFileChoser();
        makeMenu();
        textArea = new TextArea(50, 100);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
    }

    private void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openFileMenuItem = new JMenuItem(new AbstractAction("Open file...") {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFiles();
            }
        });

        JMenuItem openFileMenuItemSAX = new JMenuItem(new AbstractAction("Open file for SAX") {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSaxFile();
            }
        });
        fileMenu.add(openFileMenuItem);
        fileMenu.add(openFileMenuItemSAX);
    }

    private void getSaxFile() {
        int status = fileChooser.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            saxParser = new MySaxParser(file.getPath());
            textArea.setText(saxParser.toString());
        }
    }

    private void getFiles() {
        int status = fileChooser.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            xmlParser = new XmlParser(file.getPath());
            textArea.setText(xmlParser.toString());
        }
    }

    private void prepeareFileChoser() {
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Xml file", "xml"));
    }

    public static void main(String[] args) {
        JFrame frame = new BuildWindow();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
