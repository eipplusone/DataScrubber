package com.pearson;

import com.pearson.Interface.Windows.MainWindow;

import javax.swing.*;

/**
 * Created by voltecrus on 8/19/14.
 */
public class DataScrubber {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
