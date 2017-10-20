package com.example.hp.recycleviewexercise.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hp.recycleviewexercise.R;
import com.example.hp.recycleviewexercise.custom.PersonAdapter;
import com.example.hp.recycleviewexercise.database.DatabaseImpl;
import com.example.hp.recycleviewexercise.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PersonAdapter.OnPersonClickListener {
    private Toolbar toolbar;
    private RecyclerView rvPerson;
    private List<Person> persons = new ArrayList<>();
    private Dialog dialog;
    private FloatingActionButton actionButton;
    private EditText etName;
    private EditText etNumber;
    private Button btnDelete;
    private Button btnSave;
    private TextView tvSwitch;
    private ImageButton ibSwitch;
    private PersonAdapter adapter;
    private DatabaseImpl database;
    private Boolean check = false;
    private Boolean check_state = true;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDialog();
        initView();
        initData();
    }

    private void initPerson() {
        persons.clear();
        persons.addAll(database.getAllData());
    }

    private void initDialog() {

        dialog = new Dialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_add, null);
        etName = view.findViewById(R.id.et_name);
        etNumber = view.findViewById(R.id.et_number);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnSave = view.findViewById(R.id.btn_save);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        btnDelete.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        rvPerson = (RecyclerView) findViewById(R.id.rv_person);
        actionButton = (FloatingActionButton) findViewById(R.id.fac_add);
        tvSwitch = (TextView) findViewById(R.id.tv_switch);
        ibSwitch = (ImageButton) findViewById(R.id.ib_switch);
        database=new DatabaseImpl(this);

    }

    private void initData() {
        ibSwitch.setImageResource(R.drawable.ic_listview);
        tvSwitch.setText(R.string.list_view);

        actionButton.setOnClickListener(this);
        ibSwitch.setOnClickListener(this);
        adapter = new PersonAdapter(persons);
        adapter.setOnPersonClickListener(this);

        persons.addAll(database.getAllData());
        rvPerson.setAdapter(adapter);
        rvPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_add:
                etName.setText("");
                etNumber.setText("");
                check = false;
                dialog.show();
                break;
            case R.id.btn_delete:
                if (check) {
                    database.delete(persons.get(position));
                }
                initPerson();
                dialog.dismiss();
                adapter.notifyDataSetChanged();
                break;

            case R.id.btn_save:
                String id= UUID.randomUUID().toString();
                String name = etName.getText().toString();
                String number = etNumber.getText().toString();
                Person person=new Person(id, name, number);
                if (!check) {
                    database.save(person);
                } else {
                    Person person1=new Person(persons.get(position).getmId(), name, number);
                    database.update(person1);
                }
                initPerson();
                adapter.notifyDataSetChanged();
                setCheckState();
                dialog.dismiss();
                break;
            case R.id.ib_switch:
                check_state=!check_state;
                setCheckState();
                break;
            default:
                break;
        }
    }


    @Override
    public void clicked(int position) {
        etName.setText(persons.get(position).getmName());
        etNumber.setText(persons.get(position).getmNumber());
        dialog.show();
        check = true;
        this.position = position;
    }

    private void setCheckState() {
        if (check_state) {
            ibSwitch.setImageResource(R.drawable.ic_listview);
            tvSwitch.setText(R.string.list_view);
            rvPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }else{
            ibSwitch.setImageResource(R.drawable.ic_girdview);
            tvSwitch.setText(R.string.gird_view);
            rvPerson.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
         }
    }
}
