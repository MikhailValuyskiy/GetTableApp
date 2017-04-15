package ru.example.mvaluyskiy.gettableapp.presentation.customers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.data.vo.Customer;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder> {

    private final CustomerClickListener customerClickListener;
    private final List<Customer> customers = new ArrayList<>();

    public CustomersAdapter(@NonNull List<Customer> customers, @NonNull CustomerClickListener listener) {
        this.setCustomers(customers);
        this.customerClickListener = listener;
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.customer_container)
        RelativeLayout customerContainer;

        @BindView(R.id.first_name)
        TextView firstNameTextView;

        @BindView(R.id.last_name)
        TextView lastNameTextView;

        CustomerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            customerContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerClickListener.onCustomerClicked(customers.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        Customer customer = customers.get(position);
        if (customer != null) {
            holder.firstNameTextView.setText(customer.getFirstName());
            holder.lastNameTextView.setText(customer.getLastName());
        }
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public void setCustomers(@NonNull List<Customer> customerLists) {
        this.customers.clear();
        this.customers.addAll(customerLists);
        notifyDataSetChanged();
    }

    public interface CustomerClickListener {
        void onCustomerClicked(Customer customer);
    }
}

