package com.example.filRouge.controller.vm.member.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberPaginationResponse {

    private List<responseMember> members;
    private Long totalMembers;
    private int currentPage;
    private int totalPages;
}
