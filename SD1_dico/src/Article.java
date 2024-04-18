import java.util.Objects;

public class Article implements Comparable<Article>{

    private int codeBarre;
    private String descriptif;
    private double prix;

    public Article(int codeBarre, String descriptif, double prix) {
        this.codeBarre = codeBarre;
        this.descriptif = descriptif;
        this.prix = prix;
    }

    public int getCodeBarre() {
        return codeBarre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return ""+ codeBarre +
                "\t " + descriptif + "\t " + prix ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return codeBarre == article.codeBarre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeBarre);
    }

    @Override
    public int compareTo(Article autreArticle) {
        return codeBarre- autreArticle.codeBarre;
    }
}
