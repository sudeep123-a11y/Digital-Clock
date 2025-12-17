import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalClock extends JFrame implements Runnable {

    Thread t = null;
    
    JLabel timeLabel = new JLabel();
    JLabel dateLabel = new JLabel();
    JLabel dayLabel = new JLabel();

    public DigitalClock() {
        setTitle("Luxury Clock");
        setSize(300, 170); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
        
        // Background: Matte Charcoal (Dark Grey)
        panel.setBackground(new Color(30, 30, 30)); 

        // --- CHANGES HERE ---
        
        // 1. Time Color: Rose Gold
        // RGB: 227, 168, 175
        // 2. Size: Reduced to 35
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35)); 
        timeLabel.setForeground(new Color(227, 168, 175)); // Rose Gold Color
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Day: Silver / Muted Grey
        dayLabel.setFont(new Font("Serif", Font.ITALIC, 16)); 
        dayLabel.setForeground(new Color(192, 192, 192)); 
        dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date: Silver / Muted Grey
        dateLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(192, 192, 192)); 
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Layout Spacing
        panel.add(Box.createVerticalGlue()); 
        panel.add(timeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); 
        panel.add(dayLabel);
        panel.add(dateLabel);
        panel.add(Box.createVerticalGlue()); 

        add(panel);

        t = new Thread(this);
        t.start();

        setVisible(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Calendar cal = Calendar.getInstance();
                
                SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
                String time = timeFormatter.format(cal.getTime());

                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
                String date = dateFormatter.format(cal.getTime());

                SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
                String day = dayFormatter.format(cal.getTime());

                timeLabel.setText(time);
                dateLabel.setText(date);
                dayLabel.setText(day);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DigitalClock();
    }
}