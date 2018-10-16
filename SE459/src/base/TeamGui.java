package base;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TeamGui {

    public static void main(String[] args) {
        new TeamGui();
    }

    public TeamGui() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JLabel label;
        private ArrayList<Integer> team1 = new ArrayList<Integer>();
        private ArrayList<Integer> team2 = new ArrayList<Integer>();
        private int t1Count =0;
        private int t2Count =0;


        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            label = new JLabel("Choose a team");
            add(label, gbc);

            final JButton t1 = new JButton("Team1");
            t1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(t1Count <= 5){
                    	t1Count++;
                    	team1.add(t1Count);
                    	System.out.println("added");
                    	System.out.println(team1.toString());
                    }
                    else
                    {
                    	System.out.println("Team full");
                    }
                }
            });
            add(t1, gbc);
            
            final JButton t2 = new JButton("Team2");
            t2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(t2Count <= 5){
                    	t2Count++;
                    	team2.add(t2Count);
                    	System.out.println("added");
                    	System.out.println(team2.toString());
                    }
                    else
                    {
                    	System.out.println("Team full");
                    }
                }
            });
            add(t2, gbc);

        }

    }

}