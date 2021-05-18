package com.example.assignment5;

public class Pier {
    private String licence;
    private String img;
    private String name;
    private String location;
    private int length;
    private int cost;



    public Pier (String n, String l, int h, int c, String p, String i) {

        licence=p;
        img=i;
        name=n;
        location=l;
        length=h;
        cost=c;
    }
    public String info(){
        String tmp = new String();
        tmp+=name+" is located in "+location+" and is "+length+"m long. Byggdes "+cost+".";
        return tmp;
    }


    @Override
    public String toString(){
        return name;
    }
}
