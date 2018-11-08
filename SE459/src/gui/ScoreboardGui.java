package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



import base.PointsKeeperSingleton;

public class ScoreboardGui {


	    public static void main(String[] args) {
	        new ScoreboardGui();
	    }

	    public ScoreboardGui() {
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
//	                try {
//	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//	                    ex.printStackTrace();
//	                }

	                JFrame frame = new JFrame("Score Board");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.add(new ScorePane());
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	    }

	    @SuppressWarnings("serial")
		public class ScorePane extends JPanel {

	        private JLabel label;
	        //private Team teamSet = new Team();
	        int team1 = PointsKeeperSingleton.getUniqueInstance().getTeamScore("1");
	        int team2 = PointsKeeperSingleton.getUniqueInstance().getTeamScore("2");


	        public ScorePane() {
	            setLayout(new GridBagLayout());
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.insets = new Insets(5, 5, 5, 5);
	            gbc.gridwidth = GridBagConstraints.REMAINDER;

	            label = new JLabel("Scores");
	            add(label, gbc);
	            
	            label = new JLabel("Team 1");
	            label.setText("Team 1");
	            add(label, gbc);
	            
	            
	            label = new JLabel("Team 1 Scores");
	            label.setText(Integer.toString(team1));
	            add(label, gbc);
	            
	            
	            label = new JLabel("Team 2");
	            label.setText("Team 2");
	            add(label, gbc);
	            
	            label = new JLabel("Team 2 Scores");
	            label.setText(Integer.toString(team2));
	            add(label, gbc);
	            


	            Font labelFont = label.getFont();
	            String labelText = label.getText();

	            int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
	            int componentWidth = label.getWidth();

	            // Find out how much the font can grow in width.
	            double widthRatio = (double)componentWidth / (double)stringWidth;

	            int newFontSize = (int)(labelFont.getSize() * widthRatio);
	            int componentHeight = label.getHeight();

	            // Pick a new font size so it will not be larger than the height of label.
	            int fontSizeToUse = Math.min(newFontSize, componentHeight);

	            // Set the label's font size to the newly determined size.
	            label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
	            
	            
	        }

	    }

	}
