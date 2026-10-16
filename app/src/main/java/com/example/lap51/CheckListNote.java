package com.example.lap51;

import java.util.List;

public class CheckListNote extends Note {
    private List<String> items;

    public CheckListNote(String title, User user, List<String> items) {
        super(title, user);
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String getNoteType() {
        return "CheckList Note";
    }

    @Override
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        if (items != null) {
            for (String item : items) {
                if (!item.trim().isEmpty()) {
                    sb.append("☑ ").append(item.trim()).append("\n");
                }
            }
        }
        return sb.toString().trim();
    }
}
