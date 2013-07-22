package com.pearson.Utilities;

import com.pearson.Readers.RuleReader;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        numberOfThreadsAllowed = numberOfThreadsAllowed_;
        executor = Executors.newFixedThreadPool(numberOfThreadsAllowed);
        tablesOccupied = Collections.synchronizedSet(new HashSet<String>());
    }

    public static void execute(RuleReader reader){

        executor.execute(reader);
    }

    public static boolean isTableOccupied(String tableName){
        return tablesOccupied.contains(tableName);
    }
}
