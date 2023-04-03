package com.example.jpere32_project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private ArrayList<String>  nameList;   //data: the names displayed
    private ArrayList<Integer> posterList; //data: the images displayed
    private ArrayList<String>  directList; //data: the directors displayed
    private RVClickListener RVlistener;    //listener defined in main activity
    private Context context;

    String movieUrl;

    /* passing in the data and the listener defined in the main activity */
    public myAdapter(ArrayList<String> theList, ArrayList<Integer> thePoster, ArrayList<String> theDirect, RVClickListener listener, Context conText){
        nameList = theList;
        posterList = thePoster;
        directList = theDirect;
        context = conText;
        this.RVlistener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView = inflater.inflate(R.layout.rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listView, RVlistener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(nameList.get(position));
        holder.image.setImageResource(posterList.get(position));
        holder.director.setText(directList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
    /*
        This class creates a wrapper object around a view that contains the layout for
         an individual item in the list. It also implements the onClickListener so each ViewHolder in the list is clickable.
        It's onclick method will call the onClick method of the RVClickListener defined in
        the main activity.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

        public TextView name;
        public ImageView image;
        public TextView director;
        private RVClickListener listener;
        private View itemView;


        public ViewHolder(@NonNull View itemView, RVClickListener passedListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            director = (TextView) itemView.findViewById(R.id.textView3);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item (long click)
            this.listener = passedListener;

            /* don't forget to set the listener defined here to the view (list item) that was passed in to the constructor. */
            itemView.setOnClickListener(this); //set short click listener
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
            Log.i("ON_CLICK", "in the onclick in view holder");
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //inflate menu from xml
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu );
            menu.getItem(0).setOnMenuItemClickListener(onMenu);
            menu.getItem(1).setOnMenuItemClickListener(onMenu);
            menu.getItem(2).setOnMenuItemClickListener(onMenu);
        }

        /* listener for menu items clicked */
        private final MenuItem.OnMenuItemClickListener onMenu = new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                String movieName = name.getText().toString();
                String movieID = String.valueOf(item.getItemId());
                switch (movieID){
                    //2131231101 imdb
                    case "2131231101":
                        movieUrl = getIMDB(movieName);
                        break;
                    //2131231103 movieWiki
                    case "2131231103":
                        movieUrl = getMovieWiki(movieName);
                        break;
                    //2131231102 directWiki
                    case "2131231102":
                        movieUrl = getDirectWiki(movieName);
                        break;
                    default:
                        Log.i("Default","Error");
                }

                Uri uri = Uri.parse(movieUrl); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
                return true;
            }
        };

        //---------------------------------------------------------------------------------------------------------
        public String getIMDB(String movieName){
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

        //---------------------------------------------------------------------------------------------------------
        public String getMovieWiki(String movieName){
            switch(movieName){
                case "Boyhood":
                    return "https://en.wikipedia.org/wiki/Boyhood_(2014_film)";
                case "Django Unchained":
                    return "https://en.wikipedia.org/wiki/Django_Unchained";
                case "Fast & the Furious":
                    return "https://en.wikipedia.org/wiki/The_Fast_and_the_Furious_(2001_film)";
                case "Gran Torino":
                    return "https://en.wikipedia.org/wiki/Gran_Torino";
                case "Logan":
                    return "https://en.wikipedia.org/wiki/Logan_(film)";
                case "Moonlight":
                    return "https://en.wikipedia.org/wiki/Moonlight_(2016_film)";
                case "Spider-man into the Spider-verse":
                    return "https://en.wikipedia.org/wiki/Spider-Man:_Into_the_Spider-Verse";
                case "Whiplash":
                    return "https://en.wikipedia.org/wiki/Whiplash_(2014_film)";
                case "Under the same Moon":
                    return "https://en.wikipedia.org/wiki/Under_the_Same_Moon";
                case "Marriage Story":
                    return "https://en.wikipedia.org/wiki/Marriage_Story";
                default:
                    return "";
            }
        }

        //-----------------------------------------------------------------------------------------------------------
        public String getDirectWiki(String movieName){
            switch(movieName){
                case "Boyhood":
                    return "https://en.wikipedia.org/wiki/Richard_Linklater";
                case "Django Unchained":
                    return "https://en.wikipedia.org/wiki/Quentin_Tarantino";
                case "Fast & the Furious":
                    return "https://en.wikipedia.org/wiki/Rob_Cohen";
                case "Gran Torino":
                    return "https://en.wikipedia.org/wiki/Clint_Eastwood";
                case "Logan":
                    return "https://en.wikipedia.org/wiki/James_Mangold";
                case "Moonlight":
                    return "https://en.wikipedia.org/wiki/Barry_Jenkins";
                case "Spider-man into the Spider-verse":
                    return "https://en.wikipedia.org/wiki/Peter_Ramsey";
                case "Whiplash":
                    return "https://en.wikipedia.org/wiki/Damien_Chazelle";
                case "Under the same Moon":
                    return "https://en.wikipedia.org/wiki/Patricia_Riggen";
                case "Marriage Story":
                    return "https://en.wikipedia.org/wiki/Noah_Baumbach";
                default:
                    return "";
            }
        }

    }
    //----------------------------------------------------------------------------------------------------------
}
