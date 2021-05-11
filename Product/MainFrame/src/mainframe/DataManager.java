/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hello there!
 */
public class DataManager
{

    private static final String CSV_SEPARATOR = ",";

    /**
     * Stores profile data.
     *
     * @param data
     */
    public static void storeProfileData(ArrayList<Profile> data)
    {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("profile.csv"), "UTF-8")))
        {
            for (Profile p : data)
            {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(p);
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();

        } catch (Exception ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Stores numerical data associated with date.
     *
     * @param date The date.
     * @param data
     */
    public static void storeDateData(Date date, HashMap<Profile, ArrayList<Double>> data)
    {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./data/" + date.toString() + ".csv"), "UTF-8")))
        {
            if (!(new File("./data/").exists())) // create new folder \data if it does not exist.
            {
                new File("./data/").mkdirs();
            }
            for (Profile p : data.keySet())
            {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(p.getId());
                oneLine.append(CSV_SEPARATOR);
                data.get(p).stream().map((d) ->
                {
                    oneLine.append(d);
                    return d;
                }).forEach((_item) ->
                {
                    oneLine.append(CSV_SEPARATOR);
                });
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves profile data
     *
     * @return
     */
    public static ArrayList<Profile> getProfileData()
    {
        ArrayList<Profile> data = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(new File("profile.csv"));
            while (scanner.hasNextLine())
            {
                String[] line = scanner.nextLine().split(CSV_SEPARATOR);
                String name = line[0];
                int age = Integer.parseInt(line[1]);
                Gender gender = line[2].equals("MALE") ? Gender.MALE : Gender.FEMALE;
                String uuid = line[3];
                data.add(new Profile(name, age, gender, uuid));
            }
        } catch (FileNotFoundException | NumberFormatException ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Retrieves date data.
     *
     * @param date
     * @return
     */
    public static HashMap<Profile, ArrayList<Double>> getDateData(String date)
    {
        HashMap<Profile, ArrayList<Double>> data = new HashMap<>();
        try
        {
            Scanner scanner = new Scanner(new File("./data/" + date + ".csv"));
            while (scanner.hasNextLine())
            {
                Scanner line = new Scanner(scanner.nextLine());
                line.useDelimiter(CSV_SEPARATOR);
                ArrayList<Double> numbers = new ArrayList<>();
                Profile p = null;
                while (line.hasNext())
                {
                    String next = line.next();
                    System.out.println(next);
                    try
                    {
                        numbers.add(Double.parseDouble(next));
                    } catch (NumberFormatException e)
                    {
                        p = Profile.getProfileWithId(ProfileSelection.profiles, next);
                    }
                }
                data.put(p, numbers);
            }
        } catch (FileNotFoundException | NumberFormatException ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Stores reminder data.
     *
     * @param data
     */
    public static void storeReminders(HashMap<Date, String> data)
    {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("reminders.csv"), "UTF-8")))
        {
            for (Date d : data.keySet())
            {
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(d);
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(data.get(d));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();

        } catch (Exception ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves reminder data.
     *
     * @return
     */
    public static HashMap<Date, String> getReminderData()
    {
        HashMap<Date, String> data = new HashMap<>();
        try
        {
            Scanner scanner = new Scanner(new File("reminders.csv"));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                Scanner split = new Scanner(line);
                split.useDelimiter(CSV_SEPARATOR);
                String key = split.next();
                String value = split.next();
                data.put(new Date(key), value);
            }
        } catch (FileNotFoundException | NumberFormatException ex)
        {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    /**
     * Get national average times for a given gender and age
     *
     * @param gender
     * @param age
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static ArrayList<Double> getNationalStandards(Gender gender, int age) throws FileNotFoundException
    {
        ArrayList<ArrayList<Double>> data = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(new File(gender == Gender.MALE ? "boys.csv" : "girls.csv"));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                ArrayList<Double> currentLineData = new ArrayList<>();
                Scanner s = new Scanner(line);
                s.useDelimiter(CSV_SEPARATOR);
                while (s.hasNextDouble())
                {
                    currentLineData.add(s.nextDouble());
                }
                data.add(currentLineData);
            }
        } catch (FileNotFoundException | NumberFormatException ex)
        {
            // create the data files if it is missing or corrupted
            // we are using data files because it is easier for human to read from
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            try (PrintWriter out = new PrintWriter("boys.csv"))
            {
                out.println("76.49,40.99,77.39,44.89,38.19\n66.99,35.79,77.69,39.59,33.69\n61.99,134.79,69.29,67.89,66.79\n59.39,144.69,66.09,73.79,63.59\n58.39,140.49,64.89,72.69,62.49\n");
            }
            try (PrintWriter out = new PrintWriter("girls.csv"))
            {
                out.println("76.99,40.89,88.29,45.29,38.89\n69.39,36.39,79.39,40.19,33.89\n66.69,158.69,73.89,83.99,71.69\n65.09,155.29,72.49,81.79,70.29\n64.59,154.49,71.89,81.79,69.49\n");
            }
            return getNationalStandards(gender, age); // once the files are generated, run this method again.
        }

        // return the correct data set given age
        if (age <= 10)
        {
            return data.get(0);
        }
        if (age == 11 || age == 12)
        {
            return data.get(1);
        }
        if (age == 13 || age == 14)
        {
            return data.get(2);
        }
        if (age == 15 || age == 16)
        {
            return data.get(3);
        } else
        {
            return data.get(4);
        }
    }
}
