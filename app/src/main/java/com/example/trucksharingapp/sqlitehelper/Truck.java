package com.example.trucksharingapp.sqlitehelper;

public class Truck {
    private int id;
    String name, good, weight, height, width, length, sender, receiver, pickup, location, date;

    public Truck(String name, String good, String weight, String height, String width, String length, String sender, String receiver, String pickup, String location, String date) {
        this.name = name;
        this.good = good;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.sender = sender;
        this.receiver = receiver;
        this.pickup = pickup;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getGood() {
        return good;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getLength() {
        return length;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getPickup() {
        return pickup;
    }

    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
}
