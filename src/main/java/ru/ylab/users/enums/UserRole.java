package ru.ylab.users.enums;

/**
 * Перечисление ролей пользователей
 */
public enum UserRole {
    USER("user"),
    ADMIN("admin");
    private final String role;

    UserRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return role;
    }


}
