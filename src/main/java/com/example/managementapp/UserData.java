package com.example.managementapp;

public class UserData {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String nid;
    private String nationality;
    private String occupation;
    private String creditor;
    private double balance;

    public UserData(String username, String firstName, String lastName, String email, String address,
                    String nid, String nationality, String occupation, String creditor, double balance) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.nid = nid;
        this.nationality = nationality;
        this.occupation = occupation;
        this.creditor = creditor;
        this.balance = balance;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getNid() { return nid; }
    public String getNationality() { return nationality; }
    public String getOccupation() { return occupation; }
    public String getCreditor() { return creditor; }
    public double getBalance() { return balance; }
}

