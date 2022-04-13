import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StringSortSearch {

    private static JFrame mainFrame, sortFrame;
    private static JPanel mainPanel, fieldPanel, labelPanel, buttonPanel, exitPanel, sortPanel;
    private static JLabel stringLabel, maxElementsLabel, sortHeadingLabel;
    private static JTextArea sortResultArea;
    private static JTextField stringField;
    private static JButton submitButton, sortButton, searchButton, exitButton, sortOkButton;

    private static String[] strings = new String[10];
    private static int arrIndex = 0;

    public static void main(String[] args)
    {
        mainFrame = new JFrame();

        fieldPanel = new JPanel(new FlowLayout());
        stringLabel = new JLabel("Enter a string");
        stringField = new JTextField(10);
        fieldPanel.add(stringLabel);
        fieldPanel.add(stringField);

        labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        maxElementsLabel = new JLabel("");
        labelPanel.add(maxElementsLabel);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        sortButton = new JButton("Sort");
        searchButton = new JButton("Search");
        buttonPanel.add(submitButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(searchButton);

        exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitButton = new JButton("Exit");
        exitPanel.add(exitButton);

        mainPanel = new JPanel(new GridLayout(4, 0));
        mainPanel.add(fieldPanel);
        mainPanel.add(labelPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(exitPanel);

        mainFrame.add(mainPanel);

        mainFrame.setSize(300, 300);
        mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        submitButton.addActionListener((ActionEvent e) -> {
            submitButtonAction();
        });

        sortButton.addActionListener((ActionEvent e) -> {
            sortButtonAction();
        });

        searchButton.addActionListener((ActionEvent e) -> {
            searchButtonAction();
        });

        exitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    public static void submitButtonAction()
    {
        String word;
        if(stringField.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Integer field is mandatory!", "Field Missing Alert", JOptionPane.ERROR_MESSAGE);
        else
        {
            word = stringField.getText().trim();
            if(arrIndex == 10)
                maxElementsLabel.setText("Array can have a maximum of 10 elements");
            else
            {
                if(!isElementPresent(word))
                    strings[arrIndex++] = word;
                else if(isElementPresent(word))
                    JOptionPane.showMessageDialog(null, word + " already present!");
            }
        }
    }

    public static boolean isElementPresent(String str)
    {
        boolean found = false;
        for(int i = 0; i < strings.length; i++)
        {
            if(strings[i] == str)
            {
                found = true;
                break;
            }
        }
        return found;
    }

    public static void sortButtonAction()
    {
        if(arrIndex == 0)
        {
            JOptionPane.showMessageDialog(null, "No elements to sort!");
        }
        else
        {
            sortFrame = new JFrame();
            sortPanel = new JPanel(new GridLayout(3, 0));
            sortHeadingLabel = new JLabel("Array after sorting:");
            sortResultArea = new JTextArea();
            sortResultArea.setEditable(false);
            sortOkButton = new JButton("Ok");
            sortPanel.add(sortHeadingLabel);
            sortPanel.add(sortResultArea);
            sortPanel.add(sortOkButton);
            sortFrame.add(sortPanel);
            sortFrame.setSize(300, 200);
            sortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sortFrame.setResizable(false);
            sortFrame.setUndecorated(true);
            sortFrame.setLocationRelativeTo(null);
            sortFrame.setVisible(true);

            sortOkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sortFrame.setVisible(false);
                }
            });

            String temp;
            for(int i = 0; i < strings.length; i++)
            {
                for(int j = i + 1; j < strings.length; j++)
                {
                    if(strings[i].length() > strings[j].length())
                    {
                        temp = strings[i];
                        strings[i] = strings[j];
                        strings[j] = temp;
                    }
                }
            }

            for(int i = 0; i < strings.length; i++)
            {
                if(i == strings.length)
                    sortResultArea.append(strings[i] + "");
                else
                    sortResultArea.append(strings[i] + " ");
            }
        }
    }

    public static void searchButtonAction()
    {
        String word;
        if(stringField.getText().equals(""))
            JOptionPane.showMessageDialog(null, "String field is mandatory!", "Field Missing Alert", JOptionPane.ERROR_MESSAGE);
        else
        {
            word = stringField.getText().trim();
            for(int i = 0; i < strings.length; i++)
            {
                if(i == Arrays.asList(strings).indexOf(word))
                {
                    maxElementsLabel.setText("Element " + word + " found at position: " + (i + 1) + ".");
                }
            }
        }
    }
}