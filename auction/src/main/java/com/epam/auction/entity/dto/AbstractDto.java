package com.epam.auction.entity.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base DTO object
 */

public abstract class AbstractDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 3930097989760125094L;
    private int id;

    public AbstractDto() {
    }

    public AbstractDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public AbstractDto clone() {
        try {
            return (AbstractDto) super.clone();
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
        AbstractDto entity = (AbstractDto) object;
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
