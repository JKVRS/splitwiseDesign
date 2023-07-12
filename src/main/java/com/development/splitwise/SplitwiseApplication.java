package com.development.splitwise;

import com.development.splitwise.command.CommandRegistery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	private Scanner sc;
    private CommandRegistery commandRegistery;

	@Autowired
	public SplitwiseApplication(CommandRegistery commandRegistery){
		sc=new Scanner(System.in);
		this.commandRegistery=commandRegistery;
	}

	public static void main(String[] args) {

		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		while(true)
		{
			System.out.println("What do you want !");
			// to take the input we will use command design pattern
		}

	}
}
