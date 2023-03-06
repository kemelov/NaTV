package kg.natv.TextAd.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive;
    private String logo;
    private double pricePerLetter;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Price> prices;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Ad> ads;

}

