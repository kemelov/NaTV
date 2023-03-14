package kg.natv.TextAd.controllers;

import io.swagger.annotations.ApiOperation;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.services.ChannelService;
import kg.natv.TextAd.validations.Create;
import net.bytebuddy.pool.TypePool;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService){
        this.channelService = channelService;
    }


    @ApiOperation("Создание канала в БД")
    @PostMapping("/save")
    ResponseEntity<?> save(@Validated(Create.class) @RequestBody ChannelDTO channelDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + " - " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        ChannelDTO saveChannelDTO = channelService.save(channelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveChannelDTO);
    }

    @ApiOperation("Расчет стоимости рекламы на определенном канале")
    @GetMapping("/calculate")
    public ResponseEntity<?> calculate(@Validated(Create.class) @RequestBody PriceDTO priceDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + " - " + error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(channelService.calculate(priceDTO));
    }

    @ApiOperation("Поиск канала по ID")
    @GetMapping("/findById/{id}")
    public ChannelDTO findById(@PathVariable Long id) {
        return channelService.findById(id);
    }

    @ApiOperation("Все каналы")
    @GetMapping("/findAll")
    public List<ChannelDTO> findAll() {
        return channelService.findAll();
    }

    @ApiOperation("Все активные каналы")
    @GetMapping("/list")
    public List<ChannelDTO> findByActiveTrue() {
        return channelService.findByActiveTrue();
    }


    @ApiOperation("Редактирование данных канала по ID")
    @PatchMapping("/update/{channelId}")
    public ResponseEntity<?> update(@PathVariable Long channelId,@RequestBody ChannelDTO channelDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(channelService.update(channelId, channelDTO));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        channelService.findById(id);
        channelService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
