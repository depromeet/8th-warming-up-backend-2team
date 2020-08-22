package com.depromeet.health.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeightResponse {
    @JsonProperty("deadlift")
    Long deadLiftWeight;

    @JsonProperty("bench")
    Long benchWeight;

    @JsonProperty("squat")
    Long squatWeight;

    public WeightResponse(Long deadLiftWeight, Long benchWeight, Long squatWeight) {
        this.deadLiftWeight = deadLiftWeight;
        this.benchWeight = benchWeight;
        this.squatWeight = squatWeight;
    }

    public Long getDeadLiftWeight() {
        return deadLiftWeight;
    }

    public void setDeadLiftWeight(Long deadLiftWeight) {
        this.deadLiftWeight = deadLiftWeight;
    }

    public Long getBenchWeight() {
        return benchWeight;
    }

    public void setBenchWeight(Long benchWeight) {
        this.benchWeight = benchWeight;
    }

    public Long getSquatWeight() {
        return squatWeight;
    }

    public void setSquatWeight(Long squatWeight) {
        this.squatWeight = squatWeight;
    }
}
