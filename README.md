# Java Project: Natality-Graphs

## About

Reads natality data from a CSV file and display it, along with different calculations, using two different types of graphs.

## Usage

1. Clone ["NatalityMini.csv"](https://github.com/OmriNaor/Natality-Graphs/blob/main/NatalityMini.csv)
2. Locate the CSV file inside the Java files package

## Structure

- `Main` - Main function definition. Creates the frame and its defaults (using Java swing).
- `Chart` - An abstract class to define a chart instance variables and functions.
- `Column` - Extends the abstract chart class. Implementation of a column graph type including drawing.
- `Pie` - Extends the abstract chart class. Implementation of a pie graph type including drawing.
- `NatalityPanel` - Extends JPanel and implements MouseListener. Reads the details from the CSV file and gets input from the user. Displays the graphs accordingly.

## The Algorithm

The program reads data from a CSV file using Java's File and BufferedReader. The program then proceeds to process the data and make different calculations.

Finally, creates a dynamic JPanel and two different types of graphs and displays one of them, according to the user's input. 

The algorithm supports and displays extra data that the user can add by using the JButtons.

## Examples

* Pie chart:

![image](https://user-images.githubusercontent.com/106623821/206693822-c07acbd8-78de-427d-a84d-e9d20c24b772.png)

* Column chart:

![image](https://user-images.githubusercontent.com/106623821/206694254-992dd657-395c-4e46-9396-0da782da3689.png)




