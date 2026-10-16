package com.example.lap51;

public class TextNote extends Note {
    private String content;

    public TextNote(String title, User user, String content) {
        super(title, user);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getNoteType() {
        return "Text Note";
    }

    @Override
    public String getDetails() {
        return content;
    }
}
