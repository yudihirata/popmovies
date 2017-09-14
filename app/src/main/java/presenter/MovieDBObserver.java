package presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yudihirata.br.popmovie.adapters.RestAdapter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import models.Movie;

/**
 * Created by marco.hirata on 04/09/2017.
 */

public class MovieDBObserver implements Observer<List<Movie>> {
    private final RecyclerView mRecyclerView;

    public MovieDBObserver(Context context, RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerView.setAdapter(new RestAdapter(context));
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull List<Movie> list) {
        RestAdapter adapter = (RestAdapter)mRecyclerView.getAdapter();
        if (adapter != null){
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
