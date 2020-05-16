package com.example.dynamicmenunavdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyVH> {
    private List<String> data;
    private LayoutInflater inflater;
    private ClickListener listener;

    public RecyclerAdapter(Context context, ClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView textView;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.click(getAdapterPosition());
                }
            });
        }

        void bind(String menuName) {
            textView.setText(menuName);
        }
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    interface ClickListener {
        void click(int position);
    }
}
