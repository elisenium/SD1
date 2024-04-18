import java.util.TreeSet;

public class TestComparable {

	public static void main(String[] args) {

		TreeSet<Article> t = new TreeSet<Article>();

		t.add(new Article(321,"pate 150g",2.75));
		t.add(new Article(123,"salade",1.2));
		t.add(new Article(999,"baguette",0.85));
		t.add(new Article(111,"mais",1.5));

		for (Article article : t) {
			System.out.println(article);
		}	
	}


}
