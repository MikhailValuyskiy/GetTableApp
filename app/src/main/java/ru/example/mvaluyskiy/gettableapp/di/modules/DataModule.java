package ru.example.mvaluyskiy.gettableapp.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.mvaluyskiy.gettableapp.api.retrofit.ApiInterface;
import ru.example.mvaluyskiy.gettableapp.data.database.OrmLiteDatabaseHelper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.CustomerMapper;
import ru.example.mvaluyskiy.gettableapp.data.mappers.TableMapper;
import ru.example.mvaluyskiy.gettableapp.data.repository.AppRepository;
import ru.example.mvaluyskiy.gettableapp.data.repository.LocalRepository;
import ru.example.mvaluyskiy.gettableapp.data.repository.RemoteRepository;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    OrmLiteDatabaseHelper provideOrmLiteDatabaseHelper(Context context) {
        return new OrmLiteDatabaseHelper(context);
    }

    @Provides
    @Singleton
    LocalRepository provideLocalRepository(OrmLiteDatabaseHelper helper) {
        return new LocalRepository(helper);
    }

    @Provides
    @Singleton
    RemoteRepository provideRemoteRepository(ApiInterface apiInterface,
                                             CustomerMapper customerMapper,
                                             TableMapper tableMapper,
                                             LocalRepository localRepository) {
        return new RemoteRepository(apiInterface, customerMapper, tableMapper, localRepository);
    }

    @Provides
    @Singleton
    AppRepository provideGetTableRepository() {
        return new AppRepository();
    }
}
