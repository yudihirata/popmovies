package presenter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marco.hirata on 01/09/2017.
 */

class MovieDBClient {
    private static final String BASE_API_URL = "http://api.themoviedb.org/3/";
    static Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
