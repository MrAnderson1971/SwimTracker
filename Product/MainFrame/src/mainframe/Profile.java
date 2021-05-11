/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainframe;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Hello there!
 */
public class Profile implements Serializable
{

    private String name;
    private int age;
    private Gender gender;
    private final String uuid;

    public Profile(String name, int age, Gender gender, String uuid)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.uuid = uuid;
    }

    public Profile(String name, int age, Gender gender)
    {
        this(name, age, gender, UUID.randomUUID().toString());
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s,%s", name, age, gender, uuid);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (! (o instanceof Profile))
        {
            return false;
        }
        Profile p = (Profile) o;
        return p.uuid.equals(this.uuid);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.age;
        hash = 29 * hash + Objects.hashCode(this.gender);
        hash = 29 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    public String getId()
    {
        return this.uuid;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public Gender getGender()
    {
        return gender;
    }

    /**
     * Returns a list of names given profile array
     *
     * @param arr array of profiles
     * @return array of names
     */
    public static String[] getNames(ArrayList<Profile> arr)
    {
        ArrayList<String> names = new ArrayList<>();
        arr.stream().forEach((p) ->
        {
            names.add(p.getName());
        });
        return names.toArray(new String[names.size()]);
    }

    public static Profile getProfileWithId(ArrayList<Profile> arr, String profId)
    {
        for (Profile p : arr)
        {
            if (p.getId().equals(profId))
            {
                return p;
            }
        }
        throw new IllegalArgumentException("Error: no Profile with matching id found.");
    }

    public static Profile getProfileWithName(ArrayList<Profile> arr, String name)
    {
        for (Profile p : arr)
        {
            if (p.getName().equals(name))
            {
                return p;
            }
        }
        throw new IllegalArgumentException("Error: no Profile with matching name found.");
    }
}
