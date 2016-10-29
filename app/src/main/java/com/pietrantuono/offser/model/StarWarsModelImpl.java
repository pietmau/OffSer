package com.pietrantuono.offser.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pietrantuono.offser.StarWarsApplication;
import com.pietrantuono.offser.model.api.StarWarsApi;
import com.pietrantuono.offser.model.api.pojos.AllFilms;
import com.pietrantuono.offser.model.api.pojos.AllPeople;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class StarWarsModelImpl implements StarWarsModel {  //TODO change name
    private static final String RETAINED_FRAGMENT_TAG = "retained_frag";
    private static final String TAG = StarWarsModelImpl.class.getSimpleName();
    //private Observable<AllFilms> cachedFilmsObservable;
    //private Observable<AllPeople> cachedPeopleObservable;
    private Subscription filmsSubscription;
    private Subscription peopleSubscription;
    private StarWarsApi starWarsApi;
    private StarWarsApplication app;
    private static StarWarsModelImpl instance;

    public StarWarsModelImpl(StarWarsApi starWarsApi, StarWarsApplication app) {
        this.starWarsApi = starWarsApi;
        this.app = app;
        Log.d(TAG, "setApis");

        app.cachedFilmsObservable = starWarsApi.getAllFilms().cache();
        app.cachedPeopleObservable = starWarsApi.getAllPeople().cache();
    }

    public static StarWarsModelImpl getInstance(StarWarsApi starWarsApi, StarWarsApplication app) {
        if (instance == null) {
            instance = new StarWarsModelImpl(starWarsApi, app);
        }
        return instance;
    }


    @Override
    public void subscribeToFilms(Observer<? super AllFilms> observer) {
        Log.d(TAG, "subscribeToFilms");
        filmsSubscription = app.cachedFilmsObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    @Override
    public void unSubscribeToFilms() {
        Log.d(TAG, "unSubscribeToFilms");
        if (filmsSubscription != null) {//TODo we need this?
            filmsSubscription.unsubscribe();
        }
    }

    @Override
    public void unSubscribeToPeople() {
        Log.d(TAG, "unSubscribeToPeople");
        if (peopleSubscription != null) {//TODo we need this?
            peopleSubscription.unsubscribe();
        }
    }

    @Override
    public void subscribeToPeople(Observer<? super AllPeople> observer) {
        Log.d(TAG, "subscribeToPeople");
        peopleSubscription = app.cachedPeopleObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}
