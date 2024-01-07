package com.yuiko.study.api.response;

import com.yuiko.study.model.enums.ResourcesType;
import com.yuiko.study.model.enums.Type;

public record UserResources(
        long userId,
        double amount,
        Type type,
        ResourcesType resourceType
) {


}
