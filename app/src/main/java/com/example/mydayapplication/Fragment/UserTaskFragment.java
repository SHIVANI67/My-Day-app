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
    private static final String ARG_PARAM2 = "position";
    private RecyclerView rvTasks;
    private TaskListAdapter adapter;
    private TextView noTaskFound;
    SharedPreferences preferences;
    private TaskList list;
    private ArrayList<Tasks> morningList = new ArrayList<>();
    private ArrayList<Tasks> afternoonList = new ArrayList<>();
    private ArrayList<Tasks> eveningList = new ArrayList<>();
    private ArrayList<Tasks> nightList = new ArrayList<>();

    private int index;
    private int position;

    public UserTaskFragment() {
    }

    public static UserTaskFragment newInstance(int index, int position) {
        UserTaskFragment fragment = new UserTaskFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        args.putInt(ARG_PARAM2, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = this.getActivity().getPreferences(MODE_PRIVATE);

        String returnJson = preferences.getString("Tasklists", "");
        list = new Gson().fromJson(returnJson, TaskList.class);

        for (Tasks task : list.getTasks()) {
            int frequency = task.getFrequency();
            int duration = task.getDuration();
            int iteration = duration / frequency;

            if ((index % frequency == 0) && index < iteration) {
                for (int i = 0; i < task.getScheduleList().length; i++) {
                    String timeOfDay = task.getScheduleList()[i].getSession();

                    if (timeOfDay.equals("MORNING")) {
                        morningList.add(task);
                    } else if (timeOfDay.equals("AFTERNOON")) {
                        afternoonList.add(task);
                    } else if (timeOfDay.equals("EVENING")) {
                        eveningList.add(task);
                    } else {
                        nightList.add(task);
                    }
                }
            }
        }

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1);
            position = getArguments().getInt(ARG_PARAM2);

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

        if (list != null) {
            rvTasks.setVisibility(View.VISIBLE);

            if (position == 0) {
                if (morningList == null) {
                    noTaskFound.setVisibility(View.VISIBLE);
                } else {
                    adapter = new TaskListAdapter(getContext(), morningList, this);
                }
            } else if (position == 1) {
                if (afternoonList.size() == 0) {
                    rvTasks.setVisibility(View.GONE);
                    noTaskFound.setVisibility(View.VISIBLE);
                } else {
                    adapter = new TaskListAdapter(getContext(), afternoonList, this);
                }
            } else if (position == 2) {
                if (eveningList.size() == 0) {
                    rvTasks.setVisibility(View.GONE);
                    noTaskFound.setVisibility(View.VISIBLE);
                } else {
                    adapter = new TaskListAdapter(getContext(), eveningList, this);
                }
            } else {
                if (nightList.size() == 0) {
                    rvTasks.setVisibility(View.GONE);
                    noTaskFound.setVisibility(View.VISIBLE);
                } else {
                    adapter = new TaskListAdapter(getContext(), nightList, this);
                }
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvTasks.setLayoutManager(layoutManager);
            rvTasks.setAdapter(adapter);
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
