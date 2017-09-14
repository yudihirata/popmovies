package presenter;

import models.MovieDBResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by marco.hirata on 04/09/2017.
 */


public class MovieDBManager {

    public static final String POPULAR   = "popular";
    public static final String TOP_RATED = "top_rated";
    private final MovieDBServiceInterface mMovieDBServiceInterface;

    public MovieDBManager() {
        Retrofit retrofit        = MovieDBClient.get();
        mMovieDBServiceInterface = retrofit.create(MovieDBServiceInterface.class);
    }

    public void get(String action, Callback<MovieDBResponse> callback ){
        Call<MovieDBResponse> call = mMovieDBServiceInterface.getMovieDBResponse(action);
        if (call != null){
            call.enqueue(callback);
        }
    }
}
