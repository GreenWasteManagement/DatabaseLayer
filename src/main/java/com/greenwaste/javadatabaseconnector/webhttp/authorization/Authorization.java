package com.greenwaste.javadatabaseconnector.webhttp.authorization;

public class Authorization {

    public enum UserRolePermission {
        ADMIN(1),
        MUNICIPALITY(2),
        SMAS(3);

        private final int level;

        UserRolePermission(int level) {
            this.level = level;
        }

        public static UserRolePermission fromString(String userRole) {
            try {
                return UserRolePermission.valueOf(userRole.toUpperCase());
            } catch (IllegalArgumentException | NullPointerException e) {
                return null;
            }
        }

        public int getLevel() {
            return level;
        }

        public boolean canAccess(UserRolePermission requiredRole) {
            return this.level <= requiredRole.getLevel();
        }
    }

}
