import javax.swing.*; //Package for Jframe which will create a frame. If you use .* then all classes under java extended package is used.
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener { //if class is not made abstract then we will need to override all methods in the interface
    //which in this case is actionlistener
    JTextArea area = new JTextArea(); //Defined globally to be used everywhere
    String text;

    Notepad() {
        setTitle("NotePad Cloned");

        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad_icon.png")); /* Creates imageicon class called imageicon
        and uses classloader.getsystemresources for fetching data from system. the path of the icon has to be added very carefully with the correct extension*/

        Image icon = notepadIcon.getImage();  //Converts notepad icon which is an image-icon into an Image class
        setIconImage(icon); //Seticoimage takes Image class as an argument and sets the icon of the app as per the desired icon.

        JMenuBar menubar = new JMenuBar(); //Creates a Menubar class called menubar
        menubar.setBackground(Color.white); //Sets the background color of the menubar to white
        setJMenuBar(menubar); //adds menubar to the screen

        //FILE OPTION IN MENU

        JMenu file = new JMenu("File"); //Creates a Menu called file
        file.setFont(new Font("AERIAL",Font.BOLD, 14)); //Changes the font family of File menu. setfont takes font family, style and font size as parameters
        menubar.add(file); //Adds file menu under menubar

        //FILE MENU OPTIONS

        JMenuItem newdoc = new JMenuItem("New"); //Creates the dropdown menu that appears when New is clicked
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //When Ctrl+N is pressed then new option is linked to it.
        file.add(newdoc); //Adds the new option under File menu

        JMenuItem opendoc = new JMenuItem("Open");
        opendoc.addActionListener(this);
        opendoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(opendoc);

        JMenuItem saveddoc = new JMenuItem("Save");
        saveddoc.addActionListener(this);
        saveddoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(saveddoc);

        JMenuItem printdoc = new JMenuItem("Print");
        printdoc.addActionListener(this);
        printdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(printdoc);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        file.add(exit);

        //EDIT OPTION IN MENU

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL",Font.BOLD, 14));
        menubar.add(edit);

        //OPTIONS MENU OPTIONS

        JMenuItem cuttext = new JMenuItem("Cut");
        cuttext.addActionListener(this);
        cuttext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cuttext);

        JMenuItem copytext = new JMenuItem("Copy");
        copytext.addActionListener(this);
        copytext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copytext);

        JMenuItem pastetext = new JMenuItem("Paste");
        pastetext.addActionListener(this);
        pastetext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(pastetext);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(selectall);

        //ABOUT OPTION IN MENU

        JMenu helpmenu = new JMenu("Help");
        edit.setFont(new Font("AERIAL",Font.BOLD, 14));
        menubar.add(helpmenu);

        //HELP MENU OPTIONS
        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        helpmenu.add(help);

        //TEXT AREA TO TYPE

        area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        area.setLineWrap(true); //Sets the cursor to go to next line when the line is full
        area.setWrapStyleWord(true); //If a long word is typed, this makes sure that the entire word is taken to next line and not parts of it

        JScrollPane pane = new JScrollPane(area); //Adds a scroll bar to the text area
        pane.setBorder(BorderFactory.createEmptyBorder()); //Removes the border of the scroll screen
        add(pane); //we are passing pane instead of area variable


        setExtendedState(JFrame.MAXIMIZED_BOTH); /* Maximised the length and breadth of the frame created. Mostly makes it full screen */

        setVisible(true); //reflects all the conditions specified above. This should be the last thing in the constructor
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("New")) { //Checks to see if new option in the menu is pressed
            area.setText(" "); //Clears the writing space when new option is pressed
        } else if (ae.getActionCommand().equals("Open")) { //Checks to see if open option in menu is pressed
            JFileChooser chooser = new JFileChooser(); //Jfilechooser is a file chooser
            chooser.setAcceptAllFileFilterUsed(false); //All files types will not be accepted since we need only .txt files
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); //Only files with .txt extension will be selected
            chooser.addChoosableFileFilter(restrict); //now the file chooser will show only the .txt files in any folder

            int action = chooser.showOpenDialog(this); //Opens the file explorer dialog

            if (action != JFileChooser.APPROVE_OPTION) { //If a text file is not chosen and the explorer is closed then
                return; //it will return back to the notepad itself
            }

            File file = chooser.getSelectedFile(); //Gets the text file chosen in the explorer
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file)); //Buffereader class is used to read files. it takes a filereader class
                //which itself takes a file and reads the file
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser(); //Called because we need file explorer to go to a location and save the file
            saveas.setApproveButtonText("Save"); //Shows the button as save instead of the default open

            int action = saveas.showOpenDialog(this);

            if (action!= JFileChooser.APPROVE_OPTION) {
                return;

            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try{
              outFile = new BufferedWriter(new FileWriter(filename));
              area.write(outFile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);

        } else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition() + 1);
        } else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        } else if (ae.getActionCommand().equals("About")) {
            new About().setVisible(true);

        }

    }

    public static void main(String[] args) {
       new Notepad(); //this is a constructor
        }
    }
