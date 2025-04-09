package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    // public static HttpServletRequest getCurrentHttpRequest() {
    // RequestAttributes requestAttributes =
    // RequestContextHolder.getRequestAttributes();
    // if (requestAttributes instanceof ServletRequestAttributes) {
    // return ((ServletRequestAttributes) requestAttributes).getRequest();
    // }
    // return null;
    // }
}