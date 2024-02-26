package com.saara.categoryservice.service.impl;

import com.saara.categoryservice.dto.request.RequestUpdateDto;
import com.saara.categoryservice.dto.response.CommonResponse;
import com.saara.categoryservice.entity.Category;
import com.saara.categoryservice.repository.CategoryRepo;
import com.saara.categoryservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CommonResponse addCategory(String name) throws Exception {
        try {
            Optional<Category> existingCategory = categoryRepo.findByCategoryName(name);
            if (existingCategory.isPresent()) {
                return CommonResponse.builder()
                        .message("Already have this category")
                        .responseCode(HttpStatus.ALREADY_REPORTED)
                        .build();
            } else {
                Category category = Category.builder()
                        .categoryName(name)
                        .build();
                categoryRepo.save(category);
                return CommonResponse.builder()
                        .data(category)
                        .responseCode(HttpStatus.CREATED)
                        .message("Category save success")
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during save category");
        }
    }

    @Override
    public CommonResponse updateCategory(RequestUpdateDto requestUpdateDto) throws Exception {
        try {
            Optional<Category> optionalCategory= categoryRepo.findById(requestUpdateDto.getId());

            if(optionalCategory.isPresent()){
                  Category category = optionalCategory.get();
                  category.setCategoryName(requestUpdateDto.getCategoryName());
                categoryRepo.save(category);
                return CommonResponse.builder()
                        .message("Category update success")
                        .responseCode(HttpStatus.OK)
                        .data(category)
                        .build();
            }
            else {
                return CommonResponse.builder()
                        .responseCode(HttpStatus.NOT_FOUND)
                        .message("Category not found")
                        .build();
            }
        }catch (Exception e){
            throw  new Exception("Error occur during category update");
        }
    }

    @Override
    public CommonResponse getAllCategory() throws Exception {
        try {
            List<Category> categories = categoryRepo.findAll();
            return CommonResponse.builder()
                    .message("Get all category success")
                    .responseCode(HttpStatus.OK)
                    .data(categories)
                    .build();
        }catch (Exception e){
            throw new Exception("Error occur during get all category");
        }
    }

    @Override
    public CommonResponse getCategoryById(Long id) throws Exception {
        try {
            Optional<Category> optionalCategory= categoryRepo.findById(id);
            if(optionalCategory.isPresent()){
                Category category = optionalCategory.get();
                return CommonResponse.builder()
                        .data(category)
                        .responseCode(HttpStatus.OK)
                        .message("Get category by id success")
                        .build();
            }
            else {
                return CommonResponse.builder()
                        .message("Category not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        }catch (Exception e){
             throw new Exception("Error occur during get category by id");
        }
    }

    @Override
    public CommonResponse deleteCategory(Long id) throws Exception {
        try {
            Optional<Category> optionalCategory= categoryRepo.findById(id);
            if(optionalCategory.isPresent()){
                categoryRepo.deleteById(id);
                return CommonResponse.builder()
                        .responseCode(HttpStatus.OK)
                        .message("Category delete success")
                        .build();
            }
            else {
                return CommonResponse.builder()
                        .message("Category not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        }catch (Exception e){
            throw new Exception("Error occur during delete category");
        }

    }

}
