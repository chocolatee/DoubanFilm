package com.shenhui.doubanfilm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shenhui.doubanfilm.R;
import com.shenhui.doubanfilm.base.BaseAdapter;
import com.shenhui.doubanfilm.bean.SimpleCardBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CastCardAdapter extends BaseAdapter<CastCardAdapter.ViewHolder> {

    private Context mContext;
    private List<SimpleCardBean> mData;
    private OnItemClickListener callback;

    public CastCardAdapter(Context context, List<SimpleCardBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_cast_card_layout, parent, false);
        return new ViewHolder(v);
    }

    public void setOnItemClickListener(OnItemClickListener callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SimpleCardBean sub = mData.get(position);
        if (sub.getImage() != null) {
            imageLoader.displayImage(sub.getImage(),
                    holder.image, options);
        }
        if (sub.getIsDir()) {
            holder.text_dir.setVisibility(View.VISIBLE);
        }
        holder.text.setText(sub.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cast_item_image)
        ImageView image;
        @Bind(R.id.cast_item_text)
        TextView text;
        @Bind(R.id.cast_dir_text)
        TextView text_dir;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) {
                        int pos = getLayoutPosition();
                        callback.itemClick(mData.get(pos).getId(), mData.get(pos).getIsFilm());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void itemClick(String id, boolean isCom);
    }
}