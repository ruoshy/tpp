package cn.cwc.api.entity;

public class Broadcast {

    private int id;

    private int filmId;

    private String url;

    private boolean isMainPage;

    private int type;

    private Film film;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMainPage() {
        return isMainPage;
    }

    public void setMainPage(boolean mainPage) {
        isMainPage = mainPage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
