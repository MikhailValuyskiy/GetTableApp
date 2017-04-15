package ru.example.mvaluyskiy.gettableapp;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class BindingException extends Exception {

    public static String TAG = BindingException.class.getName();

    public BindingException() {
        super("The view wasn't binded correctly");
    }

    public BindingException(String reason){
        super(reason);
    }
}