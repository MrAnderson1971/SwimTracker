/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import java.util.stream.*;
import java.util.*;

/**
 * Provides methods for statistical calculations.
 * @author Hello there!
 */
public class Stat
{

    /**
     * Returns arithmetic mean of array of doubles.
     *
     * @param m
     * @return
     */
    public static double mean(double... m)
    {
        return DoubleStream.of(m).sum() / m.length;
    }

    /**
     * Returns max value in array of doubles.
     *
     * @param m
     * @return
     */
    public static double max(double... m)
    {
        double max = m[0];
        for (double i : m)
        {
            if (i > max)
            {
                max = i;
            }
        }
        return max;
    }

    /**
     * Returns min value in array of doubles.
     *
     * @param m
     * @return
     */
    public static double min(double... m)
    {
        double min = m[0];
        for (double i : m)
        {
            if (i < min)
            {
                min = i;
            }
        }
        return min;
    }

    /**
     * Returns range of array of doubles.
     *
     * @param m
     * @return
     */
    public static double range(double... m)
    {
        return max(m) - min(m);
    }

    /**
     * Returns sum of doubles from start to end over function.
     *
     * @param f A one-variable method that accepts double.
     * @param start
     * @param end
     * @return
     */
    public static double sigma(Lambda f, double start, double end)
    {
        if (start == end)
        {
            return f.f(start);
        }
        return f.f(end) + sigma(f, start, end - 1);
    }

    /**
     * Returns population standard deviation (sigma) from array of doubles.
     *
     * @param m
     * @return
     */
    public static double popStandardDeviation(double... m)
    {
        return Math.sqrt(
                (1.0 / m.length) * sigma((n) -> Math.pow(m[(int) n] - mean(m), 2.0), 0, m.length - 1)
        );
    }

    /**
     * Returns sample standard deviation (s) from array of doubles.
     *
     * @param m
     * @return
     */
    public static double samStandardDeviation(double... m)
    {
        return Math.sqrt(
                (1.0 / (m.length - 1)) * sigma((n) -> Math.pow(m[(int) n] - mean(m), 2.0), 0, m.length - 1)
        );
    }

    /**
     * Returns mode of array of doubles.
     *
     * @param m
     * @return Warning: may return a mode even if there is none.
     */
    public static double mode(double... m)
    {
        int max = 0;
        double maxValue = m[0];

        for (int i = 0; i < m.length; i++)
        {
            int count = 0;
            for (int j = i; j < m.length; j++)
            {
                if (m[i] == m[j])
                {
                    count++;
                }
            }
            if (count > max)
            {
                max = count;
                maxValue = m[i];
            }
        }

        return maxValue;
    }

    /**
     * Returns median of array of doubles.
     *
     * @param m
     * @return
     */
    public static double median(double... m)
    {
        Arrays.sort(m);
        if (m.length % 2 == 0)
        {
            return (m[m.length / 2] + m[m.length / 2 - 1]) / 2.0;
        }
        return m[m.length / 2];
    }
    
    /**
     * Get z score
     * 
     * @param x The target number
     * @param m An array of numbers
     * @return 
     */
    public static double zScore(double x, double... m)
    {
        return (x - mean(m)) / popStandardDeviation(m);
    }

    private interface Lambda
    {

        public double f(double n);
    }
}
