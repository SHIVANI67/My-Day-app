package com.example.mydayapplication.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mydayapplication.Activity.VideoPlayerActivity;
import com.example.mydayapplication.Adapters.TaskListAdapter;
import com.example.mydayapplication.Pojo.TaskList;
import com.example.mydayapplication.Pojo.Tasks;
import com.example.mydayapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class UserTaskFragment extends Fragment implements TaskListAdapter.OnTaskClickedListener {

    private static final String ARG_PARAM1 = "index";
    private static final String ARG_PARAM2 = "tasks";
    private RecyclerView rvTasks;
    private TaskListAdapter adapter;
    private TextView noTaskFound;
    SharedPreferences preferences;
    private ProgressBar progressBar;

    private ArrayList<Tasks> tasksArrayList;

    private int index;

    public UserTaskFragment() {
    }

    public static UserTaskFragment newInstance(int index, ArrayList<Tasks> tasksArrayList) {
        UserTaskFragment fragment = new UserTaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        args.putSerializable(ARG_PARAM2, tasksArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = this.getActivity().getPreferences(MODE_PRIVATE);

        String returnJson = preferences.getString("Tasklists", "");

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1);
            tasksArrayList = (ArrayList<Tasks>) getArguments().getSerializable(ARG_PARAM2);

            Log.e("taskarray", "list is not empty");
        }
        Log.e("taskarray", "list is empty");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_task, container, false);
        rvTasks = view.findViewById(R.id.tasks_rv);
        noTaskFound = view.findViewById(R.id.no_task_found_tv);
        progressBar = view.findViewById(R.id.progress_circular);

        if (tasksArrayList != null && tasksArrayList.size() != 0) {
            rvTasks.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            adapter = new TaskListAdapter(getContext(), tasksArrayList, this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvTasks.setLayoutManager(layoutManager);
            rvTasks.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        } else {
            noTaskFound.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onTaskClicked(int position, Tasks tasks) {

        tasks.setComplete(true);

        if (tasks.getType().equals("VOD")) {
            Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
            intent.putExtra("video url", tasks.getVideoPojo().getHlsUrl());
            startActivity(intent);
        }
    }
}
