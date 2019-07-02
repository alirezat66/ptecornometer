package ir.greencode.cornometer.mvp.view;

import java.util.List;

import ir.greencode.cornometer.base.BaseView;
import ir.greencode.cornometer.mvp.model.GroupResponseResult;

public interface HomeView extends BaseView {
    void onCleareItems();

    void onGroupesLoaded(List<GroupResponseResult> results);

    void onGroupError(Throwable e);

    void onClickOnItem(GroupResponseResult groupResponseResult);
}
