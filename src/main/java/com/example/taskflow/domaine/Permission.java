package com.example.taskflow.domaine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    CONCOUR_DELETE("concour:delete"),
    CONCOUR_UPDATE("concour:update"),
    CONCOUR_CREATE("concour:create"),
    ;


    @Getter
    private final String permission;
}
