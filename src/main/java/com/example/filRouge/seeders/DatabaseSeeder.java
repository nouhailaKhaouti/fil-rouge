package com.example.filRouge.seeders;

import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Member;
import com.example.filRouge.entities.Module;
import com.example.filRouge.entities.Role;
import com.example.filRouge.service.concourService.concourService;
import com.example.filRouge.service.memberService.MemberService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    final MemberSeeder memberSeeder;
    final DepartementSeeder departementSeeder;
    final FiliereSeeder filiereSeeder;
    final ConcourSeeder concourSeeder;
    final concourService concourService;
    final MemberService memberService;
    @Override
    public void run(String... args) throws MessagingException, IOException {
         memberSeeder.seedMemberData();
         departementSeeder.seedDepartements();
         filiereSeeder.seedFilieres();
         concourSeeder.seedConcours();
/*
        Member member= Member.builder().num(1237832).role(Role.MANAGER).password("Baouboula").email("jaouralive70@gmail.com").accountApproved(true).name("nouhaila").build();
        memberService.create(member);*/

/*        List<Module> moduleList = new ArrayList<>();
        moduleList.add(Module.builder().reference("test").build());
        concourService.saveConcourComplet(Concour.builder().reference("1236").modules(moduleList).build());*/


    }

}
