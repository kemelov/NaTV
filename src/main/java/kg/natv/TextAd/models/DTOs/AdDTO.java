package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kg.natv.TextAd.validations.Create;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@JsonIgnoreProperties(value = {"id", "channelName", "symbolCount", "endDate", "startDate", "daysCount"}, allowGetters = true)
public class AdDTO {
    private Long id;

    @Size(min = 20, message = "мин. кол-во символов - 20")
    @NotNull(message = "обязательное поле", groups = Create.class)
    private String text;

//    @NotBlank(message = "обязательное поле")
//    private Order order;
    private Long daysCount;

    @NotNull(message = "обязательное поле", groups = Create.class)
    private Long channelId;

    private transient String channelName;

    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime endDate;
    private int symbolCount;
    private OrderDateDTO orderDateDTO;
}
