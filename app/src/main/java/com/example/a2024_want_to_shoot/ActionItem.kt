package com.example.a2024_want_to_shoot

sealed class ActionItem {
    data class Header(val title: String) : ActionItem()
    data class Action(
        val name: String,
        val category: String,
        val detailsName: String,
        val activityDetails: String,
        var isBookmark: Boolean
    ) : ActionItem()
}