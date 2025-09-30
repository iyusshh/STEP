class Artwork {
    String title;
    Artwork(String t){ title = t; }
}
class Painting extends Artwork {
    String technique;
    Painting(String t,String tech){ super(t); technique=tech; }
    void showPainting(){ System.out.println(title + " uses technique: " + technique); }
}
class Sculpture extends Artwork {
    String material;
    Sculpture(String t,String m){ super(t); material=m; }
    void showSculpture(){ System.out.println(title + " made of: " + material); }
}

public class Problem5_DigitalArtGallery {
    public static void main(String[] args) {
        Artwork art = new Painting("Mona Lisa", "Oil on Canvas");
        if (art instanceof Painting) {
            Painting p = (Painting) art; // downcasting
            p.showPainting();
        }
    }
}
