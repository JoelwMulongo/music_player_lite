package com.atul.musicplayerlite.adapter;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.atul.musicplayerlite.MPConstants;
import com.atul.musicplayerlite.MPPreferences;
import com.atul.musicplayerlite.R;
import com.atul.musicplayerlite.helper.ThemeHelper;

import java.util.List;

public class AccentAdapter extends RecyclerView.Adapter<AccentAdapter.MyViewHolder> {

    public List<Integer> accentList;
    private Activity activity;

    public AccentAdapter(Activity activity) {
        this.accentList = MPConstants.ACCENT_LIST;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accent, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageViewCompat.setImageTintList(
                holder.accent,
                ColorStateList.valueOf(activity.getColor(accentList.get(position)))
        );

        if(accentList.get(position) == MPPreferences.getTheme(activity.getApplicationContext()))
            holder.check.setVisibility(View.VISIBLE);
        else
            holder.check.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return accentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageButton accent;
        private final ImageButton check;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            accent = itemView.findViewById(R.id.accent);
            check = itemView.findViewById(R.id.check);

            accent.setOnClickListener(v -> {
                MPPreferences.storeTheme(activity.getApplicationContext(), accentList.get(getAdapterPosition()));
                ThemeHelper.setTheme(activity);
            });
        }
    }
}
