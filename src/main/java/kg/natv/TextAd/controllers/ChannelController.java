package kg.natv.TextAd.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.services.ChannelService;
import kg.natv.TextAd.validations.Create;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/channel")
public class ChannelController {
    private final ChannelService channelService;
    private final ObjectMapper objectMapper;

    public ChannelController(ChannelService channelService, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
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

    @ApiOperation("Поиск канала по ID")
    @GetMapping("/findById")
    public ChannelDTO findById(@RequestParam Long id) {
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
    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id,@RequestBody ChannelDTO channelDTO) throws IOException {
        String json = objectMapper.writeValueAsString(channelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(channelService.update(id, json));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){
        channelService.findById(id);
        channelService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
