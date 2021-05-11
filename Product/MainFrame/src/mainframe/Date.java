/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import java.util.Calendar;

/**
 *
 * @author Hello there!
 */
public class Date
{

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String s)
    {
        String[] st = s.split(" ");
        this.day = Integer.parseInt(st[0]);
        this.month = Integer.parseInt(st[1]);
        this.year = Integer.parseInt(st[2]);
    }

    public Date(Calendar c)
    {
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.month = c.get(Calendar.MONTH) + 1;
        this.year = c.get(Calendar.YEAR);
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s", this.day, this.month, this.year);
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Date))
        {
            return false;
        }
        Date d = (Date) o;
        return this.day == d.day && this.month == d.month && this.year == d.year;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 31 * hash + this.day;
        hash = 31 * hash + this.month;
        hash = 31 * hash + this.year;
        return hash;
    }

    public boolean isInFuture(Date d)
    {
        if (d.year > this.year)
        {
            return true;
        }
        if (d.month > this.month)
        {
            return true;
        }
        return d.day > this.day;
    }

    /**
     * True of calendar c is in the future than this
     * @param c
     * @return 
     */
    public boolean isInFuture(Calendar c)
    {
        String sday = this.day <= 9 ? "0" + this.day : "" + this.day;
        String smonth = this.month <= 9 ? "0" + this.month : "" + this.month;
        int thisday = Integer.parseInt("" + this.year + smonth + sday);
        sday = c.get(Calendar.DAY_OF_MONTH) <= 9 ? "0" + c.get(Calendar.DAY_OF_MONTH) : "" + c.get(Calendar.DAY_OF_MONTH);
        smonth = c.get(Calendar.MONTH) + 1 <= 9 ? "0" + (c.get(Calendar.MONTH) + 1) : "" + (c.get(Calendar.MONTH) + 1);
        int calendarday = Integer.parseInt("" + c.get(Calendar.YEAR) + smonth + sday);
        return calendarday < thisday;
    }
    
    public void setDay(int day)
    {
        this.day = day;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

}
