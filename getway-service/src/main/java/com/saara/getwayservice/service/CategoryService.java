package com.saara.getwayservice.service;

import com.saara.getwayservice.dto.request.RequestUpdateDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<CommonResponse> saveCategory(String name) throws Exception;

    ResponseEntity<CommonResponse> updateCategory(RequestUpdateDto requestUpdateDto) throws Exception;

    ResponseEntity<CommonResponse> getAll() throws Exception;

    CommonResponse getCategoryById(Long id) throws Exception;

    ResponseEntity<CommonResponse> deleteCategory(Long id) throws Exception;
}
