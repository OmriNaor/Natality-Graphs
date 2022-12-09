import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Column extends Chart
{
    private static final int SPACE_BETWEEN_LINES = 30;

    /**
     * Constructor
     * @param data The data for the column chart
     * @param labels The labels for each data
     * Labels located at index i needs to match the data located at index i
     */
    public Column(ArrayList<Double> data, ArrayList<String> labels)
    {
        super(data, labels);
        this.data = data;
        this.labels = labels;
    }

    @Override
    /**
     * @param g Graphics to paint the chart
     * @param x X coordinate to start the chart at
     * @param y Y coordinate to start the chart at
     * @param width The wanted chart width
     * @param height The wanted chart height
     * Function draws the chart according to the given data, labels and arguments
     */
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        Random rnd = new Random();
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ariel", Font.PLAIN, 14));
        int zeroLinePosition = height / 50; // Location of the first background line (the 0 line)

        // Draw numbers and lines
        int startLinePosition = height - zeroLinePosition - 5;
        int i;
        int numOfLines = (height - y) / 35; // Number of backgrounds line depends on the frame size

        if (numOfLines < 1)
            return;

        int numFactor = (int) (getMax() * 2 / (numOfLines)); // Value to increase each line. Make sure that we reach the max value in the data.

        for (i = 0 ; i < numOfLines ; i++)
        {
            g.drawString("" + i * numFactor, x, startLinePosition);
            g.drawLine(x + 50, startLinePosition, width, startLinePosition);
            startLinePosition -= SPACE_BETWEEN_LINES;
        }



        // Draw labels and rectangles
        int labelPosition = x + 75; // Determine where to draw each label

        if (width < 600)
            labelPosition = x + 60;

        g.setFont(new Font("Ariel", Font.PLAIN, width / 100));

        int rectWidth = width / (this.labels.size() * 2); // Determine the width of each rectangle
        startLinePosition = height - zeroLinePosition - 5;

        for (i = 0 ; i < this.labels.size() ; i++)
        {
            if (this.labels.get(i) == null)
                continue;

            g.setColor(new Color(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat()));
            g.drawString("" + this.labels.get(i), labelPosition - this.labels.get(i).length(), height - zeroLinePosition + 10);

            // Calculate and draw rectangles
            double yPosition = this.data.get(i) / numFactor * SPACE_BETWEEN_LINES; // Calculate how high the rectangle needs to be in order to match the background line

            g.fillRect(labelPosition - this.labels.get(i).length(), startLinePosition - (int) yPosition, rectWidth, (int) yPosition);

            labelPosition += width / this.data.size() - 5;
        }
    }

    /**
     * @return the maximum value of the data in the list
     */
    private double getMax()
    {
        double max = Integer.MIN_VALUE;
        for (int i = 0 ; i < this.data.size() ; i++)
        {
            if (this.data.get(i) != null)
                if (this.data.get(i) > max)
                    max = this.data.get(i);
        }

        return max;
    }
}
