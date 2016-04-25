package com.example.glmgracy.helloworld;

import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 桂新 on 2016/4/18.
 */
public class Sandbox extends RectShape {
    public static final String NAME_KEY = "name_key";
    private List<String> mGreetings = new ArrayList<String>();
    private Date mDate = new Date();
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String greeting : mGreetings){
            stringBuilder.append(greeting + "");
        }
        return stringBuilder.toString().trim();
    }

    public Sandbox() {
    }

    @Override
    public boolean hasAlpha() {
        System.out.println("Sandbox.hasAlpha");
        if(1==1)
        {
            System.out.println("Sandbox.hasAlpha");
        }else{
            System.out.println("mGreetings = " + mGreetings);
        }
        return super.hasAlpha();
    }

    private Date getDate(){
        return mDate;
    }
//    private void setDate(){
//        mDate = new Date();
//    }
    private void setDate(Date date){
        mDate = date;
    }

    private String saySomething(){
        return  "Something";
    }

    private void addName(String name, Bundle bundle){
        bundle.putString(NAME_KEY, name);
    }
    protected String greeting(String message, String name){
        return "Hello " + message;
    }

    public Sandbox(List<String> mGreetings) {
        this.mGreetings = mGreetings;
    }

    public List<String> getGreetings() {
        return mGreetings;
    }

    public void setGreetings(List<String> greetings) {
        mGreetings = greetings;
    }

    public boolean add(String object) {
        if (!mGreetings.contains(object)) {
            return mGreetings.add(object);
        } else {
            return false;
        }
    }

    private String methodHello(){
        String greet = "Hello";
        return getGreet(greet);
    }

    @NonNull
    private String getGreet(String greet) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int nC = 0; nC < 10; nC++){
            stringBuilder.append(greet + nC);
        }
        return stringBuilder.toString();
    }

    private String methodGoodbye(){
        String greet = "Goodbye";
        return getGreet(greet);
    }
}
