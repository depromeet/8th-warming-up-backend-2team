package com.depromeet.health.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Evaluate implements Serializable {
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
