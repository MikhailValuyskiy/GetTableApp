package ru.example.mvaluyskiy.gettableapp.presentation.tables;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.example.mvaluyskiy.gettableapp.R;
import ru.example.mvaluyskiy.gettableapp.data.vo.Table;

/**
 * Created by m.valuyskiy on 16.04.17.
 */

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private TableClickListener listener;
    private List<Table> tables = new ArrayList<>();

    public TablesAdapter(TableClickListener listener) {
        this.listener = listener;
    }

    @Override
    public TablesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_table, viewGroup, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.table_view)
        ImageView tableView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tableView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Table selectedTable = tables.get(getAdapterPosition());
                    selectedTable.setFree(!selectedTable.isFree());
                    listener.onTableClicked(selectedTable);
                    tableView.setSelected(!selectedTable.isFree());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(TablesAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tableView.setSelected(!tables.get(i).isFree());
    }


    public void setTables(List<Table> tableList) {
        this.tables.clear();
        this.tables.addAll(tableList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public interface TableClickListener {
        void onTableClicked(Table table);
    }
}

