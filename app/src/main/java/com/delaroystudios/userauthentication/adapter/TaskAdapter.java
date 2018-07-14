package com.delaroystudios.userauthentication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.delaroystudios.userauthentication.R;
import com.delaroystudios.userauthentication.model.Task;

import java.util.List;

/**
 * Created by delaroy on 7/13/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context context;
    private List<Task> tasks;
    public static final String HEALTH_RECORD = "health_record";
    private ColorGenerator colorGenerator = ColorGenerator.DEFAULT;
    private TextDrawable drawableBuilder;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_items, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskAdapter.MyViewHolder viewHolder, int i) {
        String title = tasks.get(i).getTitle();
        viewHolder.task_title.setText(title);
        viewHolder.task_date_time.setText(tasks.get(i).getTaskdate() + ", " + tasks.get(i).getTasktime());
        String letter = "A";

        if(title != null && !title.isEmpty()) {
            letter = title.substring(0, 1);
        }

        int color = colorGenerator.getRandomColor();

        // Create a circular icon consisting of  a random background colour and first letter of title
        drawableBuilder = TextDrawable.builder()
                .buildRound(letter, color);
        viewHolder.thumbnailImage.setImageDrawable(drawableBuilder);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Set reminder title view
    public void setScheduleTitle(String title) {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView task_title, task_date_time;
        public ImageView  thumbnailImage;
        public MyViewHolder(View view) {
            super(view);
            task_title = (TextView) view.findViewById(R.id.task_title);
            task_date_time = (TextView) view.findViewById(R.id.task_date_time);
            thumbnailImage = (ImageView) view.findViewById(R.id.thumbnail_image);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                }
            });
        }
    }
}
