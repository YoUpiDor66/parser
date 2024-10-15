import java.util.List;
import java.util.ArrayList;

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Library:\n");
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private Price price;
    private String isbn;
    private String format;
    private Publisher publisher;
    private List<Review> reviews;
    private String language;
    private String translator;
    private List<String> awards;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getTranslator() {
        return translator;
    }
    public void setTranslator(String translator) {
        this.translator = translator;
    }
    public List<String> getAwards() {
        return awards;
    }
    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public Book() {
        this.reviews = new ArrayList<>();
        this.awards = new ArrayList<>();
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addAward(String award) {
        this.awards.add(award);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", format='" + format + '\'' +
                ", publisher=" + publisher +
                ", reviews=" + reviews +
                ", language='" + language + '\'' +
                ", translator='" + translator + '\'' +
                ", awards=" + awards +
                '}';
    }
}

class Price {
    private double amount;

    private String currency;

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}

class Publisher {
    private String name;

    private Address address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address {
    private String city;

    private String country;

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Review {
    private String user;
    private int rating;
    private String comment;

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "user='" + user + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
