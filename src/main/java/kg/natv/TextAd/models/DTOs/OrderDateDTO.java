package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import kg.natv.TextAd.validations.Create;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class OrderDateDTO {
    @JsonIgnore
    private Long id;

    @ApiModelProperty(example = "[\"01-01-2023\"]")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "обязательное поле", groups = Create.class)
    private List<LocalDate> days;
    @JsonIgnore
    private Long adId;
}
