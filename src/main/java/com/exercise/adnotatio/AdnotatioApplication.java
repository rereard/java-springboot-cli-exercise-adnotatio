package com.exercise.adnotatio;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exercise.adnotatio.cli.MainMenu;

@SpringBootApplication
public class AdnotatioApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(AdnotatioApplication.class, args);
		context.getBean(MainMenu.class).run();
	}

	@Bean
	public Scanner userInput(){
		return new Scanner(System.in);
	}

}
