package com.turkcell.inventoryservice.service.impl;

import com.turkcell.commonservice.dto.response.DocumentResponse;
import com.turkcell.inventoryservice.api.client.DocumentClient;
import com.turkcell.inventoryservice.dto.request.ProductRequest;
import com.turkcell.inventoryservice.dto.response.CategoryResponse;
import com.turkcell.inventoryservice.dto.response.ProductResponse;
import com.turkcell.inventoryservice.model.Category;
import com.turkcell.inventoryservice.model.Product;
import com.turkcell.inventoryservice.repository.ProductRepository;
import com.turkcell.inventoryservice.service.CategoryService;
import com.turkcell.inventoryservice.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final DocumentClient documentClient;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repository, DocumentClient documentClient, CategoryService categoryService) {
        this.repository = repository;
        this.documentClient = documentClient;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products =  repository.findAll();
        List<ProductResponse> response = new ArrayList<>();

        for(Product product : products){
            List<CategoryResponse> categoryResponse =
                    product.getCategories().stream().map(CategoryResponse::convert).toList();
            DocumentResponse documentResponse = documentClient.getDocumentsByProductId(product.getId().toString());

            response.add(ProductResponse.convert(product, categoryResponse, documentResponse.documents()));
        }

        return response;
    }

    @Override
    public ProductResponse getById(UUID id) {
        Product product = getProductById(id);
        List<CategoryResponse> categoryResponse = product.getCategories().stream().map(CategoryResponse::convert).toList();
        DocumentResponse documentResponse = documentClient.getDocumentsByProductId(product.getId().toString());

        ProductResponse response = ProductResponse.convert(product, categoryResponse, documentResponse.documents());

        return response;
    }

    @Override
    public ProductResponse create(MultipartFile[] files, ProductRequest request) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        request.getCategoryId().forEach(categoryId -> categoryResponses.add(categoryService.getById(categoryId)));

        Product product = new Product();
        fillProduct(request,categoryResponses,product);

        Product saveProduct = repository.save(product);
        List<String> documents = getDocuments(files, saveProduct.getId().toString());

        ProductResponse response = ProductResponse.convert(product,categoryResponses,documents);

        return response;
    }

    @Override
    public ProductResponse update(UUID id, ProductRequest request) {
        Product product = getProductById(id);
        fillProduct(request,new ArrayList<>(),product);
        repository.save(product);

        return getById(id);
    }

    @Override
    public void deleteById(UUID id) {
        Product product = getProductById(id);

        repository.delete(product);
    }

    private Product getProductById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    private List<String> getDocuments(MultipartFile[] files, String productId){
        DocumentResponse documentResponse = documentClient.create(files, productId);

        return documentResponse.documents();
    }

    private void fillProduct(ProductRequest request, List<CategoryResponse> categoryResponses, Product product){
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());

        categoryResponses.forEach(item -> {
            Category category = new Category();
            category.setId(item.id());

            product.getCategories().add(category);
        });
    }
}
