package models;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
/*
* Warning:(28, 17) Parceler: Reflection is required to access private field: String
* originalLanguage, consider using non-private.
* */
@SuppressWarnings({"WeakerAccess", "unused"})
@Generated("com.robohorse.robopojogenerator")
@Parcel
public class Movie {
	private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";


	@SerializedName("overview")
	protected String overview;

	@SerializedName("original_language")
	protected String originalLanguage;

	@SerializedName("original_title")
	protected String originalTitle;

	@SerializedName("video")
	protected boolean video;

	@SerializedName("title")
	protected String title;

	@SerializedName("genre_ids")
	protected List<Integer> genreIds;

	@SerializedName("poster_path")
	protected String posterPath;

	@SerializedName("backdrop_path")
	protected String backdropPath;

	@SerializedName("release_date")
	protected String releaseDate;

	@SerializedName("vote_average")
	protected double voteAverage;

	@SerializedName("popularity")
	protected double popularity;

	@SerializedName("id")
	protected int id;

	@SerializedName("adult")
	protected boolean adult;

	@SerializedName("vote_count")
	protected int voteCount;

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	private String getPosterPath(){
		return posterPath;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	private String getBackdropPath(){
		return backdropPath;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	private String getReleaseDate(){
		return releaseDate;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}
	public URI getPosterUri(String size){
		String url = BASE_IMAGE_URL + "/" + size + "/" + getPosterPath();
		try {
			URI uri = new URI(url);
			return uri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	public URI getPosterUri(){
		return getPosterUri("w185");
	}

	public URI getBackdropUri(String size){
		String url = BASE_IMAGE_URL + "/" + size + "/" + getBackdropPath();
		try {
			URI uri = new URI(url);
			return uri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	public URI getBackdropUri(){
		return  getBackdropUri("w500");
	}

	public String getReleaseDate(String format){
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
			DateFormat df_in = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df_output = new SimpleDateFormat(format);
			try {
				Date date = df_in.parse(getReleaseDate());
				return df_output.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return getReleaseDate();
	}

	@Override
 	public String toString(){
		return
			"Movie{" +
			"overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}