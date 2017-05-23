package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    ListView lv;
    ArrayList<Note> notes;
    ArrayAdapter aa;
    EditText etTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.buttonInsertNote);
        btnShow = (Button) findViewById(R.id.buttonShowList);
        etTitle = (EditText) findViewById(R.id.editTextNote);
        lv = (ListView) findViewById(R.id.lv);





        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars);
                final int selectedButtonId = rg.getCheckedRadioButtonId();
                final RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                db.insertNote(etTitle.getText().toString(), rb.getText().length());



                Toast.makeText(getBaseContext(), "INSERTED" , Toast.LENGTH_SHORT ).show();

                db.close();

            }
        });


        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("description", etTitle.getText());
                startActivity(i);

                Toast.makeText(getBaseContext(), "SHOW LIST" , Toast.LENGTH_SHORT ).show();
            }
        });


    }
    }


