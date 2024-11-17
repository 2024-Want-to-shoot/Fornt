package com.example.a2024_want_to_shoot

data class PlaylistItem(
    val icon: Int,         // 아이콘 리소스 ID
    val title: String,     // 제목
    val subtitle: String,  // 부제목
    var isChecked: Boolean // 체크박스 상태
)
