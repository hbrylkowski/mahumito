package com.brylkowski.mahumito.mahumito.models;

import java.io.Serializable;

public class Beacon implements Serializable{
    protected Integer id;
    protected Integer creator;
    protected Integer current_owner;
    protected String name;
    protected String description;
    protected String mission_image;
    protected String current_task;
    protected String next_task;
    protected String final_task;
    protected String pending_claim_user;
    protected String beacon_unique_id;

    public Beacon(
        Integer id,
        Integer creator,
        Integer current_owner,
        String name,
        String description,
        String mission_image,
        String current_task,
        String next_task,
        String final_task,
        String beacon_unique_id
    ) {
        this.id = id;
        this.creator = creator;
        this.current_owner = current_owner;
        this.name = name;
        this.description = description;
        this.mission_image = mission_image;
        this.pending_claim_user = pending_claim_user;
        this.beacon_unique_id = beacon_unique_id;
    }

    public Integer id() {
        return id;
    }

    public Integer creator() {
        return creator;
    }

    public Integer currentOwner() {
        return current_owner;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String missionImage() {
        return mission_image;
    }

    public String currentTask() {
        return current_task;
    }

    public String nextTask() {
        return next_task;
    }

    public String finalTask() {
        return final_task;
    }

    public String pendingClaimUser() {
        return pending_claim_user;
    }

    public String beaconUniqueId() {
        return beacon_unique_id;
    }
}
