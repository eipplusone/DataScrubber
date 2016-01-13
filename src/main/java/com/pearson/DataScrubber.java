package com.pearson;

import com.pearson.Interface.Windows.MainWindow;
import static spark.Spark.*;

import javax.swing.*;

/**
 * Created by voltecrus on 8/19/14.
 */
public class DataScrubber {
    // public static void main(final String[] args) {
        // SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run() {

        //         String[] argCopy = args.clone();
        //         new MainWindow(argCopy).setVisible(true);
        //     }
        // });
    // }
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

        post("/submit_set", (req, res) -> {
                System.out.println(req.body());
                return req.body();
            });

        get("/hello/:name", (request, response) -> {
                return "Hello: " + request.params(":name");
            });
    }
}
