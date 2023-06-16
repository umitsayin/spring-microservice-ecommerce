package com.turkcell.inventoryservice.service.impl;

import com.turkcell.commonservice.config.exception.EntityNotFoundException;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.inventoryservice.dto.request.CategoryRequest;
import com.turkcell.inventoryservice.dto.response.CategoryResponse;
import com.turkcell.inventoryservice.model.Category;
import com.turkcell.inventoryservice.repository.CategoryRepository;
import com.turkcell.inventoryservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryServiceRules rules;

    public CategoryServiceImpl(CategoryRepository repository, CategoryServiceRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryResponse> response = repository.findAll().stream().map(CategoryResponse::convert).toList();

        return response;
    }

    @Override
    public CategoryResponse getById(UUID id) {
        Category category = getCategory(id);
        CategoryResponse response = CategoryResponse.convert(category);

        return response;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        rules.checkIfCategoryExists(request.getName());

        Category category = new Category();
        category.setName(request.getName());

        CategoryResponse response = CategoryResponse.convert(repository.save(category));

        return response;
    }

    @Override
    public CategoryResponse updateById(UUID id, CategoryRequest request) {
        Category category = getCategory(id);
        category.setName(request.getName());

        CategoryResponse response = CategoryResponse.convert(repository.save(category));

        return response;
    }

    @Override
    public void deleteById(UUID id) {
        Category category = getCategory(id);
        repository.delete(category);
    }

    private Category getCategory(UUID id){
        return  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CommonConstant.Exception.CATEGORY_NOT_FOUND));
    }
}
