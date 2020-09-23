package ws.wolfsoft.online_course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.CourseListRecycleAdapter;
import ModelClass.CourseListModelClass;

public class CourseListActivity extends AppCompatActivity {


    private ArrayList<CourseListModelClass> courseListModelClasses;
    private RecyclerView recyclerView;
    private CourseListRecycleAdapter bAdapter;

    private  String title[] = {"Homepage","CourseList","DetailPage","CoursePage"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);


        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CourseListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        courseListModelClasses = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            CourseListModelClass beanClassForRecyclerView_contacts = new CourseListModelClass(title[i]);
            courseListModelClasses.add(beanClassForRecyclerView_contacts);
        }
        bAdapter = new CourseListRecycleAdapter(CourseListActivity.this,courseListModelClasses);
        recyclerView.setAdapter(bAdapter);
    }
}
