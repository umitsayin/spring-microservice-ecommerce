package com.turkcell.inventoryservice.service.impl;

import com.turkcell.commonservice.config.exception.EntityAlreadyException;
import com.turkcell.commonservice.util.constant.CommonConstant;
import com.turkcell.inventoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceRules {
    private final CategoryRepository repository;

    public CategoryServiceRules(CategoryRepository repository) {
        this.repository = repository;
    }

    public void checkIfCategoryExists(String name){
        if(repository.existsByName(name))
            throw new EntityAlreadyException(CommonConstant.Exception.CATEGORY_ALREADY_EXISTS);
    }
}
