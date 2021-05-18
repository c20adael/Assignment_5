package com.example.assignment5;

public class Pier {
    private String licence;
    private String img;
    private String name;
    private String location;
    private int length;
    private int year;



    public Pier (String n, String l, int h, int y, String p, String i) {

        licence=p;
        img=i;
        name=n;
        location=l;
        length=h;
        year=y;
    }
    public String info(){
        String tmp = new String();
        tmp+=name+" is located in "+location+" and is "+length+"m long. Was built "+year+".";
        return tmp;
    }
    public String imgtest (){
        String testbild = new String();
        testbild = img;
        return testbild;
    }


    @Override
    public String toString(){
        return name;
    }
}
