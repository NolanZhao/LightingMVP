package me.wuxm.lightingmvp.editarticle;

import me.wuxm.lightingmvp.BasePresenter;
import me.wuxm.lightingmvp.BaseView;

/**
 * Created by wuxm on 16/12/14.
 */

public interface EditArticleContract {

    interface View extends BaseView<Presenter>{

        void showErrorMsg(String msg);

        void showProgress();

        boolean isActive();
    }

    interface Presenter extends BasePresenter{

        void uploadArticle(String title,String description);
    }
}
