import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About  extends JFrame implements ActionListener {

    About () {
        setBounds(400, 100, 600, 500);
        setLayout(null); //makes the default border layout null

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/jolly.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(225, 10, 120,120);
        add(headerIcon);


        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad_icon.png"));
        Image i5 = i4.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel icon = new JLabel(i6);
        icon.setBounds(50, 180, 35,35);
        add(icon);

        JLabel text = new JLabel("PIRACY WILL NEVER DIE!!");
        text.setBounds(218, 125, 200, 50);
        text.setFont(new Font("TIMES_NEW_ROMAN", Font.ITALIC, 12));
        add(text);

        JLabel text1 = new JLabel("<html> Notepad Cloned<br>Version 0.1.0 Alpha <br>(OS BUILD JAVA)<br> Made with a lot of Frustration. <br>No Rights reserved.");
        text1.setBounds(218, 200, 200, 100);
        text1.setFont(new Font("TIMES_NEW_ROMAN", Font.PLAIN, 14));
        add(text1);
        setVisible(true);

        JButton button = new JButton("OKAY");
        button.setBounds(245, 330, 70, 30);
        button.setBackground(Color.GRAY);
        button.addActionListener(this);
        add(button);


    }

    public static void main(String[] args) {
        new About();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            this.setVisible(false);

    }
}
