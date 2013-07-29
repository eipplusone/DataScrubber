package com.pearson.Utilities;

import com.pearson.Readers.RuleReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruslan Kiselev
 *         Date: 7/22/13
 *         Time: 2:08 PM
 *         Project Name: DataScrubber
 */
public class ThreadExectutor {

    private static int numberOfThreadsAllowed;
    private static ExecutorService executor;
    private static Set<String> tablesOccupied;

    public static void initialise(int numberOfThreadsAllowed_) {

    }

    public static void submit(RuleReader reader){

        executor.submit(reader);
    }

    public static boolean isTableOccupied(String tableName){
        return tablesOccupied.contains(tableName);
    }

    public static void shutDown() throws InterruptedException {

    }
}
