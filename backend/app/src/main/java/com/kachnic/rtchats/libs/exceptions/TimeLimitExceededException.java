package com.kachnic.rtchats.libs.exceptions;

import com.kachnic.rtchats.libs.exceptions.service.OperationErrorCode;
import java.io.Serial;

public class TimeLimitExceededException extends DomainException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String paramName;
    private final String operation;
    private final long durationMs;

    public TimeLimitExceededException(final String paramName, final String operation, final long durationMs) {
        super(OperationErrorCode.TIME_EXCEEDED);
        this.paramName = paramName;
        this.operation = operation;
        this.durationMs = durationMs;
    }

    public String getParamName() {
        return paramName;
    }

    public String getOperation() {
        return operation;
    }

    public long getDurationMs() {
        return durationMs;
    }
}
