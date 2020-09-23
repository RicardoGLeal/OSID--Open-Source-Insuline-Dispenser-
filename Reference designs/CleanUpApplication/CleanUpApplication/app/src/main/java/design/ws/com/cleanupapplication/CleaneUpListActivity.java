package design.ws.com.cleanupapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.RecycleAdapter_CleanerUpList;
import ModelClass.CleanerUpModelClass;

public class CleaneUpListActivity extends AppCompatActivity {

    private String Name[] = {"1. Immediate Use Activity","2. CleanUp Activity"};

    private ArrayList<CleanerUpModelClass> cleanerUpModelClasses;

    private RecyclerView recyclerView;
    private RecycleAdapter_CleanerUpList mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleane_up_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        cleanerUpModelClasses = new ArrayList<>();



        for (int i = 0; i < Name.length; i++) {
            CleanerUpModelClass beanClassForRecyclerView_contacts = new CleanerUpModelClass(Name[i]);

            cleanerUpModelClasses.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_CleanerUpList(CleaneUpListActivity.this,cleanerUpModelClasses);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CleaneUpListActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}
