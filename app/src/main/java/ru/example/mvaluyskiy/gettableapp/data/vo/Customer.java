package ru.example.mvaluyskiy.gettableapp.data.vo;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class Customer {

    private long id;
    private String firstName;
    private String lastName;

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
