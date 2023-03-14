package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(value = {"startDate", "endDate", "isActive"})
public class DiscountDTO {
    @JsonIgnore
    private Long id;
    private int discount;
    private int fromDayCount;
    private boolean isActive;
    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime endDate;
    private Long channelId;

}
