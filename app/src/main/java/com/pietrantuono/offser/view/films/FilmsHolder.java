package com.pietrantuono.offser.view.films;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.offser.R;
import com.pietrantuono.offser.model.api.pojos.Film;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
class FilmsHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    private final TextView episodeView;
    private final TextView directorView;
    private final TextView producerView;
    private final TextView releaseView;

    public FilmsHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.title);
        episodeView = (TextView) itemView.findViewById(R.id.episodeid);
        directorView = (TextView) itemView.findViewById(R.id.director);
        producerView = (TextView) itemView.findViewById(R.id.producer);
        releaseView = (TextView) itemView.findViewById(R.id.release);
    }

    public void bind(Film film) {
        String title = film.getTitle() == null ? titleView.getContext().getResources().getString(R.string.not_available) : film.getTitle();
        titleView.setText(title);

        episodeView.setText(Integer.toString(film.getEpisodeId()));

        String director = film.getDirector() == null ? titleView.getContext().getResources().getString(R.string.not_available) : film.getDirector();
        directorView.setText(director);

        String producer = film.getProducer() == null ? titleView.getContext().getResources().getString(R.string.not_available) : film.getProducer();
        producerView.setText(producer);

        String release = film.getReleaseDate() == null ? titleView.getContext().getResources().getString(R.string.not_available) : film.getReleaseDate();
        releaseView.setText(release);
    }
}