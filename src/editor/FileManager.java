package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            textEditor.currentFile = file;

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String data;
                while ((data = br.readLine()) != null) {
                    textArea.append(data + "\n");
                }

                br.close();
            } catch (Exception e) {
                textArea.append(e.getMessage());
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

                    bw.write(textArea.getText());

                    bw.close();
                } catch (Exception e) {
                    textArea.append(e.getMessage());
                }
            }
        }
        else{
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(textEditor.currentFile, true));

                bw.write(textArea.getText());

                bw.close();
            } catch (Exception e) {
                textArea.append(e.getMessage());
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textEditor.currentFile = null;

        textArea.setText("");
    }
}
