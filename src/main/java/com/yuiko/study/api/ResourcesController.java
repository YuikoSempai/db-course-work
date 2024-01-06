package com.yuiko.study.api;

import com.yuiko.study.api.response.ResourcesPageDto;
import com.yuiko.study.api.response.UserResources;
import com.yuiko.study.service.ResourcesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/resources", produces = APPLICATION_JSON_VALUE)
public class ResourcesController {

    private final ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping("/{userId}")
    public ResourcesPageDto getResourcesByUser(@PathVariable long userId) {
        return resourcesService.getResourcesByUserId(userId);
    }

    @PostMapping("/{userId}")
    public boolean addResourceForUser(
            @PathVariable long userId,
            @RequestBody UserResources userResources
    ) {
        return resourcesService.addResourcesForUser(userId, userResources);
    }

    @GetMapping("/{userId}/best_environment")
    public ResourcesPageDto getResourcesForBestEnv(
            @PathVariable long userId
    ) {
        return resourcesService.getResourcesByUserId(userId);

    }
}
