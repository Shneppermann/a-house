package com.epam.auction.entity;

import java.util.Objects;

/**
 * Role entity
 */
public class Role extends Entity {

    private static final long serialVersionUID = -3964466719941680601L;
    private String roleName;

    public Role() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public Role clone() {
        return (Role) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Role role = (Role) object;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                "} " + super.toString();
    }
}
