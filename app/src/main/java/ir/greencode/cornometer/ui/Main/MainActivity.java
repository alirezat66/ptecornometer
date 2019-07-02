package ir.greencode.cornometer.ui.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.greencode.cornometer.R;
import ir.greencode.cornometer.base.BaseActivity;
import ir.greencode.cornometer.data.loc.MySharedPreferences;
import ir.greencode.cornometer.di.component.DaggerHomeComponent;
import ir.greencode.cornometer.di.module.HomeModule;
import ir.greencode.cornometer.mvp.model.GroupResponseResult;
import ir.greencode.cornometer.mvp.presenter.HomePresenter;
import ir.greencode.cornometer.mvp.view.HomeView;
import ir.greencode.cornometer.ui.Main.fragment.FragmentAbout;
import ir.greencode.cornometer.ui.Main.fragment.FragmentList;
import ir.greencode.cornometer.ui.Main.fragment.FragmentSample;

public class MainActivity extends BaseActivity implements HomeView {


    @Inject
    protected HomePresenter mPresenter;
    @Inject
    MySharedPreferences mySharedPreferencesl;

    @Inject
    HomeViewModel viewModel;
    ArrayList<Fragment> fragList = new ArrayList<>();
    @BindView(R.id.floating_top_bar_navigation)
    BubbleNavigationConstraintView floatingTopBarNavigation;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent, int LAYER_TYPE_SOFTWARE) {
        super.onViewReady(savedInstanceState, intent, LAYER_TYPE_SOFTWARE);
        ButterKnife.bind(this);
        init();
        //  mPresenter.getGroups();
        // viewModel.insertTimer(new Category("ali",Color.RED,""));
        //Toast.makeText(this, viewModel.getAllCats().size()+"", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    private void init() {
        fragList.add(new FragmentList());
        fragList.add(new FragmentSample());

        fragList.add(new FragmentAbout());

        ViewPagerAdapter adapter = new ViewPagerAdapter(fragList,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                floatingTopBarNavigation.setCurrentActiveItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        floatingTopBarNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCleareItems() {
        /*adapter.cleareData();*/
        //clear data
    }

    @Override
    public void onGroupesLoaded(List<GroupResponseResult> results) {
        // set data to your adapter
/*
        adapter.addData(results);
*/
    }

    @Override
    public void onGroupError(Throwable e) {
        showWaitingDialog(false);
    }

    @Override
    public void onClickOnItem(GroupResponseResult groupResponseResult) {

    }

    @Override
    public void onShowDialog() {
        showWaitingDialog(true);

    }

    @Override
    public void onUnauthorizedError() {
        // exit from project
    }

}
