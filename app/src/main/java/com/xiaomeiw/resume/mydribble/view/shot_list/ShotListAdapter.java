package com.xiaomeiw.resume.mydribble.view.shot_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomeiw.resume.mydribble.R;
import com.xiaomeiw.resume.mydribble.model.Shot;

import java.util.List;

/**
 * Created by wenxiaomei on 17/5/20.
 */

class ShotListAdapter extends RecyclerView.Adapter {
    private List<Shot> data;

    public ShotListAdapter(@NonNull List<Shot> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shot, parent, false);
        return new ShotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Shot shot = data.get(position);

        //must be string!!! or will be error
        ShotViewHolder shotViewHolder = (ShotViewHolder) holder;
        shotViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
        shotViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
        shotViewHolder.viewCount.setText(String.valueOf(shot.views_count));
        shotViewHolder.image.setImageResource(R.drawable.shot_placeholder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
