package com.example.qureshi.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
//    private final int request_code=20;
    public static int index;
    public static String save;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  String username = getIntent().getStringExtra("username");
       // String inReplyTo = getIntent().getStringExtra("in_reply_to");
       // int code = getIntent().getIntExtra("code", 0);
        lvItems = (ListView)findViewById(R.id.IvItems);
     //   items = new ArrayList<>();
        readItems();
        itemsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(itemsAdapter);
        lvItems.setOnItemClickListener(this);
       // lvItems.setOnClickListener((View.OnClickListener) this);
       // items.add("first item");
        //items.add("second item");
        setupListViewListener();


    }



    private void setupListViewListener(){

       /* lvItems.setOnClickListener(

                Intent i = new Intent(MainActivity.this,EditItemActivity.class);
        );*/


        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {

                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }



        });
    }

//readItems run when app starts
    private void  readItems(){

        File filesDir = getFilesDir();
        Log.d("myfile readitems"," "+filesDir);
        File todoFile = new File(filesDir,"todo.txt");

        try{

            items = new ArrayList<String>(FileUtils.readLines(todoFile));
            //items [hi, bye, hh, hh, hh, hh, hh, hh, hh, hh, hh, hh, hh, hh, hg, hgy, hjh]
            Log.d("read","items "+items);


        }catch (IOException e){

            items = new ArrayList<String>();

        }


    }

    private void writeItems(){

        /*INTERNAL FILING*/

        File filesDir = getFilesDir(); //Returns a File representing an internal directory for your app
        Log.d("myfile writeitems"," "+filesDir);
        File todoFile = new File(filesDir,"todo.txt"); //constructor calling. 1st parameter is file dir second is file name...mOReOVER ITS creating the file
        Log.d("obj bnne k bd"," "+filesDir);
        try{

//The FileUtils class contains utility methods for working with File objects. These include reading, writing, copying and comparing files.
           FileUtils.writeLines(todoFile,items);


        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void onAddItem(View view){

        String itemText;
     EditText etNewItem = (EditText)findViewById(R.id.etNewItem);
       itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
       etNewItem.setText("");
        writeItems();

    }

    private final int REQUEST_CODE = 1;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ListView lvItems=(ListView)findViewById(R.id.IvItems);
       // index = lvItems.getSelectedItemPosition();
            index = position;
        //index = lvItems.getItemAtPosition(position);
      //  Log.d("index ki pos"," "+index);
        Intent i = new Intent(this,EditItemActivity.class);
       // Log.d("items"," "+items.get(position));
      //  save = items.get(position);

        String item = items.get(position);
       i.putExtra("array",item);
        //i.putExtra(save,position);
     //  int itemID = String.valueOf(items.indexOf(item));

// for name or any informations

       // itemName = String.valueOf(catList.get(position).getItemName());
        //writeItems();
       startActivityForResult(i,REQUEST_CODE);


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE ) {
            // Extract name value from result extras
            String name = data.getExtras().getString("array");
         //   Log.d("name ye rha"," "+name);
            int code = data.getExtras().getInt("code", 0);
           // Log.d("code ye rha"," "+code);
            // Toast the name to display temporarily on screen
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
          //  writeItems();
     //   EditText editText = (EditText)findViewById(R.id.etNewItem);
       //     editText.setText(name);
            lvItems = (ListView)findViewById(R.id.IvItems);

            int i = 0;

          //  i=items.

            /*for(int l=0;l<items.size();l++){
            i=itemsAdapter.getPosition(name);
          //  Log.d("adapter"," "+i);
            i++;
                Log.d("adapter loop"," "+i);
            }
            Log.d("adapter"," "+i);
*/
          //  int index = itemsAdapter.getItemId();

            //position ka kch krna prega
         //   itemsAdapter.add(" " +i);
            //int index=   itemsAdapter.getItem();
          //  int index = items.indexOf(name);
              //  Log.d("index"," "+index);
           // }
          //  Log.d("index"," "+index);
           items.set(index,name);
            itemsAdapter.notifyDataSetChanged();
            writeItems();

        }
    }
    /*public int getItemPosition(long id)
    {
        for (int position=0; position<items.size(); position++)
            if (items.get(position).getId() == id)
                return position;
        return 0;
    }*/
   /* private int getAdapterItemPosition(long id)
    {
        for (int position=0; position<itemsAdapter.getCount(); position++)
            if (itemsAdapter.get(position).getId() == id)
                return position;
        return 0;
    }*/


}
