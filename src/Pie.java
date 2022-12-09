import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pie extends Chart
{

    /**
     * Constructor
     * @param data The data for the column chart
     * @param labels The labels for each data
     * Labels located at index i needs to match the data located at index i
     */
    public Pie(ArrayList<Double> data, ArrayList<String> labels)
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
        double sum = getSum(); // Get the total amount of data
        double percentData = 0; // To hold the current data's percentage out of the total
        double sliceSize = 0; // To hold the size of each data in pie's slice
        double nextSliceLocation = 0;
        int labelPosition = x; // To hold the labels position
        int yLabelPosition = y + height;
        Random rnd = new Random();

        g.setFont(new Font("Ariel", Font.BOLD, 12));

        for (int i = 0; i < this.data.size(); i++)
        {
            // Draw pie chart

            g.setColor(new Color(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat()));
            percentData = this.data.get(i) / sum * 100; // Calculate percent of each data

            sliceSize = percentData * 3.6; // Calculate the size of the pie slice
            g.fillArc(x, y, width, height, (int) Math.round(nextSliceLocation), (int) Math.ceil(sliceSize));
            nextSliceLocation += sliceSize;

            // Draw labels

            g.fillRect(labelPosition, yLabelPosition + 20, 5, 5);
            g.setColor(Color.BLACK);

            g.drawString("" + this.labels.get(i) + "  " + String.format("%.2f", percentData) + "%", labelPosition + 10, yLabelPosition + 25);
            labelPosition += this.labels.get(i).length() + 100;

            if (labelPosition + 50 >= width + x)
            {
                labelPosition = x;
                yLabelPosition += 20;
            }

        }
    }

    /**
     * @return the sum value of the data in the list
     */
    private double getSum()
    {
        double sum = 0;
        for (int i = 0; i < this.data.size(); i++)
            sum += this.data.get(i);

        return sum;
    }
}
