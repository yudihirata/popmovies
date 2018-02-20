package presenter;

import com.yudihirata.br.popmovies.BuildConfig;

import models.MovieResponse;
import models.ReviewsResponse;
import models.TrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marco.hirata on 01/09/2017.
 */

interface MovieDBServiceInterface {
    @GET("movie/{filter}?api_key=" + BuildConfig.API_KEY)
    Call<MovieResponse> getMovieDBResponse(
            @Path("filter") String filter);

    @GET("movie/{filter}/reviews?api_key=" + BuildConfig.API_KEY)
        //ReviewsResponse
    Call<ReviewsResponse> getReviewsResponse(
            @Path("filter") String filter);

    @GET("movie/{filter}/videos?api_key=" + BuildConfig.API_KEY)
    Call<TrailerResponse> getTrailerResponse(
            @Path("filter") String filter);
}


