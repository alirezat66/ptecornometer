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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.data.roomdb.MyTimer;
import ir.greencode.cornometer.data.roomdb.base.AppDatabase;
import ir.greencode.cornometer.mvp.view.TimerAdapterView;
import ir.greencode.cornometer.ui.Main.SavedActivity;
import ir.greencode.cornometer.ui.Main.adapter.MyTimerAdapter;

public class FragmentList extends Fragment implements TimerAdapterView {
    AppDatabase database;
    @BindView(R.id.list)
    RecyclerView list;

    MyTimerAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        database = AppDatabase.getInMemoryDatabase(getContext());
        list.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new MyTimerAdapter(getLayoutInflater(),this);
        list.setAdapter(adapter);
        List<MyTimer> timers = database.categoryDao().listOfTimers();
        adapter.addData(timers);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClickOnItem(MyTimer object) {
        Intent intent = new Intent(getContext(), SavedActivity.class);
        intent.putExtra("type",object.gettype());
        intent.putExtra("title",object.gettitle());
        intent.putExtra("time",object.gettime());
        intent.putExtra("waitingTime",object.getwaitingTime());
        startActivity(intent);
    }
}
