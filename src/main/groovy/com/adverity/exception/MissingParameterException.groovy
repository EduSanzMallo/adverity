package com.adverity.exception

class MissingParameterException extends AdverityException {

    String message

    MissingParameterException(String message) {
        super(message)
        this.message = message
    }
}
