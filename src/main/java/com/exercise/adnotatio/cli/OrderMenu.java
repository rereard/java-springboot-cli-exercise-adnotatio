package com.exercise.adnotatio.cli;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exercise.adnotatio.model.Customer;
import com.exercise.adnotatio.model.Delivery;
import com.exercise.adnotatio.model.Order;
import com.exercise.adnotatio.model.OrderDetail;
import com.exercise.adnotatio.model.OrderDetailId;
import com.exercise.adnotatio.model.Product;
import com.exercise.adnotatio.service.CustomerService;
import com.exercise.adnotatio.service.DeliveryService;
import com.exercise.adnotatio.service.OrderService;
import com.exercise.adnotatio.service.ProductService;

@Component
public class OrderMenu {
  @Autowired
  private Scanner userInput;
  
  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProductService productService;

  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private OrderService orderService;

  private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
  
  public void run(){
    System.out.println("\n=== Menu Pemesanan ===");
    Customer selectedCustomer = selectCustomer();
    List<OrderDetail> orderDetails = selectProducts();
    Delivery selectedDelivery = selectDelivery();
    
    System.out.println("\n=== Ringkasan Pesanan ===");
    for (OrderDetail orderDetail : orderDetails) {
      System.out.printf("%s x%d - %s\n", orderDetail.getProduct().getName(), orderDetail.getQuantity(), orderDetail.getFormatedTotalPrice());        
    }
    double subTotal = getSubtotal(orderDetails);
    System.out.printf("\nSubtotal: %s\n", formatter.format(subTotal));
    System.out.printf("Delivery fee: %s\n", formatter.format(selectedDelivery.getCost()));
    double totalAmount = subTotal + selectedDelivery.getCost();
    System.out.printf("Total: %s\n", formatter.format(totalAmount));
    System.out.print("\nApakah ini benar? (Y): ");
      String input = userInput.nextLine();
      if (input.equalsIgnoreCase("Y")) {
        if(totalAmount > selectedCustomer.getBalance()){
          System.out.println("Saldo tidak cukup!");
        } else{
          orderService.orderInsert(new Order(null, LocalDate.now(), null, selectedDelivery.getCost(), totalAmount, selectedDelivery, selectedCustomer), orderDetails);
        }
      } else{
        System.out.println("Pemesanan dibatalkan");
      }
  }

  private double getSubtotal(List<OrderDetail> orderDetails){
    double subTotal = 0;
    for (OrderDetail od : orderDetails) {
      subTotal += od.getTotalPrice();
    }
    return subTotal;
  }

  private Delivery selectDelivery(){
    Delivery selectedDelivery;
    while (true) {
      System.out.println("\nPilih metode pengiriman:");
      for (Delivery delivery : deliveryService.getAllDeliveries()) {
        System.out.println(delivery);
      }
      System.out.print("Masukkan Delivery ID: ");
        int idInput;
        try {
          idInput = Integer.parseInt(userInput.nextLine());
          selectedDelivery = deliveryService.findDeliveryById(idInput);
          return selectedDelivery;
        } catch (NumberFormatException e) {
          System.out.println("Input tidak valid\n");
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
    }
  }

  private List<OrderDetail> selectProducts(){
    List<OrderDetail> orderDetails = new ArrayList<>();
    Product selectedProduct;
    int quantity;
    int idInput;

    while (true) {
      while (true) {
        System.out.println("\nBarang tersedia:");
        for (Product product : productService.getAllProducts()) {
            if(!product.getDeactivated()){
              System.out.printf("[%s] %s - %s - Stock: %d\n", product.getId(), product.getName(), product.getFormatedPrice(), product.getUnitStock());
            }
        }
        System.out.print("Masukkan ID produk untuk ditambahkan ke daftar pemesanan: ");
        try {
          idInput = Integer.parseInt(userInput.nextLine());
          selectedProduct = productService.findProductById(idInput, true);
          break;
        } catch (NumberFormatException e) {
          System.out.println("Input tidak valid\n");
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
  
      while (true) {
        System.out.print("Masukkan kuantitas: ");
        try {
          quantity = Integer.parseInt(userInput.nextLine());
          if(quantity <= 0){
            System.out.println("Tidak boleh kurang dari sama dengan 0!");
            continue;
          } else if (quantity > selectedProduct.getUnitStock()) {
            System.out.println("Kuantitas melebihi stok yang tersedia. Silakan input lagi!");
            continue;
          }
          break;
        } catch (NumberFormatException e) {
          System.out.println("Input tidak valid\n");
        }
      }

      final Integer[] productIdHolder = new Integer[] { selectedProduct.getId() };
      boolean exists = orderDetails.stream().anyMatch(od -> od.getProduct().getId().equals(productIdHolder[0]));

      if(exists){
        System.out.println("Produk sudah ada di daftar pemesanan. Tidak dapat menambahkan kembali");
      } else{
        orderDetails.add(new OrderDetail(new OrderDetailId(null, selectedProduct.getId()), selectedProduct.getPrice(), quantity, selectedProduct, null));
        System.out.println("\nProduk ditambahkan ke daftar pemesanan");
        for (OrderDetail orderDetail: orderDetails) {
          System.out.printf("- %d x %s (%s) %s\n", orderDetail.getQuantity(), orderDetail.getProduct().getName(), orderDetail.getFormatedPrice(), orderDetail.getFormatedTotalPrice());          
        }
      }

      System.out.print("\nTambah produk lain? (Y): ");
      String input = userInput.nextLine();
      if (input.equalsIgnoreCase("Y")) {
        continue;
      }
      return orderDetails;
    }
  }

  private Customer selectCustomer(){
    Customer selectedCustomer;
    while (true) {
      System.out.println("Pilih Customer:");
      for(Customer customer : customerService.getAllCustomers()){
        System.out.printf("[%s] %s %s\n", customer.getId(), customer.getFirstName(), customer.getLastName());
      }
      System.out.print("Masukkan ID Customer: ");
      int idInput;
      try {
        idInput = Integer.parseInt(userInput.nextLine());
        selectedCustomer = customerService.findCustomertById(idInput);
        System.out.printf("\nCustomer dipilih: %s %s - Contact: %s\n", selectedCustomer.getFirstName(), selectedCustomer.getLastName(), selectedCustomer.getContact().getPhoneNumber());
        return selectedCustomer;
      } catch (NumberFormatException e) {
        System.out.println("Input tidak valid\n");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
