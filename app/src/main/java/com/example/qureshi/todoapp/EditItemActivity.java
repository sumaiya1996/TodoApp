package com.example.qureshi.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        editText = (EditText)findViewById(R.id.editText);
        Intent i =getIntent();
      String s =  i.getStringExtra("array");
        //int length=.length();
      //  putCursorAfterLastSymbol(editText);
        editText.setText(s);
        putCursorAfterLastSymbol(editText);
      //  s.setSelection(editText.getSelectionStart(),editText.getSelectionEnd());




    }
    public static void putCursorAfterLastSymbol(EditText editText) {
        editText.setSelection(editText.getText().length());
    }
    public void  Submit(View view){

        //ArrayList<String> items ;

        editText = (EditText)findViewById(R.id.editText);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("array", editText.getText().toString());
      //  data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        this.finish();

    }
}
