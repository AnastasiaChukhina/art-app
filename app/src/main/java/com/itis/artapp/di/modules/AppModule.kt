package com.itis.artapp.di.modules

import com.itis.artapp.di.modules.qualifiers.SchedulerIo
import com.itis.artapp.di.modules.qualifiers.SchedulerUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @SchedulerUi
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @SchedulerIo
    fun provideAsyncScheduler(): Scheduler = Schedulers.io()
}
