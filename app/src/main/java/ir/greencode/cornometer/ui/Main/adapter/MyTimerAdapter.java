package ir.greencode.cornometer.ui.Main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.data.roomdb.MyTimer;
import ir.greencode.cornometer.mvp.view.TimerAdapterView;

public class MyTimerAdapter extends RecyclerView.Adapter<MyTimerAdapter.Holder> {
    LayoutInflater mLayoutInflater;
    List<MyTimer> list;
    TimerAdapterView mainView;


    public MyTimerAdapter(LayoutInflater mLayoutInflater, TimerAdapterView mainView) {
        this.mLayoutInflater = mLayoutInflater;
        list = new ArrayList<>();
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_timer, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {

        holder.bind(list.get(i));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.onClickOnItem(list.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<MyTimer> cakes) {
        list.addAll(cakes);
    }

    public void cleareData() {
        list.clear();
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.img_logo)
        AppCompatImageView imgLogo;


        private Context mContext;

        public Holder(@NonNull View view) {
            super(view);
            mContext = view.getContext();
            ButterKnife.bind(this, view);
        }

        public void bind(MyTimer result) {

            tvTitle.setText(result.gettitle());
            tvDesc.setText(result.getdesc());
            Glide.with(mContext).load(R.drawable.ic_launcher_background)
                    .into(imgLogo);
        }
    }
}
