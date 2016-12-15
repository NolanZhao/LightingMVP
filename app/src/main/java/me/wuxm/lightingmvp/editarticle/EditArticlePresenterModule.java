package me.wuxm.lightingmvp.editarticle;

import android.support.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wuxm on 16/12/14.
 */
@Module
public class EditArticlePresenterModule {

    private final EditArticleContract.View mView;


    public EditArticlePresenterModule(EditArticleContract.View mView) {
        this.mView = mView;
    }

    @Provides
    EditArticleContract.View provideEditArticleContractView(){
        return mView;
    }

}
