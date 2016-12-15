package me.wuxm.lightingmvp;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wuxm on 16/12/14.
 */

@Module
public final class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
