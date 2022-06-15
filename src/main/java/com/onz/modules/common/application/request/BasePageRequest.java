package com.onz.modules.common.application.request;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BasePageRequest {

    protected int size = 10;

    protected int page = 0;

    public Pageable getPageable() {
        return PageRequest.of(page, size);
    }
}
