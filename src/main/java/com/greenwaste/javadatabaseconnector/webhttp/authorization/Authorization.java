package com.greenwaste.javadatabaseconnector.webhttp.authorization;

import lombok.Getter;

public class Authorization {

    @Getter
    public enum UserRolePermission {
        ADMIN(1),
        SMAS(2),
        MUNICIPALITY(3);

        private final int level;

        UserRolePermission(int level) {
            this.level = level;
        }

        public static UserRolePermission fromString(String userRole) {
            try {
                System.out.println(userRole);
                return UserRolePermission.valueOf(userRole.toUpperCase());
            } catch (IllegalArgumentException | NullPointerException e) {
                return null;
            }
        }

        public boolean canAccess(UserRolePermission requiredRole) {
            return this.level <= requiredRole.getLevel();
        }
    }

}
