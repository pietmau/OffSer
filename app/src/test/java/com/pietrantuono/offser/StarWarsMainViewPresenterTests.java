package com.pietrantuono.offser;

import android.os.Bundle;

import com.pietrantuono.offser.presenter.main.StarWarsMainViewPresenter;
import com.pietrantuono.offser.view.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsMainViewPresenterTests {
    StarWarsMainViewPresenter starWarsMainViewPresenter;
    private Bundle bundle;
    @Mock
    MainView view;

    @Before
    public void setUp() {
        starWarsMainViewPresenter = new StarWarsMainViewPresenter();
        bundle = new Bundle();
    }

    @Test
    public void givenPresenter_whenOnCreteAndNoBundle_thenGoToFilms() throws Exception {
        // WHEN
        starWarsMainViewPresenter.onCreate(view, null);
        // THEN
        verify(view).navigateToFilms();
    }

    @Test
    public void givenPresenter_whenOnCreteAndBundle_thenNotGoToFilms() throws Exception {
        // WHEN
        starWarsMainViewPresenter.onCreate(view, bundle);
        // THEN
        verify(view, never()).navigateToFilms();
    }

    @Test
    public void givenPresenter_whenOnGoToFilmsClicked_thenGoToFilms() throws Exception {
        // GIVEN
        starWarsMainViewPresenter.onCreate(view, bundle);
        // WHEN
        starWarsMainViewPresenter.onGoToFilmsClicked();
        // THEN
        verify(view).navigateToFilms();
    }

    @Test
    public void givenPresenter_whenOnGoToPersonsClicked_thenGoToPersons() throws Exception {
        // GIVEN
        starWarsMainViewPresenter.onCreate(view, bundle);
        // WHEN
        starWarsMainViewPresenter.onGoToPersonsClicked();
        // THEN
        verify(view).navigateToPersons();
    }
}