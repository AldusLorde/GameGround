package aldus.model.beans;

public class Order {
    private int idOrder= 0;
    private User user;
    private Game game;
    private int price;
    private boolean accepted;

    public Order() {
    }

    public Order(User user, Game game, int price, boolean accepted){
        this.user = user;
        this.game = game;
        this.price = price;
        this.accepted = accepted;
    }

    public Order(int idOrder, User user, Game game, int price, boolean accepted) {
        this(user,game,price,accepted);
        this.idOrder = idOrder;

    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
