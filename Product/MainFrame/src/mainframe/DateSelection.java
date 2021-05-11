/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import com.toedter.calendar.JCalendar;
import java.awt.EventQueue;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hello there!
 */
public class DateSelection extends javax.swing.JFrame
{

    public static java.util.Date selectedDate;
    public static Date date;
    private static ArrayList<Date> savedDates;
    private static HashMap<Date, String> reminderDates;
    public static Calendar today;

    /**
     * Creates new form DateSelection
     */
    public DateSelection()
    {
        today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        savedDates = new ArrayList<>();
        if (!(new File("./data/").exists())) // create new folder \data if it does not exist.
        {
            new File("./data/").mkdirs();
        }
        File folder = new File("./data");
        File[] listOfFiles = folder.listFiles();
        for (File f : listOfFiles) // get all dates saved
        {
            try
            {
                String fileName = f.getName().replaceAll(".csv", "");
                if (DataManager.getDateData(fileName).containsKey(ProfileSelection.selectedProfile)) // only add date if profile has stored data in it
                {
                    savedDates.add(new Date(fileName));
                }
            } catch (Exception ex)
            {
                Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reminderDates = DataManager.getReminderData();
        System.out.println(savedDates);

        // go through all saved reminders; if any of them are in the past show them, then delete them
        ArrayList<Date> passedReminders = new ArrayList<>();
        reminderDates.keySet().stream().filter((d) -> (!d.isInFuture(today))).map((d) ->
        {
            ProfileSelection.showWarningDialog("Passed reminder(s): " + reminderDates.get(d));
            return d;
        }).map((d) ->
        {
            //reminderDates.remove(d);
            return d;
        }).map((d) ->
        {
            passedReminders.add(d);
            return d;
        }).forEach((_item) ->
        {
            DataManager.storeReminders(reminderDates);
        });

        passedReminders.stream().forEach((d) ->
        {
            reminderDates.remove(d);
        });
        DataManager.storeReminders(reminderDates);

        /*for (Date d : reminderDates.keySet()) 
         {
         if (!d.isInFuture(today))
         {
         ProfileSelection.showWarningDialog("Today's reminder:" + reminderDates.get(new Date(today)));
         reminderDates.remove(d);
         passedReminders.add(d);
         DataManager.storeReminders(reminderDates);
         }
         }*/
        /*if (reminderDates.containsKey(new Date(today))) // remove reminder if date is arrived
         {
         ProfileSelection.showWarningDialog("Today's reminder:" + reminderDates.get(new Date(today)));
         reminderDates.remove(new Date(today));
         DataManager.storeReminders(reminderDates);
         }*/
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jButtonEdit = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonViewStats = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel("<html><span style='font-size:7px'>If you select a date in the future,<br>you will be prompted to add a reminder.</span></html>");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setResizable(false);
        setTitle("Date Selection");

        BlueHighlighter evaluator = new BlueHighlighter();
        for (Date d : savedDates)
        {
            evaluator.add(createDate(d));
        }

        GreenHighlighter evaluator2 = new GreenHighlighter();
        for (Date d : reminderDates.keySet())
        {
            evaluator2.add(createDate(d));
        }

        JCalendar jc = jDateChooser1.getJCalendar();
        jc.getDayChooser().addDateEvaluator(evaluator);
        jc.getDayChooser().addDateEvaluator(evaluator2);
        jc.setCalendar(jc.getCalendar());

        jLabel1.setText("Select Date:");

        jButtonEdit.setText("Input-->");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonBack.setText("<-- Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonViewStats.setText("View Stats-->");
        jButtonViewStats.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonViewStatsActionPerformed(evt);
            }
        });

        /*
        jLabel2.setText("jLabel2");
        */

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonViewStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButtonEdit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonViewStats)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditActionPerformed
    {//GEN-HEADEREND:event_jButtonEditActionPerformed
        selectedDate = jDateChooser1.getDate();
        if (selectedDate == null)
        {
            ProfileSelection.showWarningDialog("No date selected.");
            return;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(selectedDate);
        date = new Date(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));

        // if user selects date in the future, prompt user to add reminder to date, which highlights date in green.
        if (date.isInFuture(today) && date.getYear() >= 1000 && date.getYear() <= 9999)
        {
            if (!reminderDates.containsKey(date)) // prompt to add reminder if reminder doesn't exist
            {
                if (ProfileSelection.showQuestionDialog("Add Reminder?"))
                {
                    String msg = showInputDialog("Add reminder text");
                    msg = msg.length() > 0 ? msg : "No text"; // can't store reminder without message
                    reminderDates.put(date, msg);
                    DataManager.storeReminders(reminderDates);

                    EventQueue.invokeLater(() -> new DateSelection().setVisible(true));
                    this.dispose();
                }
            } else // prompt to remove reminder otherwise
            {
                if (ProfileSelection.showQuestionDialog("Message: " + reminderDates.get(date) + "\nDelete reminder?"))
                {
                    reminderDates.remove(date);
                    DataManager.storeReminders(reminderDates);

                    EventQueue.invokeLater(() -> new DateSelection().setVisible(true));
                    this.dispose();
                }
            }
            return;
        }

        java.awt.EventQueue.invokeLater(() -> new DateEditor().setVisible(true));
        this.dispose();

    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBackActionPerformed
    {//GEN-HEADEREND:event_jButtonBackActionPerformed
        java.awt.EventQueue.invokeLater(() -> new ProfileSelection().setVisible(true));
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonViewStatsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonViewStatsActionPerformed
    {//GEN-HEADEREND:event_jButtonViewStatsActionPerformed
        java.awt.EventQueue.invokeLater(() -> new StatsViewer().setVisible(true));
        this.dispose();
    }//GEN-LAST:event_jButtonViewStatsActionPerformed

    public static java.util.Date createDate(Date d)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, d.getDay());
        c.set(Calendar.MONTH, d.getMonth() - 1);
        c.set(Calendar.YEAR, d.getYear());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTime());
    }

    public static String showInputDialog(String msg)
    {
        return JOptionPane.showInputDialog(msg);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonViewStats;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
