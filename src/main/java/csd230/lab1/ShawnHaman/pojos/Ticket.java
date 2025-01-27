package csd230.lab1.ShawnHaman.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.ShawnHaman.entities.Ticket}
 */
public class Ticket extends CartItem {
    private String text;
    public Ticket() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void sellItem() {
        System.out.println(getDescription());
    }


}