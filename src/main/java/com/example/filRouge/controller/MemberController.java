package com.example.filRouge.controller;

import com.example.filRouge.controller.vm.member.request.requestMember;
import com.example.filRouge.controller.vm.member.response.MemberPaginationResponse;
import com.example.filRouge.controller.vm.member.response.responseMember;
import com.example.filRouge.entities.Member;
import com.example.filRouge.entities.Role;
import com.example.filRouge.service.memberService.MemberService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/member")
@PreAuthorize("hasRole('MANAGER')")
public class MemberController {

    final private MemberService memberService;

    final ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> addMember(@Valid  @RequestBody() requestMember requestmember) throws MessagingException, IOException {
            Member member=modelMapper.map(requestmember,Member.class);
            return ResponseEntity.ok(memberService.create(member));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") long id, @RequestBody() Member member) {
            member.setId(id);
            Member addedMember = memberService.update(member);
            return new ResponseEntity<>(addedMember, HttpStatus.OK);
    }

    @PutMapping("/{role}/{num}")
    public ResponseEntity<?> updateMemberRole(@PathVariable("role") String role, @PathVariable("num") Integer member) {
        Role newRole=Role.valueOf(role);
        Member addedMember = memberService.updateRole(newRole,member);
        return new ResponseEntity<>(addedMember, HttpStatus.OK);
    }


    @PutMapping("/approve")
    public ResponseEntity<?> approveAccount( @RequestParam() Integer member) {
        return ResponseEntity.ok(memberService.approveAccount(member));
    }

    @PostMapping("/Members")
    public ResponseEntity<?> search(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "3") Integer size){
        Pageable paging = PageRequest.of(page, size);
        Page<Member> pageTuts=memberService.searchMember(search,paging);;
        return new ResponseEntity<>( MemberPaginationResponse.builder()
                .members(pageTuts.getContent().stream()
                        .map(p->modelMapper.map(p,responseMember.class))
                        .toList())
                .totalMembers(pageTuts.getTotalElements())
                .totalPages(pageTuts.getTotalPages()).currentPage(pageTuts.getNumber())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<?> deleteMember(@PathVariable("num") Integer num) {
            Member member= Member.builder().num(num).build();
            memberService.delete(member);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
