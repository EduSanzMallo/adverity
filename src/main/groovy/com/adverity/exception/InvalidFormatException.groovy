package com.adverity.exception

class InvalidFormatException extends AdverityException {

    String message

    InvalidFormatException(String message) {
        super(message)
        this.message = message
    }
}
