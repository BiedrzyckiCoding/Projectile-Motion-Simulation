import javax.swing.*;
import java.util.ArrayList;

public class EulersMotionSimulation {

    public static void main(String[] args) {
        double gx = 0;
        double gy = -10;
        double dt = 0.1;

        //arrraylist for every collumn
        ArrayList<Double> sx = new ArrayList<>();
        ArrayList<Double> sy = new ArrayList<>();
        ArrayList<Double> vx = new ArrayList<>();
        ArrayList<Double> vy = new ArrayList<>();
        ArrayList<Double> dsx = new ArrayList<>();
        ArrayList<Double> dsy = new ArrayList<>();
        ArrayList<Double> dvx = new ArrayList<>();
        ArrayList<Double> dvy = new ArrayList<>();

        //initial values
        sx.add(0.0);
        sy.add(0.0);
        vx.add(10.0);
        vy.add(10.0);
        dsx.add(0.0);
        dsy.add(0.0);
        dvx.add(0.0);
        dvy.add(0.0);

        for (int i = 1; i < 22; i++) {
            //compute the changes
            double dvx_i = gx * dt;
            double dvy_i = gy * dt;

            double vx_i = vx.get(i - 1) + dvx_i;
            double vy_i = vy.get(i - 1) + dvy_i;

            double dsx_i = vx.get(i - 1) * dt;
            double dsy_i = vy.get(i - 1) * dt;

            double sx_i = sx.get(i - 1) + dsx_i;
            double sy_i = sy.get(i - 1) + dsy_i;

            //add values to the lists
            sx.add(sx_i);
            sy.add(sy_i);
            vx.add(vx_i);
            vy.add(vy_i);
            dsx.add(dsx_i);
            dsy.add(dsy_i);
            dvx.add(dvx_i);
            dvy.add(dvy_i);
        }

        //print results
        for (int i = 0; i < 22; i++) {
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
