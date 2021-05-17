package com.example.assignment5;

public class Pier {
    private String name;
    private String location;
    private int length;
    private int hej;



    public Pier (String n, String l, int h, int j) {
        name=n;
        location=l;
        length=h;
        hej=j;
    }
    public String info(){
        String tmp = new String();
        tmp+=name+" is located in "+location+" and is "+length+"m long. Byggdes "+hej+".";
        return tmp;
    }


    @Override
    public String toString(){
        return name;
    }
}
