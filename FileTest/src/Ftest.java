import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ftest extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JTextArea jTextArea = null;
    private JPanel controlPanel = null;
    private JButton openButton = null;
    private JButton closeButton = null;

    private JTextArea getJTextArea() {
        if (jTextArea == null) {
            jTextArea = new JTextArea();
        }
        return jTextArea;
    }

    private JPanel getControlPanel() {
        if (controlPanel == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setVgap(1);
            controlPanel = new JPanel();
            controlPanel.setLayout(flowLayout);
            controlPanel.add(getOpenButton(), null);
            controlPanel.add(getCloseButton(), null);
        }
        return controlPanel;
    }

    private JButton getOpenButton() {
        if (openButton == null) {
            openButton = new JButton();
            openButton.setText("写入文本");
            openButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File file = new File("D:/work.txt");
                    try {
                        FileWriter out = new FileWriter(file);
                        String s = jTextArea.getText();
                        out.write(s);
                        out.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
        return openButton;
    }

    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new JButton();
            closeButton.setText("读取文本");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File file = new File("D:/work.txt");
                    try {
                        FileReader in = new FileReader(file);
                        char byt[] = new char[1024];
                        int len = in.read(byt);
                        jTextArea.setText(new String(byt, 0, len));
                        in.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
        return closeButton;
    }

    public Ftest() {
        super();
        initialize();
    }

    public void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    private JPanel getJContentPane() {
        if(jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJTextArea(), BorderLayout.CENTER);
            jContentPane.add(getControlPanel(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    public static void main(String[] args) {
        Ftest thisClass = new Ftest();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.setVisible(true);
    }
}
