package pl.lodz.p.edu.rest.dto;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentDTO {
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private int rentCost;
    private boolean archive;
    private ObjectId clientId;
    private ObjectId itemId;

    public RentDTO() {
    }

    public RentDTO(LocalDateTime beginTime, LocalDateTime endTime,
                   int rentCost, boolean archive, ObjectId clientId, ObjectId itemId) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.rentCost = rentCost;
        this.archive = archive;
        this.clientId = clientId;
        this.itemId = itemId;
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

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public ObjectId getItemId() {
        return itemId;
    }

    public void setItemId(ObjectId itemId) {
        this.itemId = itemId;
    }

    public ObjectId getClientId() {
        return clientId;
    }

    public void setClientId(ObjectId clientId) {
        this.clientId = clientId;
    }
}