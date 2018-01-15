package com.epam.auction.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Base entity
 */
public abstract class Entity implements Serializable, Cloneable {
    private static final long serialVersionUID = -635267118661362561L;
    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Entity clone() {
        try {
            return (Entity) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new InternalError(exception);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Entity entity = (Entity) object;
        return id == entity.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override

    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
