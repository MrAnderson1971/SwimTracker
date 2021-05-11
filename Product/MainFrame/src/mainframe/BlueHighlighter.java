/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hello there!
 */
public class BlueHighlighter implements IDateEvaluator
{

    private final List<java.util.Date> list = new ArrayList<>();

    public List<Date> getList()
    {
        return list;
    }

    public void add(java.util.Date date)
    {
        list.add(date);
    }

    @Override
    public boolean isSpecial(java.util.Date date)
    {
        return list.contains(date);
    }

    @Override
    public Color getSpecialForegroundColor()
    {
        return Color.red.darker();
    }

    @Override
    public Color getSpecialBackroundColor()
    {
        return Color.blue;
    }

    @Override
    public String getSpecialTooltip()
    {
        return "Highlighted event.";
    }

    @Override
    public boolean isInvalid(java.util.Date date)
    {
        return false;
    }

    @Override
    public Color getInvalidForegroundColor()
    {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor()
    {
        return null;
    }

    @Override
    public String getInvalidTooltip()
    {
        return null;
    }
}
