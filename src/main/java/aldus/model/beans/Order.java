package aldus.model.beans;

import java.sql.Timestamp;

public class Order {
    private int idOrder= 0;
    private User user;
    private Game[] games;
    private double price;
    private boolean accepted;
    private Timestamp time;

    public Order() {
    }

    public Order(User user, Game[] game, double price, boolean accepted, Timestamp time){
        this.user = user;
        this.games = game;
        this.price = price;
        this.accepted = accepted;
        this.time = time;
    }

    public Order(int idOrder, User user, Game[] game, double price, boolean accepted, Timestamp time) {
        this(user,game,price,accepted, time);
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

    public Game[] getGame() {
        return games;
    }

    public void setGame(Game[] game) {
        this.games = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
