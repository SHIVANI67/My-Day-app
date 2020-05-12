package com.example.mydayapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydayapplication.Activity.VideoPlayerActivity;
import com.example.mydayapplication.Pojo.Tasks;
import com.example.mydayapplication.R;

import java.util.ArrayList;

// Recyclerview adapter to populate fragment with data
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private Context context;
    private ArrayList<Tasks> taskList;
    private OnTaskClickedListener taskClickedListener;

    public TaskListAdapter(Context context, ArrayList<Tasks> taskList, OnTaskClickedListener taskClickedListener) {
        this.context = context;
        this.taskList = taskList;
        this.taskClickedListener = taskClickedListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_adapter, parent, false);
        return new TaskViewHolder(view, taskClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tasks taskItem = taskList.get(position);
        if (taskItem != null) {
            if (taskItem.isComplete()) {
                if (taskItem.getType().equals("SUPP")) {
                    holder.drugTaken.setVisibility(View.VISIBLE);
                    holder.cvDrugToBeTaken.setVisibility(View.GONE);
                    holder.completedDrug.setVisibility(View.VISIBLE);
                    holder.iconComplete.setVisibility(View.VISIBLE);
                    holder.tvDrugTaken.setText(taskItem.getDrug().getBrandNm());
                    holder.tvDosageTaken.setText(taskItem.getDrug().getDosage().getDose() + " " + taskItem.getDrug().getDosage().getUnit());
                } else if (taskItem.getType().equals("VOD")) {
                    holder.exerciseCompleted.setVisibility(View.VISIBLE);
                    holder.cvExerciseToBeDone.setVisibility(View.GONE);
                    holder.completedExercise.setVisibility(View.VISIBLE);
                    holder.iconComplete2.setVisibility(View.VISIBLE);
                    holder.tvExerciseCompleted.setText(taskItem.getVideoPojo().getTitle());
                }
            } else {
                if (taskItem.getType().equals("SUPP")) {
                    holder.drugTaken.setVisibility(View.VISIBLE);
                    holder.cvDrugToBeTaken.setVisibility(View.VISIBLE);
                    holder.tvDrugToBeTaken.setText(taskItem.getDrug().getBrandNm());
                    holder.tvDosageToBeTaken.setText(taskItem.getDrug().getDosage().getDose() + " " + taskItem.getDrug().getDosage().getUnit());
                } else if (taskItem.getType().equals("VOD")) {
                    holder.exerciseCompleted.setVisibility(View.VISIBLE);
                    holder.cvExerciseToBeDone.setVisibility(View.VISIBLE);
                    Glide.with(holder.videoThumbnail).load(taskItem.getVideoPojo().getThumbnail()).into(holder.videoThumbnail);
                    holder.tvExerciseToBeDone.setText(taskItem.getVideoPojo().getTitle());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout drugTaken, exerciseCompleted, completedDrug, completedExercise;
        TextView tvDrugTaken, tvDosageTaken, tvDrugToBeTaken, tvDosageToBeTaken, tvWhenToBeTaken, tvExerciseCompleted, tvExerciseToBeDone, tvExerciseDuration;
        ImageView videoThumbnail;
        OnTaskClickedListener listener;
        ImageView iconComplete, iconComplete2;
        CardView cvDrugToBeTaken, cvExerciseToBeDone;

        public TaskViewHolder(@NonNull View itemView, OnTaskClickedListener listener) {
            super(itemView);
            this.listener = listener;

            drugTaken = itemView.findViewById(R.id.drug_taken_ll);
            exerciseCompleted = itemView.findViewById(R.id.exercise_done_ll);
            videoThumbnail = itemView.findViewById(R.id.video_thumbnail);
            tvDrugTaken = itemView.findViewById(R.id.drug_taken_tv);
            tvDosageTaken = itemView.findViewById(R.id.dosage_tv);
            tvDrugToBeTaken = itemView.findViewById(R.id.drug_to_be_taken_tv);
            tvDosageToBeTaken = itemView.findViewById(R.id.dosage_to_be_taken_tv);
            tvWhenToBeTaken = itemView.findViewById(R.id.when_to_be_taken_tv);
            tvExerciseCompleted = itemView.findViewById(R.id.exercise_completed_tv);
            tvExerciseToBeDone = itemView.findViewById(R.id.exercise_to_be_done_tv);
            tvExerciseDuration = itemView.findViewById(R.id.exercise_duration_tv);
            cvDrugToBeTaken = itemView.findViewById(R.id.drug_to_taken_cv);
            cvExerciseToBeDone = itemView.findViewById(R.id.exercise_to_be_done_cv);
            completedDrug = itemView.findViewById(R.id.completed_drug_ll);
            iconComplete = itemView.findViewById(R.id.icon_complete);
            iconComplete2 = itemView.findViewById(R.id.icon_complete2);
            completedExercise = itemView.findViewById(R.id.exercise_completed);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {  // handle on touch of recycler view item
            if (taskList.get(getAdapterPosition()).getType().equals("SUPP")) {
                cvDrugToBeTaken.setVisibility(View.GONE);
                completedDrug.setVisibility(View.VISIBLE);
                iconComplete.setVisibility(View.VISIBLE);
                tvDrugTaken.setText(tvDrugToBeTaken.getText());
                tvDosageTaken.setText(tvDosageToBeTaken.getText());
            } else if (taskList.get(getAdapterPosition()).getType().equals("VOD")) {
                cvExerciseToBeDone.setVisibility(View.GONE);
                completedExercise.setVisibility(View.VISIBLE);
                iconComplete2.setVisibility(View.VISIBLE);
                tvExerciseCompleted.setText(tvExerciseToBeDone.getText());
            }

            listener.onTaskClicked(getAdapterPosition(), taskList.get(getAdapterPosition()));
        }
    }

    // interface to implement onn touch listener of item
    public interface OnTaskClickedListener {
        void onTaskClicked(int position, Tasks tasks);
    }
}
