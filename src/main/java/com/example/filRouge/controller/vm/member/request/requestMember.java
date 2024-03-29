package com.example.filRouge.controller.vm.member.request;

import com.example.filRouge.entities.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestMember {
    private Integer num;

    @NotNull(message = "the name field can't be null")
    @NotBlank(message = "the name field can't be blank")
    private String name;

/*    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")*/
    private String familyName;

    @NotNull(message = "the user nationality can't be null")
    @NotBlank(message = "the user nationality can't be blank")
    private String nationality;

    private Member.IdentityDocumentType identityDocument;

    @NotNull(message = "the identity Number can't be null")
    @NotBlank(message = "the identity Number can't be blank")
    private String identityNumber;
    private String email;

    private boolean accountApproved;

}
