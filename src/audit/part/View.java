package audit.part;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jz36 on 25.05.16.
 */
public class View extends JFrame{

    private JButton openFile = new JButton("Open File");
    private JButton startButton = new JButton("Start");
    private static JTextArea currentOperation = new JTextArea();
    private JFileChooser newFile = new JFileChooser();
    private BufferedReader file;
    private String text;


    public View() {
        setLayout(null);
        setSize(500, 300);
        setVisible(true);

        OpenFileAction openAction = new OpenFileAction();

        openFile.addActionListener(openAction);

        openFile.setBounds(180,30,140,50);
        startButton.setBounds(180,100,140,50);
        currentOperation.setBounds(30,200,440,50);
        currentOperation.setEditable(false);
        add(openFile);
        add(startButton);
        add(currentOperation);
        setTitle("Студия Дмитрия Биксилеева");
    }

    public BufferedReader getFile(){
        return file;
    }

    public class OpenFileAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int ret = newFile.showDialog(null, "Открыть файл");
            if(ret == JFileChooser.APPROVE_OPTION){
                try {
                    file = new BufferedReader(new FileReader(newFile.getSelectedFile()));
                    Mail myMail = new Mail();
                    /*while(true){
                        try {
                            StringTokenizer st = new StringTokenizer(file.readLine(), ",");
                            String[] currentString = new String[3];
                            currentString[0] = st.nextToken();
                            currentString[1] = st.nextToken();
                            currentString[2] = st.nextToken();
                            myMail.sendMail(currentString[2]);
                        } catch(NoSuchElementException e1){
                            System.out.println("Finished");
                            break;
                        }
                    }*/
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }

    public static void setCurrentOperation(String text){
        currentOperation.setText(text);
    }

}
