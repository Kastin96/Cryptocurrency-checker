package com.example.checker.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    @OneToOne
    @JoinColumn(name = "cryptocurrency_id")
    private Cryptocurrency cryptocurrency;
    private double oldPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.oldPrice, oldPrice) == 0 && Objects.equals(username, user.username) &&
                Objects.equals(cryptocurrency, user.cryptocurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, cryptocurrency, oldPrice);
    }
}
