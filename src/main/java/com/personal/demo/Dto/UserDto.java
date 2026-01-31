
package com.personal.demo.Dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String password; 
    private String name;
    private String email;
    private String homeAddress;
    private List<Long> destinationIds; // Simplify the relationship to just IDs

    // Default Constructor
    public UserDto() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHomeAddress() { return homeAddress; }
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }

    public List<Long> getDestinationIds() { return destinationIds; }
    public void setDestinationIds(List<Long> destinationIds) { this.destinationIds = destinationIds; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}