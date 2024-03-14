package com.example.filRouge.controller.vm.member.response;

import com.example.filRouge.entities.Member;
import com.example.filRouge.entities.Role;
import lombok.Data;

@Data
public class responseMember {
    private Integer num;

    private String name;

    private String familyName;

    private String nationality;

    private Member.IdentityDocumentType identityDocument;

    private String identityNumber;

    private Boolean accountApproved= false;

    private Role role;
}
