package com.yuiko.study.api;

import com.yuiko.study.api.response.FlowerPageDto;
import com.yuiko.study.service.FlowerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/flowers", produces = APPLICATION_JSON_VALUE)
public class FlowerController {

    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/{userId}/flowers")
    public FlowerPageDto getFlowers(
            @PathVariable(value = "userId") long uid
    ) {
        return new FlowerPageDto(flowerService.getFlowersByUserId(uid));
    }
}
