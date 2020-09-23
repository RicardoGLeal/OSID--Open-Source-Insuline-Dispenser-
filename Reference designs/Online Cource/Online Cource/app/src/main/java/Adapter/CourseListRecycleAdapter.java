package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ModelClass.CourseListModelClass;

import ws.wolfsoft.online_course.CourceListActivity;
import ws.wolfsoft.online_course.CourseListActivity;
import ws.wolfsoft.online_course.CoursePageAactivity;
import ws.wolfsoft.online_course.DetailPageActivity;
import ws.wolfsoft.online_course.HomepageActivity;
import ws.wolfsoft.online_course.R;


public class CourseListRecycleAdapter extends RecyclerView.Adapter<CourseListRecycleAdapter.MyViewHolder> {

    Context context;


    private List<CourseListModelClass> OfferList;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);


        }

    }


    public CourseListRecycleAdapter(Context context, List<CourseListModelClass> offerList) {
        this.OfferList = offerList;
        this.context = context;
    }

    @Override
    public CourseListRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_list, parent, false);


        return new CourseListRecycleAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        CourseListModelClass lists = OfferList.get(position);
        holder.title.setText(lists.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0) {
                    Intent i = new Intent(context, HomepageActivity.class);
                    context.startActivity(i);
                }else if(position == 1) {
                    Intent i = new Intent(context, CourceListActivity.class);
                    context.startActivity(i);
                }else if(position == 2) {
                    Intent i = new Intent(context, DetailPageActivity.class);
                    context.startActivity(i);
                }else if(position == 3) {
                    Intent i = new Intent(context, CoursePageAactivity.class);
                    context.startActivity(i);
                }

            }

        });


    }


    @Override
    public int getItemCount() {
        return OfferList.size();

    }

}


