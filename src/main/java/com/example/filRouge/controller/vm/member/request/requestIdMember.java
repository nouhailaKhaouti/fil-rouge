package com.example.filRouge.controller.vm.member.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestIdMember {

      @NotNull(message = "the id can't be null")
      private Integer num;
}
