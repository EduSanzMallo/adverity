package com.adverity.exception

class AdverityException extends RuntimeException {

    String message

    AdverityException(String message) {
        super(message)
    }
}
