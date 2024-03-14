package com.example.filRouge.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    final MemberSeeder memberSeeder;
    @Override
    public void run(String... args)  {
         memberSeeder.seedMemberData();
    }

}
