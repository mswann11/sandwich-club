package com.udacity.sandwichclub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichAdapterViewHolder> {
    private String[] mSandwichNames;
    private String[] mSandwichImages;
    private Context context;
    final private SandwichAdapterOnClickHandler mClickHandler;

    public interface SandwichAdapterOnClickHandler {
        void OnClick(int position);
    }

    public SandwichAdapter(SandwichAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class SandwichAdapterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        public final TextView mSandwichTextView;
        public final ImageView mSandwichImageView;
        public SandwichAdapterViewHolder(View view) {
            super(view);
            mSandwichTextView = view.findViewById(R.id.sandwich_name);
            mSandwichImageView = view.findViewById(R.id.sandwich_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mClickHandler.OnClick(clickedPosition);
        }
    }

    @Override
    public SandwichAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        SandwichAdapterViewHolder viewHolder = new SandwichAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SandwichAdapterViewHolder holder, int position) {
        holder.mSandwichTextView.setText(mSandwichNames[position]);
        Picasso.with(context)
                .load(mSandwichImages[position])
                .into(holder.mSandwichImageView);
    }

    @Override
    public int getItemCount() {
        if(mSandwichNames == null) {
            return 0;
        } else {
            return mSandwichNames.length;
        }
    }

    public void setSandwichData(String[] sandwichNames, String[] sandwichImages){
        mSandwichNames = sandwichNames;
        mSandwichImages = sandwichImages;
        notifyDataSetChanged();
    }
}
