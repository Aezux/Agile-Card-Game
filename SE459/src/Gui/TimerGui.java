package base;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TimerGui {

    public static void main(String[] args) {
        new TimerGui();
    }

    public TimerGui() {
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

        private LocalDateTime startTime;
        private JLabel label;
        private Timer timer;
        private Duration duration = Duration.ofMinutes(3);

        public TestPane() {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            label = new JLabel("...");
            add(label, gbc);

            final JButton btn = new JButton("Start");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timer.isRunning()) {
                        timer.stop();
                        startTime = null;
                        btn.setText("Start");
                    } else {
                        startTime = LocalDateTime.now();
                        timer.start();
                        btn.setText("Stop");
                    }
                }
            });
            add(btn, gbc);

            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime now = LocalDateTime.now();
                    Duration runningTime = Duration.between(startTime, now);
                    Duration timeLeft = duration.minus(runningTime);
                    if (timeLeft.isZero() || timeLeft.isNegative()) {
                        timeLeft = Duration.ZERO;
                        btn.doClick(); // Cheat
                    }

                    label.setText(format(timeLeft));
                }
            });
        }

        protected String format(Duration duration) {
            long hours = duration.toHours();
            long mins = duration.minusHours(hours).toMinutes();
            long seconds = duration.minusMinutes(mins).toMillis() / 1000;
            return String.format("%02dm %02ds", mins, seconds);
        }

    }

}