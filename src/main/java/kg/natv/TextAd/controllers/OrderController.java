package kg.natv.TextAd.controllers;

import kg.natv.TextAd.models.DTOs.OrderDTO;
import kg.natv.TextAd.services.OrderService;
import kg.natv.TextAd.validations.Create;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    ResponseEntity<?> save(@Validated(Create.class) @RequestBody OrderDTO orderDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + " - " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        OrderDTO saved = orderService.save(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/findById/{id}")
    OrderDTO findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.findById(id);
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
