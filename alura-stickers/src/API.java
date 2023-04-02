public enum API {
    IMDB_TOP_MOVIES("https://imdb-api.com/en/API/Top250Movies/"),
    IMDB_MOST_POPULAR("https://imdb-api.com/en/API/MostPopularMovies/"),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=");

    private String url;

    API(String url){
        this.url = url; 
    }

    public String getUrl(){
        return url;
    }
}
