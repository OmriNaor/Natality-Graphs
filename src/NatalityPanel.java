import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class NatalityPanel extends JPanel implements MouseListener
{
    private JButton cmdClear, cmdChar, cmdInstructions, cmdAddData;
    private JComboBox<String> comTable;
    private Chart[] chart;
    private int paintStatus; // To hold the current paint status
    private File f;
    private FileReader fr;
    private BufferedReader br;
    private static final String COMBOBOX_GENDER = "Gender";
    private static final String COMBOBOX_MONTHS = "Birth Months";
    private static final int PIE_INDEX = 0;
    private static final int COLUMN_INDEX = 1;
    private static final int CLEAR_PAINT = -1;

    /**
     * Default Constructor.
     * Reads data from a file and creates the charts depends on the data
     */
    public NatalityPanel()
    {
        this.chart = new Chart[2];
        int maleCounter = 0;
        int femaleCounter = 0;
        this.paintStatus = CLEAR_PAINT;

        this.cmdClear = new JButton("Clear");
        this.cmdChar = new JButton("Show Chart");
        this.cmdInstructions = new JButton("Instructions");
        this.cmdAddData = new JButton("Add Data");
        this.comTable = new JComboBox();

        this.comTable.insertItemAt(COMBOBOX_GENDER, 0);
        this.comTable.insertItemAt(COMBOBOX_MONTHS, 1);

        add(this.cmdInstructions);
        add(this.cmdAddData);
        add(this.cmdChar);
        add(this.cmdClear);
        add(this.comTable);

        this.cmdClear.addMouseListener(this);
        this.cmdChar.addMouseListener(this);
        this.cmdInstructions.addMouseListener(this);
        this.cmdAddData.addMouseListener(this);

        this.f = new File("NatalityMini.csv");
        int[] months = new int[13]; // 12 months, starting from 1 to 12.

        // Read and count gender + month of birth from the file
        try
        {
            this.fr = new FileReader(f);
            this.br = new BufferedReader(fr);
            while(true)
            {
                // First check the gender by getting the last char in every line
                String s = br.readLine();
                if (s == null)
                    break;

                String gender = s.substring(8, 9);
                if (gender.equals("M"))
                    maleCounter++;
                else
                    femaleCounter++;

                // Continue to check the month of birth by taking the middle location in every line
                String monthString = s.substring(5, 7);
                if (monthString == null)
                    break;

                int monthInt = Integer.parseInt(monthString); // Convert the string to int
                months[monthInt]++;
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }

        catch (EOFException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<Double> dataPie = new ArrayList<>();
        dataPie.add((double) maleCounter);
        dataPie.add((double) femaleCounter);

        ArrayList<String> labelsPie = new ArrayList<>();
        labelsPie.add("Male");
        labelsPie.add("Female");


        this.chart[PIE_INDEX] = new Pie(dataPie, labelsPie);

        ArrayList<Double> dataColumn = new ArrayList<>();
        ArrayList<String> labelsColumn = new ArrayList<>();

        // Initialise the months birth details
        for (int i = 1 ; i < months.length ; i++)
            dataColumn.add((double) months[i]);

        labelsColumn.add("January");
        labelsColumn.add("February");
        labelsColumn.add("March");
        labelsColumn.add("April");
        labelsColumn.add("May");
        labelsColumn.add("June");
        labelsColumn.add("July");
        labelsColumn.add("August");
        labelsColumn.add("September");
        labelsColumn.add("October");
        labelsColumn.add("November");
        labelsColumn.add("December");

        this.chart[COLUMN_INDEX] = new Column(dataColumn, labelsColumn);
    }


    @Override
    /**
     * Handling the mouse clicked events and display the correct chart.
     */
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == cmdInstructions)
            JOptionPane.showMessageDialog(null,
                    "This is a dynamic project. You may change the size of the frame as well as add as much data as you'd like to!" +
                            "\nThe bigger the frame, the easier to see the details.");
        else
        {
            if (e.getSource() == cmdChar && this.comTable.getSelectedItem() == COMBOBOX_GENDER)
            {
                paintStatus = PIE_INDEX;
                repaint();
            }

            if (e.getSource() == cmdChar && this.comTable.getSelectedItem() == COMBOBOX_MONTHS)
            {
                paintStatus = COLUMN_INDEX;
                repaint();
            }

            if(e.getSource() == cmdClear)
            {
                paintStatus = CLEAR_PAINT;
                repaint();
            }

            if (e.getSource() == cmdAddData)
            {
                String[] options = {"Pie Chart", "Column Chart"};
                int chartIndex = JOptionPane.showOptionDialog(null, "To which chart would you like to add the data?",
                        "Click a button",  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                String newLabel = JOptionPane.showInputDialog(null, "Enter the new data label");
                String newData = JOptionPane.showInputDialog(null, "Enter the new data value");

                if (newLabel != null && newData != null && !newLabel.isEmpty() && !newData.isEmpty() && Double.parseDouble(newData) >= 0)
                {
                    this.chart[chartIndex].add(Double.parseDouble(newData), newLabel);
                    repaint();
                }
            }
        }
    }

    public void paintComponent(Graphics g)
    {
         super.paintComponent(g);

        if(paintStatus == CLEAR_PAINT)
            repaint();

        if(paintStatus == PIE_INDEX)
            this.chart[PIE_INDEX].draw(g, getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);

        if(paintStatus == COLUMN_INDEX)
           this.chart[COLUMN_INDEX].draw(g, 20, 50, getWidth() - 20, getHeight() - 20);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
