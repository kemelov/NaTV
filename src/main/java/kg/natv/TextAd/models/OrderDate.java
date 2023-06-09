package kg.natv.TextAd.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import kg.natv.TextAd.repositories.converter.LocalDateSetConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_order_date")
public class OrderDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Convert(converter = LocalDateSetConverter.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Set<LocalDate> days;

    @OneToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
