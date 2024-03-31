package com.example.assignment2;
public class Restautant {
    String name;
    String location;
    String phone;
    String description;

    @Override
    public String toString() {
        return "Restautant{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public Restautant() {
    }

    public Restautant(String name, String location, String phone, String description) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
    }
}
