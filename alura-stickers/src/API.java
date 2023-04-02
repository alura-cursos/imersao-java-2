public enum API {
    IMDB_TOP_MOVIES("https://imdb-api.com/en/API/Top250Movies/",new ContentExtractorIMDB()),
    IMDB_MOST_POPULAR("https://imdb-api.com/en/API/MostPopularMovies/",new ContentExtractorIMDB()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=",new ContentExtractorNASA()),
    LANGUAGE("http://localhost:8080/languages", new ContentExtractorLanguage());
    private String url;
    private ContentExtractor extractor;

    API(String url, ContentExtractor extractor){
        this.url = url; 
        this.extractor  = extractor;
    }

    public String getUrl(){
        return url;
    }

    public ContentExtractor getExtractor(){
        return extractor;
    }
}
