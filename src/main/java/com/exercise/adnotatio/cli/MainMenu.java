package com.exercise.adnotatio.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class MainMenu {
  private final Scanner userInput;
  private final ProductMenu productMenu;
  private final OrderMenu orderMenu;

  public MainMenu(Scanner userInput, ProductMenu productMenu, OrderMenu orderMenu) {
      this.userInput = userInput;
      this.productMenu = productMenu;
      this.orderMenu = orderMenu;
  }
  
  public void run(){
    while (true){
      System.out.print("""
              \n=== Main Menu ===
              1. Pengaturan Produk
              2. Pemesanan
              4. Tutup Aplikasi
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
        case 1 -> productMenu.run();
        case 2 -> orderMenu.run();
        case 4 -> {
            System.out.println("Terima kasih telah menggunakan aplikasi ini.");
            System.exit(1);
        }
        default -> System.out.println("Input salah");
      }
    }
  } 
}
