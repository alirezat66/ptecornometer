package ir.greencode.cornometer.ui.Main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.mvp.model.TimerObject;
import ir.greencode.cornometer.mvp.view.SampleAdapterView;
import ir.greencode.cornometer.ui.Main.adapter.SampleAdapter;
import ir.greencode.cornometer.ui.Main.countdown.CountDownActivity;
import ir.greencode.cornometer.ui.Main.timer.TimerActivity;
import ir.greencode.cornometer.ui.Main.twostep.TwoStepActivity;

public class FragmentSample extends Fragment implements SampleAdapterView {
    @BindView(R.id.list)
    RecyclerView list;

    SampleAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        ButterKnife.bind(this, view);
        adapter = new SampleAdapter(getLayoutInflater(),this);
        list.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        list.setAdapter(adapter);
        List<TimerObject> objects = new ArrayList<>();
        objects.add(new TimerObject(1,"CountDown Timer","set time and we count down for you!",R.drawable.count_down_timer));
        objects.add(new TimerObject(2,"Stop Watch","we count time for you from 0!",R.drawable.timer));
        objects.add(new TimerObject(3,"Two Step Timer","set wait time befor start count down!",R.drawable.stopwatch));
        adapter.addData(objects);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClickOnItem(TimerObject object) {
        if(object.getId()==1){
            // go to countdown page
            Intent intent = new Intent(getActivity(), CountDownActivity.class);
            startActivity(intent);
        }else if(object.getId()==2){
            Intent intent = new Intent(getActivity(), TimerActivity.class);
            startActivity(intent);
            // go to timer page
        }else {
            Intent intent = new Intent(getActivity(), TwoStepActivity.class);
            startActivity(intent);
            //go to two step timer
        }
    }
}
