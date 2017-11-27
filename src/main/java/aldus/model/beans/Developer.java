package aldus.model.beans;

public class Developer {
    private int id = 0;
    private String developer;

    public Developer(String developer){
        this.developer = developer;
    }

    public Developer(int id, String developer) {
        this(developer);
        this.id = id;
    }

    public Developer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
