package com.saara.categoryservice.controller;

import com.saara.categoryservice.dto.request.RequestUpdateDto;
import com.saara.categoryservice.dto.response.CommonResponse;
import com.saara.categoryservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory/{name}")
    public ResponseEntity<CommonResponse> addCategory(@PathVariable("name") String name) throws Exception {

        CommonResponse commonResponse = categoryService.addCategory(name);
        return ResponseEntity.ok(commonResponse);
    }
    @PutMapping("/update")
    public ResponseEntity<CommonResponse> updateCategory(@RequestBody RequestUpdateDto requestUpdateDto) throws Exception {
        CommonResponse commonResponse = categoryService.updateCategory(requestUpdateDto);
        return ResponseEntity.ok(commonResponse);
    }
    @GetMapping("/getAll")
    public ResponseEntity<CommonResponse> getAllCategory() throws Exception {
        CommonResponse commonResponse = categoryService.getAllCategory();
        return ResponseEntity.ok(commonResponse);
    }
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CommonResponse> getCategoryById(@PathVariable("id") Long id) throws Exception {
        CommonResponse commonResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(commonResponse);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteCategory(@PathVariable("id") Long id) throws Exception {
        CommonResponse commonResponse = categoryService.deleteCategory(id);
        return ResponseEntity.ok(commonResponse);
    }
}
