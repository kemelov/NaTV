package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.Order;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class AdDTO {
    private Long id;
    private String text;
    private int symbolCount;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime endDate;
    private Order order;
    private Channel channel;
}
