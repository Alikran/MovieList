package com.example.jpere32_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>  myList;
    ArrayList<Integer> myImages;
    ArrayList<String>  myDirect;
    Context myContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerView nameView = (RecyclerView) findViewById(R.id.recyler_view);

        List<String> names = Arrays.asList("Boyhood", "Django Unchained", "Fast & the Furious", "Gran Torino", "Logan", "Moonlight", "Spider-man into the Spider-verse", "Whiplash", "Under the same Moon", "Marriage Story");
        myList = new ArrayList<>();
        myList.addAll(names);

        List<Integer> moviePics = Arrays.asList(R.drawable.boyhood,R.drawable.djangounchained,R.drawable.fastandfury,R.drawable.grantorino,R.drawable.logan,R.drawable.moonlight,R.drawable.spiderman,R.drawable.whiplash,R.drawable.undermoon,R.drawable.marrystory);
        myImages = new ArrayList<>();
        myImages.addAll(moviePics);

        List<String> mDirect = Arrays.asList("Richard Linklater", "Quentin Tarantino", "Rob Cohen", "Clint Eastwood", "James Mangold", "Barry Jenkins", " Peter Ramsey", "Damien Chazelle", "Patricia Riggen", "Noah Baumbach");
        myDirect = new ArrayList<>();
        myDirect.addAll(mDirect);

        myContext = this;

        //Define the listener with a lambda and access the name of the list item from the view
        RVClickListener listener = (view,position)->{
            TextView name = (TextView)view.findViewById(R.id.textView);
            String movieName = name.getText().toString();
            String movieUrl = userClick(movieName);

            Uri uri = Uri.parse(movieUrl); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        };

        myAdapter adapter = new myAdapter(myList, myImages, myDirect, listener, myContext);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);

        if(item.getItemId() == R.id.listView){
            nameView.setLayoutManager(new GridLayoutManager(this, 1)); //use this line to see as a grid
        }
        else if(item.getItemId() == R.id.gridView){
            nameView.setLayoutManager(new GridLayoutManager(this, 2)); //use this line to see as a standard vertical list
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView nameView = (RecyclerView) findViewById(R.id.recyler_view);

        List<String> names = Arrays.asList("Boyhood", "Django Unchained", "Fast & the Furious", "Gran Torino", "Logan", "Moonlight", "Spider-man into the Spider-verse", "Whiplash", "Under the same Moon", "Marriage Story");
        myList = new ArrayList<>();
        myList.addAll(names);

        List<Integer> moviePics = Arrays.asList(R.drawable.boyhood,R.drawable.djangounchained,R.drawable.fastandfury,R.drawable.grantorino,R.drawable.logan,R.drawable.moonlight,R.drawable.spiderman,R.drawable.whiplash,R.drawable.undermoon,R.drawable.marrystory);
        myImages = new ArrayList<>();
        myImages.addAll(moviePics);

        List<String> mDirect = Arrays.asList("Richard Linklater", "Quentin Tarantino", "Rob Cohen", "Clint Eastwood", "James Mangold", "Barry Jenkins", " Peter Ramsey", "Damien Chazelle", "Patricia Riggen", "Noah Baumbach");
        myDirect = new ArrayList<>();
        myDirect.addAll(mDirect);

        myContext = this;

        //Define the listener with a lambda and access the list of names with the position passed in
        //  RVClickListener listener = (view, position)-> Toast.makeText(this, "position: "+position, Toast.LENGTH_LONG).show();

        //Define the listener with a lambda and access the name of the list item from the view
        RVClickListener listener = (view,position)->{
            TextView name = (TextView)view.findViewById(R.id.textView);
            String movieName = name.getText().toString();
            String movieUrl = userClick(movieName);

            Uri uri = Uri.parse(movieUrl); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        };

        myAdapter adapter = new myAdapter(myList, myImages, myDirect, listener, myContext);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);
        nameView.setLayoutManager(new GridLayoutManager(this, 2)); //use this line to see as a grid
    }

    //-------------------------------------------------------------------------------------------------------------
    public String userClick(String movieName){
        switch(movieName){
            case "Boyhood":
                return "https://www.imdb.com/title/tt1065073/";
            case "Django Unchained":
                return "https://www.imdb.com/title/tt1853728/";
            case "Fast & the Furious":
                return "https://www.imdb.com/title/tt0232500/";
            case "Gran Torino":
                return "https://www.imdb.com/title/tt1205489/";
            case "Logan":
                return "https://www.imdb.com/title/tt3315342/";
            case "Moonlight":
                return "https://www.imdb.com/title/tt4975722/";
            case "Spider-man into the Spider-verse":
                return "https://www.imdb.com/title/tt4633694/";
            case "Whiplash":
                return "https://www.imdb.com/title/tt2582802/";
            case "Under the same Moon":
                return "https://www.imdb.com/title/tt0796307/";
            case "Marriage Story":
                return "https://www.imdb.com/title/tt7653254/";
            default:
                return "";
        }
    }

}