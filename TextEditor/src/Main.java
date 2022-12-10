import java.awt.*;
import javax.print.PrintException;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import java.nio.Buffer;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class Editor extends JFrame implements ActionListener
{


    JFrame f;

    JTextArea t;

    Editor()
    {
        //initialising
        f=new JFrame("notepad");
        //setting the overall theme of the application
        try{
            //f.show();
            UIManager.setLookAndFeel("javax.swing.plaf.metal.metalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }catch (Exception e)
        {
               //e.printStackTrace();
            System.out.println("Exception Occuring");
        }

        //create the text cmoponent
        t=new JTextArea();

        //create the menubar
        JMenuBar menu=new JMenuBar();

        JMenu file= new JMenu("File");

        JMenuItem n1=new JMenuItem("New");
        JMenuItem n2=new JMenuItem("Open");
        JMenuItem n3=new JMenuItem("Save");
        JMenuItem n4=new JMenuItem("Print");



        n1.addActionListener(this);
        n2.addActionListener(this);
        n3.addActionListener(this);
        n4.addActionListener(this);

        file.add(n1);
        file.add(n2);
        file.add(n3);
        file.add(n4);


        JMenu edit= new JMenu("Edit");


        JMenuItem n5=new JMenuItem("Cut");
        JMenuItem n6=new JMenuItem("Copy");
        JMenuItem n7=new JMenuItem("Paste");

        n5.addActionListener(this);
        n6.addActionListener(this);
        n7.addActionListener(this);


        //adding menuitems to edit menu
        edit.add(n5);
        edit.add(n6);
        edit.add(n7);

        //creating the close button
        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);

       // JMenu edit= new JMenu("Edit");

        //adding the menus to the menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //adding the menubar and the textArea to the frame
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(800,400);

        f.show();


    }
    //function to adding the functionality to each of the menuitems
    public void actionPerformed(ActionEvent e) {
        //etracting  the button pressed
        String s=e.getActionCommand();

        if(s.equals("New"))
        {
            t.setText("");

        }else if(s.equals("Open"))
        {
            //initialising the JFileChoser with desired directory
            JFileChooser j=new JFileChooser("D:");

            //invoking the  opendialogbox with an integr
            int r=j.showOpenDialog(null);

            if(r==JFileChooser.APPROVE_OPTION)
            {
                //set the label to the path of the selected file location
                File fi=new File(j.getSelectedFile().getAbsolutePath());
                //String to copy the data from chosen file
                try{
                    String s1="", s2="";

                    //store the whole file in fr
                    FileReader fr=null;
                    fr=new FileReader(fi);

//buffers the fr variable character by character so thar it can be stored
                    BufferedReader br=new BufferedReader(fr);


//storing the first character inside s2
                    s2=br.readLine();


                    while((s1=br.readLine())!=null)
                    {
                        s2=s2+'\n'+s1;
                    }

                    t.setText(s2);
                }catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(f,exc.getMessage());
                }
            }

        }else if(s.equals("Save"))
        {
            JFileChooser j=new JFileChooser("D:");

            int r=j.showSaveDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter fw=new FileWriter(fi,false);

                    BufferedWriter bw=new BufferedWriter(fw);

                    bw.write(t.getText());

                    //after writing is finished flush the stream
                    bw.flush();
                    bw.close();
                }catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(f,exc.getMessage());
                }
            }

        }else if(s.equals("Print"))
        {
            try{
                t.print();
            }catch (PrinterException ex)
            {
                ex.getMessage();
            }

        }else if(s.equals("Cut"))
        {
            t.cut();
        }else if(s.equals("Copy"))
        {
            t.copy();
        }else if(s.equals("Paste"))
        {
            t.paste();
        }else if(s.equals("Close"))
        {
            f.setVisible(false);
        }
    }


}
public class Main {
    public static void main(String[] args) {
        Editor e=new Editor();
    }
}