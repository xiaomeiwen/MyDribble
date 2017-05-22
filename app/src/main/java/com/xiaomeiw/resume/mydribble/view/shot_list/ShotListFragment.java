package com.xiaomeiw.resume.mydribble.view.shot_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomeiw.resume.mydribble.R;
import com.xiaomeiw.resume.mydribble.model.Shot;
import com.xiaomeiw.resume.mydribble.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wenxiaomei on 17/5/20.
 */

public class ShotListFragment extends Fragment {

    @BindView(R.id.fragment_recycle_view) RecyclerView recyclerView;
    boolean hideToolBar = false;

    public static ShotListFragment newInstance() {
        return new ShotListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources()
                .getDimensionPixelSize(R.dimen.spacing_medium)));
        recyclerView.setAdapter(new ShotListAdapter(fakeData()));
    }

    private List<Shot> fakeData() {
        List<Shot> fakeList = new ArrayList<Shot>();
        Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            Shot shot = new Shot();
            shot.views_count = random.nextInt(10000);
            shot.likes_count = random.nextInt(200);
            shot.buckets_count = random.nextInt(50);
            fakeList.add(shot);
        }
        return fakeList;
    }
}
