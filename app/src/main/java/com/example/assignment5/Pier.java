package com.example.assignment5;

public class Pier {
    private String name;
    private String location;
    private int length;
    public Pier (String n, String l, int h) {
        name=n;
        location=l;
        length=h;
    }
    public String info(){
        String tmp = new String();
        tmp+=name+" is located in "+location+" and is "+length+"m long.";
        return tmp;
    }
    @Override
    public String toString(){
        return name;
    }
}
