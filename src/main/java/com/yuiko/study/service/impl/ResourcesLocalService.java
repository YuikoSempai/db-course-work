package com.yuiko.study.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yuiko.study.api.response.UserResources;
import com.yuiko.study.api.response.ResourcesPageDto;
import com.yuiko.study.model.enums.ResourcesType;
import com.yuiko.study.model.enums.Type;
import com.yuiko.study.service.ResourcesService;
import org.springframework.stereotype.Service;

@Service
public class ResourcesLocalService implements ResourcesService {

    private final List<UserResources> userResourcesList = new ArrayList<>();

    public ResourcesLocalService() {
        userResourcesList.addAll(List.of(
                new UserResources(
                        100,
                        1.0,
                        Type.soil,
                        ResourcesType.clay
                ),
                new UserResources(
                        100,
                        10.0,
                        Type.water,
                        ResourcesType.bottled
                ),
                new UserResources(
                        100,
                        200.5,
                        Type.fertilizer,
                        ResourcesType.organic
                )
        ));
    }

    @Override
    public ResourcesPageDto getResourcesByUserId(long uid) {
        return new ResourcesPageDto(
                userResourcesList.stream().filter(res -> res.userId() == uid).toList()
        );
    }

    @Override
    public boolean addResourcesForUser(long uid, UserResources userResources) {
        return userResourcesList.add(userResources);
    }
}
