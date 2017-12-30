package projects.ritu.financeadviser;

/**
 * Created by Ritu on 01-05-2017.
 */

public class ArticleCard {
    private String article;
    private int thumbnail;
    private int articleId;
    private String articleTitle;
    public ArticleCard() {
    }

    public ArticleCard(String articleTitle,String article, int thumbnail,int categoryId) {
        this.article = article;
        this.thumbnail = thumbnail;
        this.articleId=categoryId;
        this.articleTitle=articleTitle;
    }

    public String getArticle() {
        return article;
    }

    public String getArticleTitle() {
        return articleTitle;
    }


    public int getArticleId() {return articleId;}

    public void setArticle(String article) {
        this.article = article;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
