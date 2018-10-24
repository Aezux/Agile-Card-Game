package Gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import base.Team;

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

                JFrame frame = new JFrame("Join Team");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TeamPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TeamPane extends JPanel {

        private JLabel label;
        private Team teamSet = new Team();
        private String name;
        JTextField txtInput;


        public TeamPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            label = new JLabel("Enter your name and choose a team");
            add(label, gbc);

            txtInput = new JTextField("",30);
            add(txtInput,gbc);
            
            final JButton t1 = new JButton("Team1");
            t1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	name = txtInput.getText();
                    if(teamSet.getTeam1Size() <= 5){
                    	teamSet.addMember(name,"team1");
                    	System.out.println("added");
                    	System.out.println(teamSet.getTeam1());
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
                	name = txtInput.getText();
                    if(teamSet.getTeam2Size() <= 5){
                    	teamSet.addMember(name,"team2");
                    	System.out.println("added");
                    	System.out.println(teamSet.getTeam2());
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