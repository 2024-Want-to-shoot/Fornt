package com.example.a2024_want_to_shoot

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActionDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actiondetails);

        // 뒤로 가기 버튼 연결
        val backButton = findViewById<ImageButton>(R.id.back_button)
        // 뒤로 가기 버튼 클릭 이벤트
        backButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity를 종료하여 스택에서 제거
        }

        // Intent에서 동작 이름 받기
        val actionName = intent.getStringExtra("action_name") ?: "동작 이름 없음"
        val detailsName = intent.getStringExtra("details_name") ?: "세부 이름 없음"
        val activityDetails = intent.getStringExtra("activity_details") ?: "세부 이름 없음"

        // 받아온 정보를 TextView에 설정
        val actionNameTextView = findViewById<TextView>(R.id.activity_name)
        val detailsNameTextView = findViewById<TextView>(R.id.activity_details_name)
        val activityDetailsTextView = findViewById<TextView>(R.id.activity_details)
        actionNameTextView.text = actionName
        detailsNameTextView.text = detailsName
        activityDetailsTextView.text = activityDetails


        // 재생 버튼 연결
        val startButton = findViewById<ImageButton>(R.id.play_state_button)
        // 재생 버튼 클릭 이벤트
        startButton.setOnClickListener {
            // 버튼 이미지 변경
            val currentSrc = startButton.drawable.constantState
            val newImageResource = if (currentSrc == resources.getDrawable(R.drawable.ic_stopplay).constantState) {
                R.drawable.ic_startplay // 재생 중일 때 보여줄 이미지
            } else {
                R.drawable.ic_stopplay // 멈춘 상태일 때 보여줄 이미지
            }

            startButton.setImageResource(newImageResource)
        }

        // '이전 플레이' 버튼 연결
        val beforePlayButton = findViewById<ImageButton>(R.id.before_play_button)
        beforePlayButton.setOnClickListener {
            // 이전 플레이 버튼 클릭 시 처리할 내용 (옵션)
        }

        // '다음 플레이' 버튼 연결
        val nextPlayButton = findViewById<ImageButton>(R.id.next_play_button)
        nextPlayButton.setOnClickListener {
            // 다음 플레이 버튼 클릭 시 처리할 내용 (옵션)
        }
    }

}