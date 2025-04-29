package com.example.unitconverterapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SlideItem> slideItems;
    private Context context;

    public SliderAdapter(List<SlideItem> slideItems, Context context) {
        this.slideItems = slideItems;
        this.context = context;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder holder, int position) {
        SlideItem slideItem = slideItems.get(position);

        holder.lottieAnimationView.setAnimation(slideItem.animation);
        holder.title.setText(slideItem.title);
        holder.description.setText(slideItem.description);

        /* if (position == slideItems.size() - 1) {
            holder.getStartedButton.setVisibility(View.VISIBLE);
            holder.getStartedButton.setOnClickListener(v -> {
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity) context).finish();
            });
        } else {
            holder.getStartedButton.setVisibility(View.GONE);
        }*/
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView lottieAnimationView;
        TextView title, description;
        //Button getStartedButton;

        public SliderViewHolder(View itemView) {
            super(itemView);
            lottieAnimationView = itemView.findViewById(R.id.lottieView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            //getStartedButton = itemView.findViewById(R.id.getStartedButton);
        }
    }
}
