package com.example.hp.recycleviewexercise.custom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.recycleviewexercise.model.Person;
import com.example.hp.recycleviewexercise.R;

import java.util.List;

/**
 * Created by hp on 10/18/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> persons;
    private OnPersonClickListener onPersonClickListener;
    public PersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(viewType, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, int position) {
        Person person=persons.get(position);
        holder.tvName.setText(person.getmName());
        holder.tvNumber.setText(person.getmNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPersonClickListener.clicked(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_person;
    }

    @Override
    public int getItemCount() {
        return null!=persons ? persons.size() : 0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvNumber;

        public PersonViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }


    public void setOnPersonClickListener(OnPersonClickListener listener) {
        onPersonClickListener = listener;
    }

    public interface OnPersonClickListener {
        void clicked(int position);
    }
}
