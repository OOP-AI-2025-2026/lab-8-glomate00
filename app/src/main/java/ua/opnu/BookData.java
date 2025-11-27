package ua.opnu;

import java.util.Locale;

class BookData implements Comparable<BookData> {
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
        if (reviews == 0) {
            return 0.0;
        }
        return total / reviews;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getReviews() {
        return reviews;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public int compareTo(BookData other) {
        double thisRating = this.getRating();
        double otherRating = other.getRating();

        int cmp = Double.compare(thisRating, otherRating);
        if (cmp != 0) {
            return -cmp;
        }

        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "BookData{title='%s', author='%s', rating=%.2f}",
                title, author, getRating());
    }
}
