package com.example.a2024_want_to_shoot

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toolbar
import android.view.MenuItem
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024_want_to_shoot.rules

class RulesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryButtons: Map<String, Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        // 뒤로가기 버튼 연결
        val backButton = findViewById<ImageButton>(R.id.back_button)
        // 뒤로가기 버튼 클릭 이벤트
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity를 종료하여 스택에서 제거
        }

        val categorybasic = findViewById<Button>(R.id.category_basic)
        val categoryteam = findViewById<Button>(R.id.category_team)
        val categorystart = findViewById<Button>(R.id.category_start)
        val categoryscore = findViewById<Button>(R.id.category_score)
        categoryButtons = mapOf(
            "기본 규칙" to categorybasic,
            "팀 구성 및 선수" to categoryteam,
            "경기 시작 및 진행" to categorystart,
            "득점 및 스코어링" to categoryscore
        )

        recyclerView = findViewById(R.id.rule_list)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // 다른 파일에서 데이터 가져오기
        val rules = rules()

        // 가져온 데이터를 사용
        rules.forEach {
            println(it)
        }

        // 어댑터 설정
        val adapter = RulesAdapter(rules)
        recyclerView.adapter = adapter

        // 카테고리 버튼 클릭 이벤트
        categoryButtons.forEach { (category, button) ->
            button.setOnClickListener {
                adapter.filterByCategory(category)
            }
        }
    }

    }
