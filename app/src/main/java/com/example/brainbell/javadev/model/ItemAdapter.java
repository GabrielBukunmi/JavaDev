package com.example.brainbell.javadev.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.brainbell.javadev.R;
import com.example.brainbell.javadev.controller.DetailActivity;

import java.util.List;

/**
 * Created by BRAINBELL on 12-Sep-17.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemsArraylist) {
        this.context = applicationContext;
        this.items = itemsArraylist;

    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_activity, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getLogin());
        Glide.with(context)
                .load(items.get(i).getAvatar_Url()).asBitmap()
                .placeholder(R.drawable.placeholder2)
                .into(new BitmapImageViewTarget(viewHolder.imageView){
                    @Override
                    protected void setResource(Bitmap resource){
                        RoundedBitmapDrawable circularBitmapDrawable=
                                RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                            circularBitmapDrawable.setCircular(true);
                             viewHolder.imageView.setImageDrawable(circularBitmapDrawable);
                    }

                });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.cover);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posit = getAdapterPosition();
                    if (posit != RecyclerView.NO_POSITION) {
                        Item clickedData = items.get(posit);
                        Intent intent = new Intent(context,DetailActivity.class);
                        intent.putExtra("login", items.get(posit).getLogin());
                        intent.putExtra("html_url", items.get(posit).getHtml_Url());
                        intent.putExtra("avatar_url", items.get(posit).getAvatar_Url());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), clickedData.getLogin(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}

