package com.exercise.adnotatio.cli;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.exercise.adnotatio.model.Product;
import com.exercise.adnotatio.service.ProductService;

@Component
public class ProductMenu {
  private final Scanner userInput;
  private final ProductService productService;

  public ProductMenu(Scanner userInput, ProductService productService) {
      this.userInput = userInput;
      this.productService = productService;
  }

  public void run(){
    while (true){
      System.out.print("""
              \n=== Product Menu ===
              1. Lihat semua produk
              2. Tambah produk baru
              3. Update produk
              4. Delete produk
              5. Kembali ke Main Menu
              Pilihan anda: 
              """);
      int input;
      try {
          input = Integer.parseInt(userInput.nextLine());
      } catch (NumberFormatException e) {
          System.out.println("Input tidak valid");
          continue;
      }
      switch (input){
        case 1 -> showProducts();
        case 2 -> addProduct();
        case 3 -> editProduct();
        case 4 -> deleteProduct();
        case 5 -> {
            return;
        }
        default -> System.out.println("Input salah!");
      }
    }
  }

  public void deleteProduct(){
    Product selectedProduct;
    System.out.print("Masukkan ID produk yang ingin dihapus: ");
    int idInput;
    try {
      idInput = Integer.parseInt(userInput.nextLine());
      selectedProduct = productService.findProductById(idInput);
      System.out.println(selectedProduct);
    } catch (NumberFormatException e) {
      System.out.println("Input tidak valid");
      return;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }
    System.out.print("Apakah anda yakin ingin menghapus produk ini? (Y): ");
    String input = userInput.nextLine();
    if (input.equalsIgnoreCase("Y")) {
      try {
        productService.productDelete(idInput);
      } catch (Exception e) {
        System.out.println("Terjadi error, coba lagi");
      }
    } else{
      System.out.println("Batal hapus");
    }
  }

  public void editProduct(){
    Product selectedProduct;

    System.out.print("Masukkan ID produk yang ingin diupdate: ");
    int idInput;
    try {
      idInput = Integer.parseInt(userInput.nextLine());
      selectedProduct = productService.findProductById(idInput);
      System.out.println("Produk ditemukan: " + selectedProduct.getName());
    } catch (NumberFormatException e) {
      System.out.println("Input tidak valid");
      return;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    Double priceEdit = null;
    System.out.print("Harga produk ini " + selectedProduct.getFormatedPrice() + ". Ubah harga? (Y): ");
    String inputPrice = userInput.nextLine();
    if (inputPrice.equalsIgnoreCase("Y")) {
      while (true) {
        System.out.print("Masukkan harga baru (Rp): ");
        try {
          priceEdit = Double.valueOf(userInput.nextLine());
          if(priceEdit < 0){
            System.out.println("Harga tidak boleh negatif");
            continue;
          }
          break;
        } catch(NumberFormatException e) {
          System.out.println("Masukkan angka!");
        }
      }
    }

    Integer stockEdit = null;
    System.out.print("Stock produk ini " + selectedProduct.getUnitStock() + ". Ubah jumlah stok? (Y): ");
    String inputStock = userInput.nextLine();
    if (inputStock.equalsIgnoreCase("Y")) {
      while (true) {
        System.out.print("Masukkan stok baru (Rp): ");
        try {
          stockEdit = Integer.valueOf(userInput.nextLine());
          if(stockEdit < 0){
            System.out.println("Harga tidak boleh negatif");
            continue;
          }
          break;
        } catch(NumberFormatException e) {
          System.out.println("Masukkan angka!");
        }
      }
    }
    
    try{
      System.out.println("\nMengupdate produk!\n" + productService.productUpdate(idInput, new Product(null, selectedProduct.getName(), priceEdit, stockEdit, false)));
    } catch(Exception e){
      System.out.println("Terjadi error, coba lagi");
    }
  }

  public void addProduct(){
    String name;
    while (true) {
      System.out.print("Masukkan nama produk: ");
      name = userInput.nextLine();
      if(name.isEmpty()){
        System.out.println("Nama produk tidak boleh kosong!");
        continue;
      }
      break;
    }

    double price;
    while (true) {
      System.out.print("Masukkan harga produk (Rp): ");
      try {
        price = Double.parseDouble(userInput.nextLine());
        if(price < 0){
          System.out.println("Harga tidak boleh negatif");
          continue;
        }
        break;
      } catch(NumberFormatException e) {
        System.out.println("Masukkan angka!");
      }
    }

    int stock;
    while (true) {
      System.out.print("Masukkan stok produk: ");
      try {
        stock = Integer.parseInt(userInput.nextLine());
        if(stock < 0){
          System.out.println("Stok tidak boleh negatif");
          continue;
        }
        break;
      } catch(NumberFormatException e) {
        System.out.println("Masukkan angka!");
      }
    }
    
    try{
      System.out.println("\nBerhasil menambahkan produk!\n" + productService.productInsert(new Product(null, name, price, stock, false)));
    } catch(Exception e){
      System.out.println("Terjadi error, coba lagi");
    }
  }

  public void showProducts(){
    int pageNumber = 0;

    while (true) {
      Page<Product> page = productService.getProductsByPage(pageNumber);

      List<Product> products = page.getContent();
      System.out.println("\n=== Page " + (pageNumber + 1) + " of " + page.getTotalPages() + " ===");

      for (int i = 0; i < products.size(); i++) {
          System.out.printf("%s%n", products.get(i));
      }

      if (page.hasNext()) {
          System.out.print("\nLanjut ke halaman berikutnya? (Y): ");
          String input = userInput.nextLine();
          if (input.equalsIgnoreCase("Y")) {
              pageNumber++;
          } else {
              break;
          }
      } else {
          System.out.println("\nAnda sudah di halaman terakhir.");
          System.out.print("Ketik apapun untuk kembali ke menu produk: ");
          userInput.nextLine();
          break;
      }
    }
  }
}
