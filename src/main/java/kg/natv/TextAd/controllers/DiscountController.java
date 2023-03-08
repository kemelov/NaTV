package kg.natv.TextAd.controllers;

import io.swagger.annotations.ApiOperation;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @ApiOperation("Поиск скидки по ID")
    @GetMapping("/find/{id}")
    public DiscountDTO findById(@PathVariable Long id) {
        return discountService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DiscountDTO discountDTO){
        DiscountDTO saved = discountService.save(discountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        discountService.findById(id);
        discountService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long discountId,@RequestBody DiscountDTO discountDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.update(discountId, discountDTO));
    }

    @PutMapping("/deactivate/{discountId}")
    public ResponseEntity<?> deactivate(@PathVariable Long discountId){
        DiscountDTO deactivated = discountService.deactivate(discountId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deactivated);
    }
}
