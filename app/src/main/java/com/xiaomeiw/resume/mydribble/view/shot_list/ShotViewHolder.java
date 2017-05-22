package com.xiaomeiw.resume.mydribble.view.shot_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomeiw.resume.mydribble.R;

import butterknife.BindView;

/**
 * Created by wenxiaomei on 17/5/20.
 */

class ShotViewHolder extends RecyclerView.ViewHolder {
    public View cover;
    public TextView likeCount;
    public TextView bucketCount;
    public TextView viewCount;
    public ImageView image;

    public ShotViewHolder(View view) {
        super(view);
        cover = (View) view.findViewById(R.id.shot_clickable_cover);
        likeCount = (TextView) view.findViewById(R.id.shot_like_count);
        bucketCount = (TextView) view.findViewById(R.id.shot_bucket_count);
        viewCount = (TextView) view.findViewById(R.id.shot_view_count);
        image = (ImageView) view.findViewById(R.id.shot_image);
    }
}
