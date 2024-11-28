package pl.lodz.p.edu.mvc.model;

import org.bson.types.ObjectId;
import pl.lodz.p.edu.mvc.model.item.Item;
import pl.lodz.p.edu.mvc.model.user.Client;

import java.time.LocalDateTime;

public class Rent {
    private ObjectId id;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private int rentCost;
    private boolean archive = false;
    private Client client;
    private Item item;

    public Rent(LocalDateTime beginTime, int rentCost, Client client, Item item) {
        this.beginTime = beginTime;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public Rent() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public long getRentDays() {
        return java.time.Duration.between(beginTime, endTime).toDays();
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getRentInfo() {
        return "Rent ID: " + id + ", Client: " + client.getFirstName() + ", Item: " + item.getItemName();
    }
}
