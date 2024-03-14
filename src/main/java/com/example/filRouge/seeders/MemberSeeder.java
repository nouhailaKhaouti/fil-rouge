package com.example.filRouge.seeders;

import com.example.filRouge.Repository.MemberRepository;
import com.example.filRouge.entities.Member;
import com.example.filRouge.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberSeeder {
    private final MemberRepository memberRepository;

     void seedMemberData() {
        if (memberRepository.count() == 0) {

            List<Member> memberList = new ArrayList<>();

            memberList.add(Member.builder().num(1).name("John").familyName("Doe").accessionDate(LocalDate.of(2022, 1, 1))
                    .nationality("USA").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("123456").accountApproved(false).email("John@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(2).name("Jane").familyName("Smith").accessionDate(LocalDate.of(2022, 1, 2))
                    .nationality("UK").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("789012").accountApproved(false).email("Smith@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(3).name("Michael").familyName("Johnson").accessionDate(LocalDate.of(2022, 1, 3))
                    .nationality("Canada").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("345678").accountApproved(false).email("Brown@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(4).name("Emily").familyName("Brown").accessionDate(LocalDate.of(2022, 1, 4))
                    .nationality("Australia").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("901234").accountApproved(false).email("John@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(5).name("Daniel").familyName("Wilson").accessionDate(LocalDate.of(2022, 1, 5))
                    .nationality("Germany").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("567890").accountApproved(false).email("Wilson@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(6).name("Olivia").familyName("Taylor").accessionDate(LocalDate.of(2022, 1, 6))
                    .nationality("France").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("123789").accountApproved(false).email("Taylor@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(7).name("James").familyName("Anderson").accessionDate(LocalDate.of(2022, 1, 7))
                    .nationality("Spain").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("456012").accountApproved(false).email("Anderson@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(8).name("Sophia").familyName("Martinez").accessionDate(LocalDate.of(2022, 1, 8))
                    .nationality("Italy").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("890123").accountApproved(false).email("Martinez@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(9).name("Benjamin").familyName("Lopez").accessionDate(LocalDate.of(2022, 1, 9))
                    .nationality("Mexico").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("567890").accountApproved(false).email("Lopez@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(10).name("Isabella").familyName("Garcia").accessionDate(LocalDate.of(2022, 1, 10))
                    .nationality("Brazil").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("234567").accountApproved(false).email("Garcia@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(11).name("William").familyName("Rodriguez").accessionDate(LocalDate.of(2022, 1, 11))
                    .nationality("Argentina").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("901234").accountApproved(false).email("William@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(12).name("Mia").familyName("Lee").accessionDate(LocalDate.of(2022, 1, 12))
                    .nationality("China").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("678901").accountApproved(false).email("Lee@gmail.com").password("John").role(Role.PROF).build());

            memberList.add(Member.builder().num(13).name("Alexander").familyName("Nguyen").accessionDate(LocalDate.of(2022, 1, 13))
                    .nationality("Japan").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("345678").accountApproved(false).email("Nguyen@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(14).name("Ava").familyName("Kim").accessionDate(LocalDate.of(2022, 1, 14))
                    .nationality("South Korea").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("012345").accountApproved(false).email("Ava@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(14).name("Ava").familyName("Kim").accessionDate(LocalDate.of(2022, 1, 14))
                    .nationality("South Korea").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("012345").accountApproved(false).email("Kim@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(15).name("Henry").familyName("Chen").accessionDate(LocalDate.of(2022, 1, 15))
                    .nationality("Taiwan").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("789012").accountApproved(false).email("Chen@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(16).name("Charlotte").familyName("Wang").accessionDate(LocalDate.of(2022, 1, 16))
                    .nationality("India").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("456789").accountApproved(false).email("Wang@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(17).name("Sebastian").familyName("Gupta").accessionDate(LocalDate.of(2022, 1, 17))
                    .nationality("Russia").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("123456").accountApproved(false).email("Gupta@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(18).name("Scarlett").familyName("Kumar").accessionDate(LocalDate.of(2022, 1, 18))
                    .nationality("Australia").identityDocument(Member.IdentityDocumentType.PASSPORT).identityNumber("890123").accountApproved(false).email("Kumar@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(19).name("Elijah").familyName("Ali").accessionDate(LocalDate.of(2022, 1, 19))
                    .nationality("Canada").identityDocument(Member.IdentityDocumentType.CIN).identityNumber("567890").accountApproved(false).email("Ali@gmail.com").password("John").role(Role.STUDENT).build());

            memberList.add(Member.builder().num(20).name("Grace").familyName("Khan").accessionDate(LocalDate.of(2022, 1, 20))
                    .nationality("UK").identityDocument(Member.IdentityDocumentType.CARTE_RESIDENCE).identityNumber("234567").accountApproved(false).email("khan@gmail.com").password("John").role(Role.STUDENT).build());
            memberRepository.saveAll(memberList);
        }
    }
}
