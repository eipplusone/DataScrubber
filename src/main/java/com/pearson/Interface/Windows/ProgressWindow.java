package com.pearson.Interface.Windows;

import com.pearson.Readers.SetReader;
import com.pearson.Utilities.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by voltecrus on 8/12/14.
 */
public class ProgressWindow extends JDialog {
    private JPanel viewer;
    private JProgressBar progressBar;
    private JLabel setName;
    private JTextArea logArea;

    private static final int borderSize = 10;
    private static final int windowSize = 600;

    public ProgressWindow(SetReader reader) {
        this();
        setName.setText("Running");
        if (Constants.IN_DEVELOPMENT) System.out.println("Finished running " +
                "Progress Window loop");
    }

    private ProgressWindow() {

        viewer = new JPanel();
        progressBar = new JProgressBar();
        setName = new JLabel("This should be the set' name");
        logArea = new JTextArea();

        BoxLayout panelLayout = new BoxLayout(viewer, BoxLayout.Y_AXIS);
        viewer.setLayout(panelLayout);
        // padding
        viewer.setBorder(new EmptyBorder(borderSize, borderSize, borderSize, borderSize));

        viewer.add(setName);
        viewer.add(progressBar);
        viewer.add(logArea);
        add(viewer);

        // todo figure out how to make it centered and regular sized without having
        // it to be set to the right size
        setBounds(windowSize, windowSize, windowSize, windowSize);

        setModalityType(ModalityType.APPLICATION_MODAL);
        // Center the window
        setLocationRelativeTo(null);
    }

    /**
     * Updates log with messages and separates them with new line chars
     */
    public void updateLogText(String... messages) {
        for (String message : messages) logArea.append(message + "\n");
    }

    /**
     * Useful in debugging to make just one window appear
     *
     * @param args
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProgressWindow().setVisible(true);
            }
        });
    }

    public int getProgress() {
        return progressBar.getValue();
    }

    public void setProgress(int progress) {
        progressBar.setValue(progress);
    }

    public void setLabelName(String labelName) {
        setName.setText(labelName);
    }
}
