package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kg.natv.TextAd.models.OrderStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"createdDate", "totalPrice", "status"}, allowGetters = true)
public class OrderDTO {
    @JsonIgnore
    private Long id;

    @Size(min = 20, message = "мин. кол-во символов - 20")
    @NotNull(message = "обязательное поле")
    private String text;

    @NotBlank(message = "обязательное поле")
    private String email;
    private String fio;
    private String phone;

    private BigDecimal totalPrice = BigDecimal.valueOf(0);
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private List<ChannelDateDTO> channels;

    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime createdDate;
}
