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


    // Override toString
    @Override
    public String toString() {
        return "Ticket{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }

    // Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getText(), ticket.getText());
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getText());
    }
}