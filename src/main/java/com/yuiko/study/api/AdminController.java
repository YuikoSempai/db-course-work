package com.yuiko.study.api;

import java.util.List;

import com.yuiko.study.api.response.EnvironmentPage;
import com.yuiko.study.model.Environment;
import com.yuiko.study.model.enums.FertilizerType;
import com.yuiko.study.model.enums.FlowerSpecies;
import com.yuiko.study.model.enums.SoilType;
import com.yuiko.study.model.enums.WaterType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @PostMapping("/best_env/add")
    public boolean addNewBestEnv(
            @RequestBody Environment environment
    ) {
        return true;
    }

    @GetMapping("/best_env")
    public EnvironmentPage getBestEnvironments() {
        return new EnvironmentPage(List.of(
                new Environment(FlowerSpecies.rose, SoilType.sandy, FertilizerType.organic, WaterType.rainy),
                new Environment(FlowerSpecies.tulip, SoilType.clayey, FertilizerType.ammophos, WaterType.bottled)
        ));
    }
}
