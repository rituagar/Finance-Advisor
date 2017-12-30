package projects.ritu.financeadviser;

/**
 * Created by Ritu on 01-05-2017.
 */

public class Category {
    private String name;
    private int numOfSongs;
    private int thumbnail;
    private int categoryId;
    public Category() {
    }

    public Category(String name, int numOfSongs, int thumbnail,int categoryId) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.categoryId=categoryId;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {return categoryId;}
    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
