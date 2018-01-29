package com.example.nikunj.db;

/**
 * Created by Nikunj on 29-01-2018.
 */
public enum UserType {
    ADMIN, USER;

    public static UserType getUserType(String userType)
    {
        if(userType.equalsIgnoreCase("user"))
        {
            return USER;
        }
        if(userType.equalsIgnoreCase("admin"))
        {
            return ADMIN;
        }
        return null;
    }
}
