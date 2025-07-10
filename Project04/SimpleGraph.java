/*
 * The purpose of this file is to create a simple graph to plot the 
 * data from the simulation.
 * Author: Svita Kiran
 * Date: 3/17/24
*/

import javax.swing.*;
import java.awt.*;

public class SimpleGraph extends JPanel {

    private double[] dataX;
    private double[] dataY;

    public SimpleGraph(double[] dataX, double[] dataY) {
        this.dataX = dataX;
        this.dataY = dataY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (double x : dataX) {
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
        }
        for (double y : dataY) {
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }

        int padding = 30;
        int width = getWidth() - 2 * padding;
        int height = getHeight() - 2 * padding;

        g.setColor(Color.BLACK);
        g.drawLine(padding, getHeight() - padding, padding, padding);
        g.drawLine(padding, getHeight() - padding, getWidth() - padding, getHeight() - padding);

        g.setColor(Color.RED);
        for (int i = 0; i < dataX.length; i++) {
            int x = padding + (int) ((dataX[i] - minX) / (maxX - minX) * width);
            int y = getHeight() - padding - (int) ((dataY[i] - minY) / (maxY - minY) * height);
            g.fillOval(x - 2, y - 2, 4, 4);
        }
    }

    public static void main(String[] args) {
        double[] dataX = {30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        double[] dataY = {1.2, 1.1, 1.05, 1.03, 1.02, 1.01, 1.0, 0.99, 0.98, 0.97, 0.96};

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().add(new SimpleGraph(dataX, dataY));
        frame.setVisible(true);
    }
}
