package com.adverity

class Campaign {

    String name

    static constraints = {
        name nullable: false, blank: false
    }
}
