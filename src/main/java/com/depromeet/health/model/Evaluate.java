package com.depromeet.health.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Evaluate {
    @EmbeddedId
    EvaluateId id;

    public Evaluate() {

    }

    public Evaluate(EvaluateId id) {
        this.id = id;
    }

    public EvaluateId getId() {
        return id;
    }

    public void setId(EvaluateId id) {
        this.id = id;
    }
}
