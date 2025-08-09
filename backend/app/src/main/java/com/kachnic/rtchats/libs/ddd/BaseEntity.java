package com.kachnic.rtchats.libs.ddd;

import com.kachnic.rtchats.libs.exceptions.MissingArgumentException;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

public class BaseEntity<T> {
    protected final T entityId;

    protected BaseEntity(final T entityId) {
        DomainValidate.ifNull(entityId)
                .thenThrow(() -> new MissingArgumentException(
                        ((ParameterizedType) getClass().getGenericSuperclass())
                                .getActualTypeArguments()[0].getTypeName()));
        this.entityId = entityId;
    }

    public T getEntityId() {
        return entityId;
    }

    @Override
    public final boolean equals(final Object obj) {
        return obj == this
                || (obj instanceof BaseEntity<?> other
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
