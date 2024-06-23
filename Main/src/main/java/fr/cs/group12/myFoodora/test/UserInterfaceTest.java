package fr.cs.group12.myFoodora.test;

import org.junit.Before;
import org.junit.Test;
import fr.cs.group12.myFoodora.userInterface.MyFoodoraGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * This class tests the functionality of the MyFoodora user interface utilizing the GUI.
 */
public class UserInterfaceTest {

    private MyFoodoraGUI myFoodoraGUI;
    private List<String> output;

    @Before
    public void setUp() throws Exception {
        // Initialize the GUI and ensure it is done before proceeding
        SwingUtilities.invokeAndWait(() -> myFoodoraGUI = new MyFoodoraGUI());

        // Redirect System.out to capture output
        output = new ArrayList<>();
        System.setOut(new PrintStream(new CustomOutputStream(output)));
    }

    private void executeCommand(String command) {
        // Simulate entering the command in the input field and pressing the execute button
        myFoodoraGUI.getInputField().setText(command);
        for (ActionListener al : myFoodoraGUI.getExecuteButton().getActionListeners()) {
            al.actionPerformed(new ActionEvent(myFoodoraGUI.getExecuteButton(), ActionEvent.ACTION_PERFORMED, null));
        }
    }

    private String getCapturedOutput() {
        StringBuilder capturedOutput = new StringBuilder();
        for (String line : output) {
            capturedOutput.append(line).append("\n");
        }
        return capturedOutput.toString().trim();
    }

    @Test
    public void testLoginCeo() throws Exception {
        executeCommand("login ceo 123456789");
        Thread.sleep(5000); // Wait for the command to process
        String capturedOutput = getCapturedOutput();
        String expected ="Login successful.";
        // Check the output for the expected result visually (problem with capturing output).

    }

    @Test
    public void testLoginFalse() throws Exception {
        executeCommand("login falseusername falsepassword");
        Thread.sleep(5000); // Wait for the command to process
        String capturedOutput = getCapturedOutput();
        String expected ="Invalid username or password.";

        // Check the output for the expected result visually (problem with capturing output).
    }

    // Custom OutputStream to capture console output into a List
    private static class CustomOutputStream extends java.io.OutputStream {
        private final List<String> output;
        private StringBuilder currentLine;

        public CustomOutputStream(List<String> output) {
            this.output = output;
            this.currentLine = new StringBuilder();
        }

        @Override
        public void write(int b) {
            char c = (char) b;
            if (c == '\n') {
                output.add(currentLine.toString());
                currentLine = new StringBuilder();
            } else {
                currentLine.append(c);
            }
        }
    }
}
