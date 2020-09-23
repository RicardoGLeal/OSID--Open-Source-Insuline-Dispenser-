package fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import design.ws.com.cleanupapplication.R;

import static android.content.ContentValues.TAG;



public class Clean_Fragment extends Fragment {


    private View view;


    private CircleProgress circleProgress;
    private ArcProgress arcProgress;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.clean_fragment, container, false);




        return view;



    }


}
