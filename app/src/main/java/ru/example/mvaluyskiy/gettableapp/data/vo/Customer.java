package ru.example.mvaluyskiy.gettableapp.data.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by m.valuyskiy on 15.04.17.
 */


@DatabaseTable(tableName = "customer")
public class Customer {

    @DatabaseField(id = true)
    private long id;
    @DatabaseField(columnName = "first_name")
    private String firstName;
    @DatabaseField(columnName = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {
        private Customer instance;

        public Builder() {
            this.instance = new Customer();
        }

        public Builder setId(long id) {
            this.instance.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            instance.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            instance.lastName = lastName;
            return this;
        }

        public Customer build() {
            return instance;
        }

    }
}
