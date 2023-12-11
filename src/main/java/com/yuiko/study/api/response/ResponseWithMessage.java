package com.yuiko.study.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWithMessage {
    int status;
    String message;

    public ResponseWithMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
