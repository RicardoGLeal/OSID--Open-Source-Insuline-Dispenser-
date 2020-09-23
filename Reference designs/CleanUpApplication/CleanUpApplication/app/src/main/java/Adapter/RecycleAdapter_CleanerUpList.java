package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import ModelClass.CleanerUpModelClass;
import design.ws.com.cleanupapplication.ImmediateActivity;
import design.ws.com.cleanupapplication.MainActivity;
import design.ws.com.cleanupapplication.R;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_CleanerUpList extends RecyclerView.Adapter<RecycleAdapter_CleanerUpList.MyViewHolder> {
Context context;

    boolean showingFirst = true;
    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;
    private List<CleanerUpModelClass> moviesList;




    public RecycleAdapter_CleanerUpList(Context mainActivityContacts, List<CleanerUpModelClass> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview, parent, false);



        return new MyViewHolder(itemView);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        CleanerUpModelClass movie = moviesList.get(position);
        holder.name.setText(movie.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0)
                {
                    Intent i = new Intent(context, ImmediateActivity.class);
                    context.startActivity(i);
                }
                else if(position == 1){

                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView name;


        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.text_title);

        }

    }






}


