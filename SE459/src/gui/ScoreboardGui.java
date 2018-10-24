package gui;

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

public class ScoreboardGui {


	    public static void main(String[] args) {
	        new ScoreboardGui();
	    }

	    public ScoreboardGui() {
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                    ex.printStackTrace();
	                }

	                JFrame frame = new JFrame("Score Board");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.add(new ScorePane());
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	    }

	    public class ScorePane extends JPanel {

	        private JLabel label;
	        //private Team teamSet = new Team();



	        public ScorePane() {
	            setLayout(new GridBagLayout());
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.insets = new Insets(5, 5, 5, 5);
	            gbc.gridwidth = GridBagConstraints.REMAINDER;

	            label = new JLabel("Scores");
	            add(label, gbc);


	        }

	    }

	}
