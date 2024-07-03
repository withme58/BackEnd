package toy.withme58.api.common.exception;

import toy.withme58.api.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
