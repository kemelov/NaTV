package kg.natv.TextAd.controllers.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Getter
@Setter
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private List<String> errors;

    public ErrorResponse(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

}
