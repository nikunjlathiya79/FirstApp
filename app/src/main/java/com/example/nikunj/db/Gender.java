package com.example.nikunj.db;

/**
 * Created by Nikunj on 29-01-2018.
 */
public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        switch(this) {
            case MALE: return "male";
            case FEMALE: return "female";
            default: throw new IllegalArgumentException();
        }
    }
    public static Gender getGender(String gender)
    {
        if(gender.equalsIgnoreCase("male"))
        {
            return MALE;
        }
        if(gender.equalsIgnoreCase("female"))
        {
            return FEMALE;
        }
        return null;
    }
}
