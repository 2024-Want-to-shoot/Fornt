package com.example.a2024_want_to_shoot

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlaybackActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

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
//            "프런트 체인지 드리블" -> R.raw.frontchange_audio
//            "원 핸드 슛" -> R.raw.onehand_audio
//            "패스트 패스" -> R.raw.fastpass_audio
//            "리버스 패스" -> R.raw.reversepass_audio
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

//        // 더미 데이터 가져오기
//        val actions = getDummyData()
//
//        // 전달받은 데이터
//        val motionName = intent.getStringExtra("action_name") ?: "default"
//        Log.d("PlaybackActivity", "Motion Name: $motionName")
//
//        // 전달받은 motionName과 더미 데이터를 연결
//        val matchedAction = actions.find {
//            it is ActionItem.Action && it.name.trim().equals(motionName.trim(), ignoreCase = true)
//        } as? ActionItem.Action

//        // UI 연결
//        val detailsNameTextView = findViewById<TextView>(R.id.activity_details_name)
//        val activityDetailsTextView = findViewById<TextView>(R.id.activity_details)
//        val imageView = findViewById<ImageView>(R.id.bigactivity_img)
//
//        // 이미지 및 텍스트 동적으로 설정
//        if (matchedAction != null) {
//            detailsNameTextView.text = matchedAction.detailsName
//            activityDetailsTextView.text = matchedAction.activityDetails
//
//            val imageResource = when (matchedAction.name) {
//                "레이업 슛" -> R.drawable.layupshot
//                else -> R.drawable.layupshot // 기본 이미지
//            }
//            imageView.setImageResource(imageResource)
//
//        } else {
//            detailsNameTextView.text = "동작을 찾을 수 없습니다"
//            activityDetailsTextView.text = "더미 데이터와 일치하지 않습니다."
//            imageView.setImageResource(R.drawable.layupshot) // 기본 이미지
//        }

//        // MediaPlayer 설정
//        val audioResource = when (motionName) {
//            "레이업 슛" -> R.raw.layupshot
//            else -> R.raw.layupshot // 기본 오디오
//        }
//        mediaPlayer = MediaPlayer.create(this, audioResource)
//
//        val startButton = findViewById<ImageButton>(R.id.play_state_button)
//        startButton.setOnClickListener {
//            val currentSrc = startButton.drawable.constantState
//            Log.d("PlaybackActivity", "Current button state: $currentSrc")
//            if (currentSrc == resources.getDrawable(R.drawable.ic_stopplay).constantState) {
//                startButton.setImageResource(R.drawable.ic_startplay)
//                mediaPlayer?.pause()
//            } else {
//                startButton.setImageResource(R.drawable.ic_stopplay)
//                mediaPlayer?.start()
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        // MediaPlayer 해제
//        mediaPlayer?.release()
//        mediaPlayer = null
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//}
