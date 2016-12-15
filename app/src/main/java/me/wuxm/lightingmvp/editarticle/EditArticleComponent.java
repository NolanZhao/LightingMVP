package me.wuxm.lightingmvp.editarticle;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Component;
import dagger.Module;

/**
 * Created by wuxm on 16/12/14.
 */

@Component(modules = EditArticlePresenterModule.class)
public interface EditArticleComponent {

    void inject(EditArticleActivity editArticleActivity);
}
