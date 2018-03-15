package presenter;

import android.support.v7.widget.RecyclerView;

import com.yudihirata.br.popmovies.adapters.BaseAdapter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by marco.hirata on 08/03/2018.
 */

public class Observer implements io.reactivex.Observer<List<?>> {
    private final RecyclerView mRecyclerView;

    public Observer(RecyclerView mRecyclerView, RecyclerView.Adapter adapter) {
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerView.setAdapter(adapter);
    }


    public void onSubscribe(@NonNull Disposable d) {

    }

    public void onError(@NonNull Throwable e) {

    }

    public void onComplete() {

    }

    public void setList(List<?> list){
        if (list.size() > 0) {
            BaseAdapter adapter = (BaseAdapter) mRecyclerView.getAdapter();
            adapter.setList(list);
            this.setAdapter(adapter);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onNext(@NonNull List<?> list) {
        this.setList(list);
    }

}
