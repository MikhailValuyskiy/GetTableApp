package ru.example.mvaluyskiy.gettableapp.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomerDto {

    @SerializedName("id")
    private long id;

    @SerializedName("customerFirstName")
    private String firstName;

    @SerializedName("customerLastName")
    private String lastName;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
