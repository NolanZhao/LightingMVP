package me.wuxm.lightingmvp.editarticle;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by wuxm on 16/12/14.
 */

public class EditArticlePresenter implements EditArticleContract.Presenter {

    @NonNull
    private final EditArticleContract.View mView;

    @Inject
    public EditArticlePresenter(EditArticleContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void uploadArticle(String title, String description) {

    }

    @Override
    public void start() {

    }

    @Inject
    void setupListeners(){
        mView.setPresenter(this);
    }
}
