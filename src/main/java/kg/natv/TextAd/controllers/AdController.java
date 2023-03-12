package kg.natv.TextAd.controllers;

import io.swagger.annotations.ApiOperation;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.services.AdService;
import kg.natv.TextAd.validations.Create;
import kg.natv.TextAd.validations.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ad")
public class AdController{
    private final AdService adService;
    public AdController(AdService adService){
        this.adService = adService;
    }

    @ApiOperation("Создание рекламы в БД")
    @PostMapping("/save")
    ResponseEntity<?> save(@Validated(Create.class) @RequestBody AdDTO adDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        AdDTO saveAdDTO = adService.save(adDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveAdDTO);
    }

    @ApiOperation("Поиск рекламы по ID")
    @GetMapping("/findById/{id}")
    public AdDTO findById(@PathVariable Long id) {
        return adService.findById(id);
    }

    @ApiOperation("Редактирование данных канала по ID")
    @PatchMapping("/update/{adId}")
    public ResponseEntity<?> update(@PathVariable Long adId, @Validated(Update.class) @RequestBody AdDTO adDTO,
                                    BindingResult bindingResult) throws IOException
    {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.status(HttpStatus.OK).body(adService.update(adId, adDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        adService.findById(id);
        adService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
