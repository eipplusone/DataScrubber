package com.pearson;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Ruslan Kiselev
 *         Date: 6/17/13
 *         Time: 4:48 PM
 *         Project Name: DataScrubber
 */
public class LauncherTry {

    public static void main(String[] args) {
        System.out.println(new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong())).toString());


    }
}
