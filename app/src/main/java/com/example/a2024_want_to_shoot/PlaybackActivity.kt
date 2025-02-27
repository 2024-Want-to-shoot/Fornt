package com.example.a2024_want_to_shoot

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlaybackActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    // 동작 이름별 이미지와 오디오 리소스를 매핑한 데이터
    private val motionResources = mapOf(
        "레이업 슛" to Pair(R.drawable.layupshot, R.raw.layupshot),
        "프런트 체인지 드리블" to Pair(R.drawable.fronchang, R.raw.layupshot),
        "원 핸드 슛" to Pair(R.drawable.onehandshot, R.raw.layupshot),
        "패스트 패스" to Pair(R.drawable.fastpass, R.raw.layupshot),
        "리버스 패스" to Pair(R.drawable.reversepass, R.raw.layupshot)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        // 뒤로 가기 버튼 연결
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity를 종료하여 스택에서 제거
        }

        // 전달받은 데이터 가져오기
        val itemTitle = intent.getStringExtra("action_name") // 변수명을 통일
        supportActionBar?.title = itemTitle ?: "동작"

        val motionName = intent.getStringExtra("motion_name") ?: "default" // 동작 이름

        // ImageView 동적 설정
        val imageView = findViewById<ImageView>(R.id.bigactivity_img)
        when (motionName) {
            "레이업 슛" -> imageView.setImageResource(R.drawable.layupshot) // 레이업 슛 이미지
            "프런트 체인지 드리블" -> imageView.setImageResource(R.drawable.fronchang) // 점프 슛 이미지
            "원 핸드 슛" -> imageView.setImageResource(R.drawable.onehandshot) // 자유투 이미지
            "패스트 패스" -> imageView.setImageResource(R.drawable.fastpass) // 자유투 이미지
            "리버스 패스" -> imageView.setImageResource(R.drawable.reversepass) // 자유투 이미지
            else -> imageView.setImageResource(R.drawable.layupshot)
        }
        // MediaPlayer 설정
        val audioResource = when (motionName) {
            "레이업 슛" -> R.raw.layupshot
            "프런트 체인지 드리블" -> R.raw.layupshot
            "원 핸드 슛" -> R.raw.layupshot
            "패스트 패스" -> R.raw.layupshot
            "리버스 패스" -> R.raw.layupshot
            else -> R.raw.layupshot
        }

        mediaPlayer = MediaPlayer.create(this, audioResource)
        // 플레이 버튼 설정
        val playButton = findViewById<ImageButton>(R.id.play_state_button)
        playButton.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                playButton.setImageResource(R.drawable.ic_stopplay) // 아이콘 변경 (재생 버튼)
            } else {
                mediaPlayer?.start()
                playButton.setImageResource(R.drawable.ic_startplay) // 아이콘 변경 (정지 버튼)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // MediaPlayer 해제
        mediaPlayer?.release()
        mediaPlayer = null
    }
}