package com.example.a2024_want_to_shoot

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.a2024_want_to_shoot.getDummyData

class SearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchInput: EditText
    private lateinit var categoryButtons: Map<String, Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // 뒤로가기 버튼 연결
        val backButton = findViewById<ImageButton>(R.id.back_button)
        // 뒤로가기 버튼 클릭 이벤트
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // 현재 Activity를 종료하여 스택에서 제거
        }

        // 검색창 연결
        searchInput = findViewById(R.id.search_input)
        searchInput.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_NORMAL

        // 카테고리 버튼 연결
        val categoryShot = findViewById<Button>(R.id.category_shot)
        val categoryDribble = findViewById<Button>(R.id.category_dribble)
        val categoryStep = findViewById<Button>(R.id.category_step)
        val categoryPass = findViewById<Button>(R.id.category_pass)
        val categoryScreen = findViewById<Button>(R.id.category_screen)
        categoryButtons = mapOf(
            "슛" to categoryShot,
            "드리블" to categoryDribble,
            "스탭" to categoryStep,
            "패스" to categoryPass,
            "스크린" to categoryScreen
        )

        // RecyclerView 설정
        recyclerView = findViewById(R.id.action_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 다른 파일에서 데이터 가져오기
        val actions = getDummyData()

        // 가져온 데이터를 사용
        actions.forEach {
            println(it)
        }

        // 어댑터 설정
        val adapter = ActionAdapter(actions)
        recyclerView.adapter = adapter

        // 검색창 필터링
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                adapter.filter(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 카테고리 버튼 클릭 이벤트
        categoryButtons.forEach { (category, button) ->
            button.setOnClickListener {
                adapter.filterByCategory(category)
            }
        }
    }

}