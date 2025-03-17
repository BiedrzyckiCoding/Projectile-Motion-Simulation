import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MidPointMotionSimulationGui extends JPanel {
    private final ArrayList<Double> sx;
    private final ArrayList<Double> sy;
    private static final int SCALE = 50; // Scale factor for visualization
    private static final int PADDING = 50; // Padding around the graph
    public MidPointMotionSimulationGui(ArrayList<Double> sx, ArrayList<Double> sy) {
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Set line thickness

        int height = getHeight();
        int width = getWidth();

        // Draw grid lines
        g2d.setColor(new Color(200, 200, 200)); // Light gray grid
        for (int i = 0; i < width; i += SCALE) {
            g2d.drawLine(i + PADDING, height - PADDING, i + PADDING, PADDING);
        }
        for (int i = 0; i < height; i += SCALE) {
            g2d.drawLine(PADDING, height - i - PADDING, width - PADDING, height - i - PADDING);
        }

        // Draw axes
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Thicker axis lines
        g2d.drawLine(PADDING, height - PADDING, width - PADDING, height - PADDING); // X-axis
        g2d.drawLine(PADDING, height - PADDING, PADDING, PADDING); // Y-axis

        // Draw tick marks and labels on X and Y axes
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= (width - 2 * PADDING) / SCALE; i++) {
            int x = i * SCALE + PADDING;
            g2d.drawLine(x, height - PADDING - 5, x, height - PADDING + 5);
            g2d.drawString(String.valueOf(i), x - 5, height - PADDING + 20);
        }
        for (int i = 0; i <= (height - 2 * PADDING) / SCALE; i++) {
            int y = height - i * SCALE - PADDING;
            g2d.drawLine(PADDING - 5, y, PADDING + 5, y);
            g2d.drawString(String.valueOf(i), PADDING - 30, y + 5);
        }

        // Label axes
        g2d.drawString("X (Distance)", width - PADDING, height - PADDING + 30);
        g2d.drawString("Y (Height)", PADDING - 40, PADDING - 10);

        // Draw trajectory
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3)); // Make trajectory line thicker
        for (int i = 0; i < sx.size() - 1; i++) {
            int x1 = (int) (sx.get(i) * SCALE) + PADDING;
            int y1 = height - (int) (sy.get(i) * SCALE) - PADDING;
            int x2 = (int) (sx.get(i + 1) * SCALE) + PADDING;
            int y2 = height - (int) (sy.get(i + 1) * SCALE) - PADDING;

            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
