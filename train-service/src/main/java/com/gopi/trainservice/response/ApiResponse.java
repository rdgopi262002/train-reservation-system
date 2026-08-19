package com.gopi.trainservice.response;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data,
        Map<String, List<String>> errors) {
}
