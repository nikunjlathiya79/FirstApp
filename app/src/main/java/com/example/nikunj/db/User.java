package com.example.nikunj.db;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Nikunj on 29-01-2018.
 */
public class User {

    private String firstName, lastName, userName, emailId, password;
    private UserType userType;
    private Gender gender;
    private ArrayList<String> hobbies = new ArrayList<>();

    public User(String firstName, String lastName, String emailId,String userName, String password, Gender gender,UserType userType, ArrayList<String> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.userType = userType;
        this.gender = gender;
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public Gender getGender() {
        return gender;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }
}
