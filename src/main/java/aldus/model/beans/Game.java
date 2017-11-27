package aldus.model.beans;

public class Game {
    private int id = 0;
    private Developer developer;
    private Genre genre;
    private String name;
    private int year;
    private String description;
    private int price;
    private int discount;
    private String image;

    public Game(Developer developer, Genre genre, String name, int year, String description, int price, int discount, String image) {
        this.developer = developer;
        this.genre = genre;
        this.name = name;
        this.year = year;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.image = image;
    }

    public Game(int id, Developer developer, Genre genre, String name, int year, String description, int price, int discount, String image) {
        this(developer,genre,name,year,description,price,discount,image);
        this.id = id;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
