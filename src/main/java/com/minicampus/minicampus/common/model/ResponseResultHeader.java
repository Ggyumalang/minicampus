package com.minicampus.minicampus.common.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResultHeader {
    Boolean result;
    String message;

    public ResponseResultHeader(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseResultHeader(boolean result) {
        this.result = result;
    }
}
