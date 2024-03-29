package com.yuiko.study.api;

import java.util.List;

import com.yuiko.study.api.response.CheckedFlowerResponse;
import com.yuiko.study.api.response.FlowerPageDto;
import com.yuiko.study.api.response.Statistic;
import com.yuiko.study.api.response.StatisticPage;
import com.yuiko.study.api.response.WaterPageDto;
import com.yuiko.study.model.Flower;
import com.yuiko.study.service.FlowerDbService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/flowers", produces = APPLICATION_JSON_VALUE)
public class FlowerController {

    private final FlowerDbService flowerDbService;

    public FlowerController(FlowerDbService flowerDbService) {
        this.flowerDbService = flowerDbService;
    }

    @GetMapping("/{userId}/flowers")
    public FlowerPageDto getFlowers(
            @PathVariable(value = "userId") long uid
    ) {
        return new FlowerPageDto(flowerDbService.getFlowersByUserId(uid));
    }

    @PostMapping("/{userId}/flowers/add")
    public boolean addFlower(
            @PathVariable(value = "userId") long uid,
            @RequestBody Flower flower
    ) {
        return flowerDbService.addFlower(uid, flower);
    }

    @DeleteMapping("/{userId}/flowers")
    public boolean deleteFlower(
            @PathVariable(value = "userId") long uid,
            @RequestParam("flowerId") Long flowerId
    ) {
        return flowerDbService.deleteFlower(uid, flowerId);
    }

    @PatchMapping("/{userId}/flowers")
    public boolean updateFlower(
            @PathVariable(value = "userId") long uid,
            @RequestBody Flower flower
    ) {
        return flowerDbService.updateFLower(
                uid,
                flower
        );
    }

    @GetMapping("/{userId}/flowers/check")
    public CheckedFlowerResponse checkFlowerEnv(@PathVariable long userId) {
        return new CheckedFlowerResponse(flowerDbService.checkFlowerEnv(userId));
    }

    @GetMapping("/statistic")
    public StatisticPage getStatistic() {
        return new StatisticPage(List.of(flowerDbService.getStatistic()));
    }

    @GetMapping("/{userId}/flowers/water")
    public WaterPageDto getWateringSchedule(@PathVariable long userId) {
        return new WaterPageDto(flowerDbService.getWateringSchedule(userId));
    }

    @GetMapping("/{userId}/flowers/water/flowers")
    public boolean getIsFlowerWatered(
            @PathVariable(value = "userId") long uid,
            @RequestParam(value = "flowerId") Long flowerId
    ) {
        return flowerDbService.waterFlower(uid, flowerId);
    }
}
