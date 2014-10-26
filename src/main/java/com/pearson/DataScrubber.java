package com.pearson;

import com.pearson.Interface.Windows.MainWindow;

import javax.swing.*;

/**
 * Created by voltecrus on 8/19/14.
 */
public class DataScrubber {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String[] argCopy = args.clone();
                new MainWindow(argCopy).setVisible(true);
            }
        });
    }
}
