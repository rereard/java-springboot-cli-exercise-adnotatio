package com.exercise.adnotatio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exercise.adnotatio.model.Product;
import com.exercise.adnotatio.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  private static final int PAGE_SIZE = 5;

  public Page<Product> getProductsByPage(int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("id"));
    return productRepository.findAll(pageable);
  }

  public void productDelete(Integer id){
    if(id == null){
      throw new RuntimeException("ID tidak boleh null");
    }
    if(!productRepository.existsById(id)){
      throw new RuntimeException("Produk dengan ID " + id + " tidak ditemukan");
    }
    productRepository.deleteById(id);
    System.out.println("Produk dengan ID " + id + " berhasil dihapus");
  }

  public Product productInsert(Product product){
    product.setId(null);
    return productRepository.save(product);
  }

  public Product productUpdate(Integer id, Product product){
    Product selectedProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product dengan id " + id + " tidak ditemukan"));
    if(product.getPrice() != null){
      selectedProduct.setPrice(product.getPrice());
    }
    if(product.getUnitStock() != null){
      selectedProduct.setUnitStock(product.getUnitStock());
    }
    productRepository.save(selectedProduct);
    return selectedProduct;
  }

  public Product findProductById(Integer id){
    return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product dengan ID "+ id + " tidak ditemukan"));
  }

}
