// File: EntertainmentHub.java

class Entertainment {
    protected String title;

    public Entertainment(String title) {
        this.title = title;
    }

    public void start() {
        System.out.println("Starting " + title);
    }

    public void stop() {
        System.out.println("Stopping " + title);
    }
}

class Movie extends Entertainment {
    private String genre;

    public Movie(String title, String genre) {
        super(title);
        this.genre = genre;
    }

    public void showSubtitles() {
        System.out.println("Showing subtitles for " + title + " (" + genre + ")");
    }

    public void adjustQuality() {
        System.out.println("Adjusting video quality for " + title);
    }
}

class Game extends Entertainment {
    private String platform;

    public Game(String title, String platform) {
        super(title);
        this.platform = platform;
    }

    public void saveProgress() {
        System.out.println("Saving " + title + " progress on " + platform);
    }

    public void showLeaderboard() {
        System.out.println(title + " leaderboard on " + platform);
    }
}

public class EntertainmentHub {
    public static void main(String[] args) {
        System.out.println("--- Playing a Movie ---");
        Entertainment myMedia = new Movie("Avengers", "Action");
        myMedia.start();
        Movie myMovie = (Movie) myMedia;
        myMovie.showSubtitles();
        myMovie.adjustQuality();

        System.out.println("\n--- Playing a Game ---");
        myMedia = new Game("FIFA 24", "PlayStation");
        myMedia.start();
        Game myGame = (Game) myMedia;
        myGame.saveProgress();
        myGame.showLeaderboard();

        System.out.println("\n--- Demonstrating Wrong Downcast ---");
        Entertainment anotherMedia = new Movie("Inception", "Sci-Fi");
        try {
            Game wrongGame = (Game) anotherMedia;
            wrongGame.saveProgress();
        } catch (ClassCastException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Cannot cast a Movie object to a Game reference!");
        }
    }
}