package com.vs.indexae.di

import android.content.Context
import androidx.room.Room
import com.vs.indexae.dao.ItemDAO
import com.vs.indexae.room_database.MyRoomDatabase
import com.vs.indexae.ui.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context):MyRoomDatabase
            = Room.databaseBuilder(context,MyRoomDatabase::class.java,"index_ie_database")
        .allowMainThreadQueries().build()

    @Provides
    fun providesItemDao(itemDatabase: MyRoomDatabase):ItemDAO = itemDatabase.itemDao()

}