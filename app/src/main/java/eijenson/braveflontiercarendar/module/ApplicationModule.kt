package eijenson.braveflontiercarendar.module

import android.content.Context
import dagger.Module
import dagger.Provides
import eijenson.braveflontiercarendar.Application
import javax.inject.Singleton

/**
 * Created by eijenson on 2017/08/27.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}