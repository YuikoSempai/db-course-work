package com.yuiko.study.api;

import java.util.List;

import com.yuiko.study.api.response.FlowerPageDto;
import com.yuiko.study.model.Flower;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/flowers", produces = APPLICATION_JSON_VALUE)
public class FlowerController {

    @GetMapping("/{id}/flowers")
    public FlowerPageDto getFlowers(
            @PathVariable(value = "id") long id
    ) {
        if (id == 1) {
            return new FlowerPageDto(List.of(new Flower(1, "flower with id 1")));
        }
        if (id == 2) {
            return new FlowerPageDto(List.of(new Flower(2, "flower with id 2")));
        }
        return new FlowerPageDto(List.of(new Flower(0, "-")));
    }
}
