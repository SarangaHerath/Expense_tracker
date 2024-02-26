package com.saara.categoryservice.service;

import com.saara.categoryservice.dto.request.RequestUpdateDto;
import com.saara.categoryservice.dto.response.CommonResponse;

public interface CategoryService {
    CommonResponse addCategory(String name) throws Exception;

    CommonResponse updateCategory(RequestUpdateDto requestUpdateDto) throws Exception;

    CommonResponse getAllCategory() throws Exception;

    CommonResponse getCategoryById(Long id) throws Exception;

    CommonResponse deleteCategory(Long id) throws Exception;
}
