package com.example.a2024_want_to_shoot

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "동작 플레이리스트"

        // RecyclerView 설정
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 설정
        val items = listOf(
            PlaylistItem(R.drawable.ic_launcher_foreground, "레이업 슛", "슛", false),
            PlaylistItem(R.drawable.ic_launcher_foreground, "프런트 체인지 드리블", "드리블", false),
            PlaylistItem(R.drawable.ic_launcher_foreground, "원 핸드 슛", "슛", false),
            PlaylistItem(R.drawable.ic_launcher_foreground, "패스트 패스", "패스", false),
            PlaylistItem(R.drawable.ic_launcher_foreground, "리버스 드리블", "드리블", false)
        )

        // 어댑터 연결 + 클릭 이벤트 처리
        val adapter = PlaylistAdapter(items) { clickedItem ->
            // 클릭된 아이템에 따라 화면 이동
            val intent = Intent(this, PlaybackActivity::class.java)
            intent.putExtra("item_title", clickedItem.title) // 제목 전달
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
