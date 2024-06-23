package fr.cs.group12.myFoodora.userInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import fr.cs.group12.myFoodora.myFoodora.MyFoodoraSystem;
import fr.cs.group12.myFoodora.user.Manager;
/**
 * The MyFoodoraGUI class provides a graphical user interface (GUI) for the MyFoodora application.
 * It allows users to interact with the MyFoodora system through a graphical interface.
 */
public class MyFoodoraGUI extends JFrame {

    private JTextPane displayArea;
    private JTextField inputField;
    private JButton executeButton;
    private MyFoodoraCLUI myFoodoraCLUI;
    private MyFoodoraSystem myFoodoraSystem;
    private SimpleAttributeSet userTextAttributes;
    private SimpleAttributeSet systemTextAttributes;
    /**
     * Constructs a new MyFoodoraGUI object.
     */
    public MyFoodoraGUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        myFoodoraSystem = new MyFoodoraSystem();
        myFoodoraCLUI = new MyFoodoraCLUI();
        // Perform initial setup
        initializeSystem();

        // Set up the frame
        setTitle("MyFoodora");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the display area
        displayArea = new JTextPane();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayArea.setBackground(Color.GRAY);
        displayArea.setForeground(Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Initialize text attributes
        userTextAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(userTextAttributes, Color.CYAN);
        StyleConstants.setBold(userTextAttributes, true);

        systemTextAttributes = new SimpleAttributeSet();
        StyleConstants.setForeground(systemTextAttributes, Color.GREEN);

        // Display welcome message
        appendToPane(displayArea, "Welcome to MyFoodora CLUI. Type 'help' for a list of commands.\n", systemTextAttributes);

        // Create the input field
        setInputField(new JTextField(30));
        getInputField().setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Create the execute button
        setExecuteButton(new JButton("Execute"));
        getExecuteButton().setFont(new Font("SansSerif", Font.BOLD, 14));
        getExecuteButton().setBackground(Color.DARK_GRAY);
        getExecuteButton().setForeground(Color.WHITE);

        // Set up the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        inputPanel.add(getInputField());
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(getExecuteButton());
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);

        // Add action listener to the button
        getExecuteButton().addActionListener(new ExecuteButtonListener());

        // Display the frame
        setVisible(true);
    }
    /**
     * Initializes the MyFoodora system.
     */
    public void initializeSystem() {
        Manager ceo = new Manager("ceo", "ceo", "1", "ceo", "123456789", myFoodoraSystem);
        Manager deputy = new Manager("deputy", "deputy", "1", "deputy", "123456789", myFoodoraSystem);
        MyFoodoraSystem.addUser(ceo);
        MyFoodoraSystem.addUser(deputy);
        System.setProperty("user.dir", Paths.get("build").toAbsolutePath().toString());
        String initpath ="./eval/my_foodora.ini";

        setup(initpath);
    }
    /**
     * Sets up the MyFoodora system.
     *
     * @param path The path to the initialization file.
     */
    public void setup(String path) {
        myFoodoraCLUI.setup(path);
    }

    /**
     * Action listener for the execute button. It listens for button clicks and executes
     * the command entered by the user in the input field.
     */
    private class ExecuteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = getInputField().getText();
            appendToPane(displayArea, "Command: " + input + "\n", userTextAttributes);
            executeCommand(input);
            getInputField().setText("");
        }
    }
    /**
     * Executes a command entered by the user.
     *
     * @param input The command entered by the user.
     */
    public void executeCommand(String input) {
        // Redirect System.out to displayArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(displayArea, systemTextAttributes));
        System.setOut(printStream);
        System.setErr(printStream);

        // Execute the command using MyFoodoraCLUI
        myFoodoraCLUI.executeCommand(input);
    }
    /**
     * Appends text to the JTextPane with specified attributes.
     *
     * @param tp      The JTextPane to append text to.
     * @param msg     The message to append.
     * @param attrSet The attributes to apply to the message.
     */
    public void appendToPane(JTextPane tp, String msg, AttributeSet attrSet) {
        try {
            Document doc = tp.getDocument();
            doc.insertString(doc.getLength(), msg, attrSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    /**
     * Main method to start the MyFoodoraGUI.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyFoodoraGUI());
    }

    /**
     * Retrieves the input field used for user input.
     *
     * @return The input field used for user input.
     */
    public JTextField getInputField() {
		return inputField;
	}
    /**
     * Sets the input field used for user input.
     *
     * @param inputField The input field to be set.
     */
	public void setInputField(JTextField inputField) {
		this.inputField = inputField;
	}
    /**
     * Retrieves the execute button used to trigger command execution.
     *
     * @return The execute button used to trigger command execution.
     */
	public JButton getExecuteButton() {
		return executeButton;
	}
    /**
     * Sets the execute button used to trigger command execution.
     *
     * @param executeButton The execute button to be set.
     */
	public void setExecuteButton(JButton executeButton) {
		this.executeButton = executeButton;
	}

    /**
     * Custom OutputStream to redirect console output to the JTextPane.
     */
    class CustomOutputStream extends OutputStream {
        private JTextPane textPane;
        private AttributeSet attrSet;

        public CustomOutputStream(JTextPane textPane, AttributeSet attrSet) {
            this.textPane = textPane;
            this.attrSet = attrSet;
        }

        @Override
        public void write(int b) {
            appendToPane(textPane, String.valueOf((char) b), attrSet);
            textPane.setCaretPosition(textPane.getDocument().getLength());
        }
    }
}
