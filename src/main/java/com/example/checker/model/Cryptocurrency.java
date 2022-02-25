package com.example.checker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocurrency {
    @Id
    private long id;
    private String name;
    private String symbol;
    @JsonProperty("price_usd")
    private double priceUsd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cryptocurrency)) return false;
        Cryptocurrency that = (Cryptocurrency) o;
        return id == that.id && Double.compare(that.priceUsd, priceUsd) == 0 && Objects.equals(name, that.name) && Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, priceUsd);
    }
}
