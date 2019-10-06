package tugas6;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Farhan
 */
public class Tugas6Controller {
    private Tugas6 view;
    private List<Integer> list = new ArrayList<>();
    
    public Tugas6Controller(Tugas6 view){
        this.view = view;
        
            this.view.getBtnBaca().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    proses();
                }
            });
            
            this.view.getBtnSimpan().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    save();
                }
            });
    }
    
    private void save(){
        JFileChooser loadFile = view.getLoadFile();
        if(JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)){
            BufferedWriter writer = null;
            try{
                String contents = view.getTxtPane().getText();
                if(contents != null && !contents.isEmpty()){
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                }
            } catch(FileNotFoundException ex){
                Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch(IOException ex){
                Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                if(writer != null){
                    try{
                        writer.flush();
                        writer.close();
                        view.getTxtPane().setText("");
                        JOptionPane.showMessageDialog(null, "Sukses!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch(IOException ex){
                        Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Teks Masih Kosong!","Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    private void proses(){
            
            JFileChooser loadFile = view.getLoadFile();
            StyledDocument doc = view.getTxtPane().getStyledDocument();
            if(JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)){
//                BufferedReader reader = null;

                PushbackReader reader = null;
                char[] words = new char[(int)loadFile.getSelectedFile().length()];
                
                
                try{
                    
                    // membuat variabel 
                    int desimal; 
                    char ascii;
                    String line = null;
                    
                    // variabel untuk menghitung kata dan karakter
                    // defaultnya 0
                    int wordCount = 0;
                    int charCount = 0;
                    
                    // inisialisasi class LineNumberReader dan LineNumberInputStream
                    // digunakan untuk menghitung baris, jumlah kata, dan jumlah karakter nanti
                    LineNumberReader numReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                    LineNumberInputStream inputStream = new LineNumberInputStream(new FileInputStream(loadFile.getSelectedFile()));
                    
//                    reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                    
//                    String data = null;
//                    doc.insertString(0, "", null);
//                    while((data = reader.readLine()) != null){
//                        doc.insertString(doc.getLength(), data, null);
//                        doc.insertString(doc.getLength(), "\n", null);
//                    }

                    reader = new PushbackReader(new InputStreamReader(new FileInputStream(loadFile.getSelectedFile())));
                    
                    reader.read(words);
                    
                    String data = null;
                    doc.insertString(0, "", null);
                    if((data = new String(words)) != null){
//                        reader.read(words);
                        doc.insertString(doc.getLength(), data , null);
                    }
                    
                    
                    
                    // melakukan penghitungan jumlah baris
                    while((desimal = inputStream.read()) != -1){
                        ascii = (char) desimal;
                    }
                    
                    // melakukan penghitungan jumlah karakter dan jumlah kata
                    while((line = numReader.readLine()) != null){
                        String[] wordList = line.split("\\s");
                        wordCount += wordList.length;
                        charCount += line.length();
                    }
                    
                    // pop up message yang menampilkan Jumlah baris, kata, dan karakter
                    JOptionPane.showMessageDialog(view, "File berhasil dibaca!" +
                            "\nJumlah baris     : " + (inputStream.getLineNumber() + 1) +
                            "\nJumlah kata      : " + (wordCount) +
                            "\nJumlah karakter  : " + (charCount), "Information", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (FileNotFoundException ex){
                    Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | BadLocationException ex){
                    Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if(reader != null){
                        try{
                            reader.close();
                        } catch(IOException ex){
                            Logger.getLogger(Tugas6Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
            
            
}
