import java.util.Comparator;

public class ComparateurArticle implements Comparator<Article> {

    @Override
    public int compare(Article article1, Article article2) {
        return article1.getDescriptif().compareTo(article2.getDescriptif());
    }


}
