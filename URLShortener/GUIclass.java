package URLShortener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;

class shortenerGUI extends JFrame implements ActionListener
{
    JLabel l1;
    JLabel l2,l3;
    JButton b1,b2,b3;
    JTextField t1,t2;
    public shortenerGUI()
    {
        l1= new JLabel("                    WELCOME TO URL SHORTENER                    ");
        l2 = new JLabel("Enter the URL");
        b1 = new JButton("CONVERT");
        l3 = new JLabel("\n\n");

        t1 = new JTextField(50);
        t2 = new JTextField(30);
        b2 = new JButton("SAVE");
        b3 = new JButton("CLEAR");

        add(l1);
        add(l3);
        add(l2);
        add(t1);
        add(b1);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        //add(l1);
        add(t2);
        add(b2);
        add(b3);

        setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
        setSize(500,400);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1)
        {
            Boolean check=true;
            MD5toBase62 l = new MD5toBase62();
            String str;
            File f=new File("URLShortenerData.txt");
            BufferedReader freader = null;
            try {
                freader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String s;
            while(true) {
                try {
                    if (!((s = freader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String[] st = s.split(" ");
                String  LURL = st[0];
                String  SURL= st[1];

                if (LURL.equals(t1.getText())) {
                    System.out.println("Already saved in the file");
                    check=false;
                    t2.setText(SURL);
                }
            }
            if(check){
            try {
                str = l.urlshortnerMD5Hash(t1.getText(),7);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
            t2.setText("https://www.shorturl."+str);}
        }
        if(e.getSource()==b3)
        {
             t1.setText("");
             t2.setText("");
        }
        if(e.getSource()==b2)
        {
            Boolean flag=true;
            /*try {
                File myObj = new File("URLShortenerData.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException exp) {
                System.out.println("An error occurred.");
                exp.printStackTrace();}*/
            File f=new File("URLShortenerData.txt");
            BufferedReader freader = null;
            try {
                freader = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            String s;
            while(true) {
                try {
                    if (!((s = freader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String[] st = s.split(" ");
                String  LURL = st[0];
                String  SURL= st[1];

                if (LURL.equals(t1.getText())) {
                    System.out.println("Already saved in the file");
                    flag=false;
                }
            }
            if(flag) {
                try {
                    FileWriter myWriter = new FileWriter("URLShortenerData.txt");
                    myWriter.write(t1.getText() + " " + t2.getText() + "\n");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException exp) {
                    System.out.println("An error occurred.");
                    exp.printStackTrace();
                }
            }
        }




    }
}
public class GUIclass {
    public static void main(String[] args) {
            shortenerGUI s = new shortenerGUI();
    }
}
