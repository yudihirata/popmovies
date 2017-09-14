package presenter;

import com.yudihirata.br.popmovie.BuildConfig;

import models.MovieDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marco.hirata on 01/09/2017.
 */

interface MovieDBServiceInterface {
    @GET("movie/{filter}?api_key="+ BuildConfig.API_KEY)
    Call<MovieDBResponse> getMovieDBResponse(
            @Path("filter") String filter);
}


