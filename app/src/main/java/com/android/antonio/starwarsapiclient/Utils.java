package com.android.antonio.starwarsapiclient;


public class Utils {
    public static int getPlanetIdFromUrl(String url){
        return Integer.parseInt(url.substring(url.lastIndexOf("planets/"),url.length()-1).replace("planets/",""));
    }
    public static int getFilmIdFromUrl(String url){
        return Integer.parseInt(url.substring(url.lastIndexOf("films/"),url.length()-1).replace("films/",""));
    }
    public static int getPeopleIdFromUrl(String url){
        return Integer.parseInt(url.substring(url.lastIndexOf("people/"),url.length()-1).replace("people/",""));
    }
}
