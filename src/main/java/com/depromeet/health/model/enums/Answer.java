package com.depromeet.health.model.enums;

public enum Answer {
    a("a"),b("b"),c("c"),d("d");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
