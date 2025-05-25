package com.brhdc.binjwatch.util

enum class Action {
    ADD,
    REMOVE,
    REMOVE_ALL,
    NO_ACTION
}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> Action.ADD

        this == "REMOVE" -> Action.REMOVE

        this == "REMOVE_ALL" -> Action.REMOVE_ALL

        else -> Action.NO_ACTION
    }
}