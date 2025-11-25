package ua.opnu.Task2;

public class BookData implements Comparable<BookData> {
    private String title;
    private String author;
    private int reviews;
    private double total;

    public BookData(String title, String author, int reviews, double total) {
        this.title = title;
        this.author = author;
        this.reviews = reviews;
        this.total = total;
    }

    public double getRating() {
        return reviews > 0 ? total / reviews : 0.0;
    }

    @Override
    public int compareTo(BookData other) {
        int ratingComparison = Double.compare(other.getRating(), this.getRating());
        if (ratingComparison == 0) {
            return this.title.compareTo(other.title);
        }
        return ratingComparison;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getReviews() { return reviews; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format("BookData{title='%s', author='%s', rating=%.2f}", 
                           title, author, getRating());
    }
}
