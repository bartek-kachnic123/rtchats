package com.kachnic.rtchats.libs.ddd;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

import com.kachnic.rtchats.libs.utils.ObjectGuard;

public class EntityBase<T> {

    protected final T entityId;

    protected EntityBase(final T entityId) {
        this.entityId = ObjectGuard.requireNotNull(entityId, this::getEntityIdName);
    }

    private String getEntityIdName() {
        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
    }

    public T getEntityId() {
        return entityId;
    }

    @Override
    public final boolean equals(final Object obj) {
        return obj == this
                || (obj instanceof EntityBase<?> other
                        && other.canEqual(this)
                        && Objects.equals(this.entityId, other.entityId));
    }

    protected boolean canEqual(final Object other) {
        return this.getClass() == other.getClass();
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(entityId);
    }
}
