import javax.swing.*;
import java.util.ArrayList;

public class MidPointMotionSimulation {

    public static void main(String[] args) {
        double gx = 0; //gravity in x direction (0 for projectile motion)
        double gy = -10; //gravity in y direction
        double dt = 0.1; //time step
        double m = 1.0; //mass of object
        double kA = 0.1; //drag coefficient

        //arrraylist for every collumn
        ArrayList<Double> sx = new ArrayList<>();
        ArrayList<Double> sy = new ArrayList<>();
        ArrayList<Double> vx = new ArrayList<>();
        ArrayList<Double> vy = new ArrayList<>();

        //initial values
        sx.add(0.0);
        sy.add(0.0);
        vx.add(10.0);
        vy.add(10.0);

        for (int i = 1; i < 21; i++) {
            //compute midpoint velocities
            double vx_mid = vx.get(i - 1) + (dt / 2) * ((gx * m - kA * vx.get(i - 1)) / m);
            double vy_mid = vy.get(i - 1) + (dt / 2) * ((gy * m - kA * vy.get(i - 1)) / m);

            //compute midpoint acceleration
            double ax_mid = (gx * m - kA * vx_mid) / m;
            double ay_mid = (gy * m - kA * vy_mid) / m;

            //update velocity using midpoint acceleration
            double vx_i = vx.get(i - 1) + dt * ax_mid;
            double vy_i = vy.get(i - 1) + dt * ay_mid;

            //update position using midpoint velocity
            double sx_i = sx.get(i - 1) + dt * vx_mid;
            double sy_i = sy.get(i - 1) + dt * vy_mid;

            //add values to the lists
            sx.add(sx_i);
            sy.add(sy_i);
            vx.add(vx_i);
            vy.add(vy_i);
        }

        //print results
        for (int i = 0; i < 21; i++) {
            System.out.print(sx.get(i));
            System.out.print(" " + sy.get(i));
            System.out.print(" " + vx.get(i));
            System.out.print(" " + vy.get(i));
            System.out.println();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Projectile Motion");
            frame.setSize(1600, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new EulersMotionSimulationGui(sx, sy));
            frame.setVisible(true);
        });
    }

}
