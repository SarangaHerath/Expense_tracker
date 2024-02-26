package com.saara.getwayservice.controller;

import com.saara.getwayservice.dto.request.LoginRequestDto;
import com.saara.getwayservice.dto.request.RegisterRequestDto;
import com.saara.getwayservice.dto.request.RequestUpdateDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/v1/category-gate")
@RestController
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("saveCategory/{name}")
    public ResponseEntity<CommonResponse> saveCategory(@PathVariable("name") String name) throws Exception {
              log.info("hit category-gate save method, category name: {} ",name);
              ResponseEntity<CommonResponse> commonResponse = categoryService.saveCategory(name);
              return commonResponse;
    }
    @PutMapping("update")
    public ResponseEntity<CommonResponse> updateCategory(@RequestBody RequestUpdateDto requestUpdateDto) throws Exception {
        log.info("hit category-gate login method, dto: {} ",requestUpdateDto);
        ResponseEntity<CommonResponse> commonResponse = categoryService.updateCategory(requestUpdateDto);
        return commonResponse;
    }
    @GetMapping("getAll")
    public ResponseEntity<CommonResponse> getAllCategory() throws Exception {
        log.info("hit category-gate get all user method");
        ResponseEntity<CommonResponse> commonResponse = categoryService.getAll();
        return commonResponse;
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<CommonResponse> getCategoryById(@PathVariable("id") Long id) throws Exception {
        CommonResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteCategory/{id}")
    public ResponseEntity<CommonResponse> deleteCategory(@PathVariable("id") Long id) throws Exception {
        ResponseEntity<CommonResponse> response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response.getBody());
    }
}
