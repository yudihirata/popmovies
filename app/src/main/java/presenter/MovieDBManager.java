package presenter;

import models.MovieResponse;
import models.ReviewsResponse;
import models.TrailerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by marco.hirata on 04/09/2017.
 */


public class MovieDBManager {

    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String FAVORITES = "favorites";
    private final MovieDBServiceInterface mMovieDBServiceInterface;

    public MovieDBManager() {
        Retrofit retrofit = MovieDBClient.get();
        mMovieDBServiceInterface = retrofit.create(MovieDBServiceInterface.class);
    }

    public void get(String action, Callback<MovieResponse> callback) {

        Call<MovieResponse> call = mMovieDBServiceInterface.getMovieDBResponse(action);
        if (call != null) {
            call.enqueue(callback);
        }
    }

    public void getTrailers(String movie_id, Callback<TrailerResponse> callback) {
        Call<TrailerResponse> call = mMovieDBServiceInterface.getTrailerResponse(movie_id);
        if (call != null) {
            call.enqueue(callback);
        }
    }

    public void getReviews(String movie_id, Callback<ReviewsResponse> callback) {
        Call<ReviewsResponse> call = mMovieDBServiceInterface.getReviewsResponse(movie_id);
        if (call != null) {
            call.enqueue(callback);
        }
    }
}
