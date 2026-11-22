import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PizzaGUIFrame extends JFrame
{
    private JRadioButton thinButton;
    private JRadioButton regularButton;
    private JRadioButton deepDishButton;

    private JComboBox<String> sizeCombo;

    private JCheckBox pepperoniBox;
    private JCheckBox sausageBox;
    private JCheckBox mushroomsBox;
    private JCheckBox onionsBox;
    private JCheckBox baconBox;
    private JCheckBox pineappleBox;

    private JTextArea receiptArea;

    public PizzaGUIFrame()
    {
        setTitle("Pizza Order Form");
        setSize(900,600);
        setLocationRelativeTo(null);

        buildGUI();
    }

    private void buildGUI()
    {
        setLayout(new BorderLayout());

        //---------------------------------
        // Crust Panel
        //---------------------------------

        JPanel crustPanel = new JPanel();

        crustPanel.setBorder(
                new TitledBorder("Crust Type"));

        thinButton =
                new JRadioButton("Thin");

        regularButton =
                new JRadioButton("Regular");

        deepDishButton =
                new JRadioButton("Deep Dish");

        ButtonGroup crustGroup =
                new ButtonGroup();

        crustGroup.add(thinButton);
        crustGroup.add(regularButton);
        crustGroup.add(deepDishButton);

        crustPanel.add(thinButton);
        crustPanel.add(regularButton);
        crustPanel.add(deepDishButton);

        //---------------------------------
        // Size Panel
        //---------------------------------

        JPanel sizePanel = new JPanel();

        sizePanel.setBorder(
                new TitledBorder("Size"));

        String[] sizes =
                {"Small","Medium",
                        "Large","Super"};

        sizeCombo =
                new JComboBox<>(sizes);

        sizePanel.add(sizeCombo);

        //---------------------------------
        // Toppings Panel
        //---------------------------------

        JPanel toppingPanel =
                new JPanel(new GridLayout(3,2));

        toppingPanel.setBorder(
                new TitledBorder("Toppings"));

        pepperoniBox =
                new JCheckBox("Pepperoni");

        sausageBox =
                new JCheckBox("Sausage");

        mushroomsBox =
                new JCheckBox("Mushrooms");

        onionsBox =
                new JCheckBox("Onions");

        baconBox =
                new JCheckBox("Bacon");

        pineappleBox =
                new JCheckBox("Pineapple");

        toppingPanel.add(pepperoniBox);
        toppingPanel.add(sausageBox);
        toppingPanel.add(mushroomsBox);
        toppingPanel.add(onionsBox);
        toppingPanel.add(baconBox);
        toppingPanel.add(pineappleBox);

        //---------------------------------
        // Top Area
        //---------------------------------

        JPanel optionsPanel =
                new JPanel(new GridLayout(1,3));

        optionsPanel.add(crustPanel);
        optionsPanel.add(sizePanel);
        optionsPanel.add(toppingPanel);

        //---------------------------------
        // Receipt Area
        //---------------------------------

        JPanel receiptPanel =
                new JPanel(new BorderLayout());

        receiptPanel.setBorder(
                new TitledBorder("Receipt"));

        receiptArea =
                new JTextArea(15,40);

        receiptArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(receiptArea);

        receiptPanel.add(scrollPane);

        //---------------------------------
        // Buttons
        //---------------------------------

        JPanel buttonPanel = new JPanel();

        JButton orderButton =
                new JButton("Order");

        JButton clearButton =
                new JButton("Clear");

        JButton quitButton =
                new JButton("Quit");

        buttonPanel.add(orderButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        //---------------------------------
        // Listeners
        //---------------------------------

        orderButton.addActionListener(
                e -> createReceipt());

        clearButton.addActionListener(
                e -> clearForm());

        quitButton.addActionListener(
                e -> quitProgram());

        //---------------------------------
        // Layout
        //---------------------------------

        add(optionsPanel,
                BorderLayout.NORTH);

        add(receiptPanel,
                BorderLayout.CENTER);

        add(buttonPanel,
                BorderLayout.SOUTH);
    }

    private void createReceipt()
    {
        String crust = "";

        if(thinButton.isSelected())
            crust = "Thin";

        else if(regularButton.isSelected())
            crust = "Regular";

        else if(deepDishButton.isSelected())
            crust = "Deep Dish";

        if(crust.equals(""))
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a crust.");

            return;
        }

        String toppings = "";
        int toppingCount = 0;

        if(pepperoniBox.isSelected())
        {
            toppings += "Pepperoni\n";
            toppingCount++;
        }

        if(sausageBox.isSelected())
        {
            toppings += "Sausage\n";
            toppingCount++;
        }

        if(mushroomsBox.isSelected())
        {
            toppings += "Mushrooms\n";
            toppingCount++;
        }

        if(onionsBox.isSelected())
        {
            toppings += "Onions\n";
            toppingCount++;
        }

        if(baconBox.isSelected())
        {
            toppings += "Bacon\n";
            toppingCount++;
        }

        if(pineappleBox.isSelected())
        {
            toppings += "Pineapple\n";
            toppingCount++;
        }

        if(toppingCount == 0)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Select at least one topping.");

            return;
        }

        String size =
                (String)sizeCombo.getSelectedItem();

        double sizePrice = 0;

        switch(size)
        {
            case "Small":
                sizePrice = 8.00;
                break;

            case "Medium":
                sizePrice = 12.00;
                break;

            case "Large":
                sizePrice = 16.00;
                break;

            case "Super":
                sizePrice = 20.00;
                break;
        }

        double toppingPrice =
                toppingCount * 1.00;

        double subtotal =
                sizePrice + toppingPrice;

        double tax =
                subtotal * .07;

        double total =
                subtotal + tax;

        String receipt = "";

        receipt +=
                "====================================\n";

        receipt +=
                "Crust: "
                        + crust + "\n";

        receipt +=
                "Size: "
                        + size
                        + "    $"
                        + String.format("%.2f",
                        sizePrice)
                        + "\n\n";

        receipt +=
                "Ingredients:\n";

        receipt += toppings + "\n";

        receipt +=
                "Topping Cost: $"
                        + String.format("%.2f",
                        toppingPrice)
                        + "\n\n";

        receipt +=
                "Sub-total: $"
                        + String.format("%.2f",
                        subtotal)
                        + "\n";

        receipt +=
                "Tax: $"
                        + String.format("%.2f",
                        tax)
                        + "\n";

        receipt +=
                "------------------------------------\n";

        receipt +=
                "Total: $"
                        + String.format("%.2f",
                        total)
                        + "\n";

        receipt +=
                "====================================";

        receiptArea.setText(receipt);
    }

    private void clearForm()
    {
        thinButton.setSelected(false);
        regularButton.setSelected(false);
        deepDishButton.setSelected(false);

        sizeCombo.setSelectedIndex(0);

        pepperoniBox.setSelected(false);
        sausageBox.setSelected(false);
        mushroomsBox.setSelected(false);
        onionsBox.setSelected(false);
        baconBox.setSelected(false);
        pineappleBox.setSelected(false);

        receiptArea.setText("");
    }

    private void quitProgram()
    {
        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to quit?",
                        "Quit",
                        JOptionPane.YES_NO_OPTION);

        if(choice ==
                JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
}