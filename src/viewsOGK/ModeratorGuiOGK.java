package viewsOGK;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import controllerOGK.ModeratorOGK;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ModeratorGuiOGK {

    private String CONTENT_FILE_PATH = "";
    private final ModeratorOGK moderatorOGK;

     private final JLabel headerWelcomeLabelOGK;
     private final JButton uploadFileButtonOGK;

    public ModeratorGuiOGK(ModeratorOGK moderatorOGK) {
        this.moderatorOGK = moderatorOGK;
        // moderatorOGK.createModeratedFile("src/files/test.txt");

         JFrame frameOGK = new JFrame();

         JLabel headerLabelOGK = new JLabel("OGK - File Moderator", SwingConstants.CENTER);
         headerLabelOGK.setBounds(calculateForCenter(360), 0, 360, 40);
         headerLabelOGK.setFont(new Font("Verdana", Font.BOLD, 18));

         headerWelcomeLabelOGK = new JLabel("WELCOME GUEST", SwingConstants.CENTER);
         headerWelcomeLabelOGK.setBounds(calculateForCenter(360), 40, 360, 50);
         headerWelcomeLabelOGK.setFont(new Font("Verdana", Font.PLAIN, 16));

         uploadFileButtonOGK = new JButton("Moderate File");
         uploadFileButtonOGK.setBounds(calculateForCenter(250),100,250, 40);


         final JLabel messageLabelOGK = new JLabel("", SwingConstants.CENTER);
         messageLabelOGK.setBounds(calculateForCenter(360), 400, 360, 50);
         messageLabelOGK.setFont(new Font("Verdana", Font.PLAIN, 16));

         uploadFileActionOGK(frameOGK, uploadFileButtonOGK, messageLabelOGK);

         frameOGK.add(headerLabelOGK);
         frameOGK.add(headerWelcomeLabelOGK);
         frameOGK.add(uploadFileButtonOGK);
         frameOGK.add(messageLabelOGK);

         frameOGK.setSize(510,500);
         frameOGK.getContentPane().setBackground(Color.DARK_GRAY);
         frameOGK.setLocationRelativeTo(null);
         frameOGK.setLayout(null);
         frameOGK.setVisible(true);
         frameOGK.setTitle("OGK File Moderator");
         frameOGK.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }

     private void uploadFileActionOGK(final JFrame frameOGK, JButton buttonOGK, JLabel messageLabelOGK){
         buttonOGK.addActionListener(e -> {
             JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
             fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));

             int r = fileChooser.showDialog(null, "Upload");

             // if the user selects a file
             if (r == JFileChooser.APPROVE_OPTION) {
                 CONTENT_FILE_PATH = fileChooser.getSelectedFile().getAbsolutePath();
                 try {
                     String newFile = moderatorOGK.saveStringToFile(
                             moderatorOGK.createModeratedFileMatchingWord(CONTENT_FILE_PATH),
                             moderatorOGK.getFileName(CONTENT_FILE_PATH, false));
                     messageLabelOGK.setText("File Successfully Moderated: "+ moderatorOGK.getFileName(newFile, true));
                     messageLabelOGK.setForeground(Color.GREEN);
                 } catch (IOException ex) {
                     messageLabelOGK.setText("Error while Moderating File: "+ moderatorOGK.getFileName(CONTENT_FILE_PATH, true));
                     messageLabelOGK.setForeground(Color.RED);
                     throw new RuntimeException(ex);
                 }
             }
         });
     }

    private int calculateForCenter(int width){
        return (510 - width)/2;
    }
}
