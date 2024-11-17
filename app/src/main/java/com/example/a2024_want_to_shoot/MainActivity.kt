package com.example.a2024_want_to_shoot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var calendarView: CalendarView
    private lateinit var attendanceButton: Button
    private lateinit var attendanceStatus: TextView
    private lateinit var basketGrid: GridLayout

    private val attendanceStatusList = BooleanArray(10)
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar() // 툴바 설정
        setupDrawerLayout() // 사이드바 설정
        setupUI() // UI 요소 초기화
        setupBasketGrid() // 농구공 GridLayout 초기화
    }

    // 툴바 설정
    private fun setupToolbar() {
        val mainToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    // DrawerLayout 및 NavigationView 초기화
    private fun setupDrawerLayout() {
        drawerLayout = findViewById(R.id.main_drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItem(menuItem)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // UI 초기화
    private fun setupUI() {
        calendarView = findViewById(R.id.calendarView)
        attendanceButton = findViewById(R.id.attendanceButton)
        attendanceStatus = findViewById(R.id.attendanceStatus)
        basketGrid = findViewById(R.id.basketGrid)

        // 초기화 버튼 설정
        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            resetAttendance() // 초기화 메서드 호출
        }

        // 날짜 선택 이벤트
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            selectedDate = dateFormat.format(calendar.time)
            checkAttendance(selectedDate)
        }

        // 출석체크 버튼 클릭 이벤트
        attendanceButton.setOnClickListener {
            markAttendance()
        }

        setupWelcomeText() // 환영 메시지 설정
        setupPlaylistButton() // 플레이리스트 버튼 설정
    }

    // 농구공 GridLayout 초기화
    private fun setupBasketGrid() {
        for (i in attendanceStatusList.indices) {
            val imageView = ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 150
                    height = 150
                    setMargins(8, 8, 8, 8)
                }
                setImageResource(R.drawable.ic_basketball_empty)
                tag = i
            }
            basketGrid.addView(imageView)
        }
    }

    // 환영 메시지 설정
    private fun setupWelcomeText() {
        val textView = findViewById<TextView>(R.id.welcom_coment)
        val text = "지수님\n같이 농구해요! "
        val spannableString = SpannableString(text)
        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_basketball_resized)
        drawable?.setBounds(0, 0, 50, 50)
        drawable?.let {
            val imageSpan = ImageSpan(it, ImageSpan.ALIGN_BOTTOM)
            spannableString.setSpan(imageSpan, text.length - 1, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        textView.text = spannableString
    }

    // 플레이리스트 버튼 설정
    private fun setupPlaylistButton() {
        val openPlaylistButton = findViewById<ImageView>(R.id.playlist_open_button)
        openPlaylistButton.setOnClickListener {
            val intent = Intent(this, PlaylistActivity::class.java)
            startActivity(intent)
        }
    }

    private fun markAttendance() {
        if (selectedDate.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences("AttendancePrefs", Context.MODE_PRIVATE)
            val isPresent = sharedPreferences.getBoolean(selectedDate, false)

            if (isPresent) {
                // 이미 출석된 경우 메시지 표시
                attendanceStatus.text = "이미 출석 체크한 날짜입니다!"
                return
            }

            saveAttendance(selectedDate)

            // 모든 공이 채워진 상태인지 확인
            if (attendanceStatusList.all { it }) {
                resetAttendance()
                attendanceStatus.text = "새로운 출석 체크가 시작되었습니다!"
                return
            }

            // 비어 있는 첫 번째 공을 채움
            for (i in attendanceStatusList.indices) {
                if (!attendanceStatusList[i]) {
                    attendanceStatusList[i] = true
                    val imageView = basketGrid.getChildAt(i) as ImageView
                    imageView.setImageResource(R.drawable.ic_basketball)
                    updateAttendanceMessage()
                    return
                }
            }

            attendanceStatus.text = "이미 모든 출석이 완료되었습니다!"
        } else {
            attendanceStatus.text = "날짜를 선택해주세요!"
        }
    }


    private fun resetAttendance() {

        // SharedPreferences 초기화
        val sharedPreferences = getSharedPreferences("AttendancePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // 모든 데이터 삭제
        editor.apply()
        // 공 상태 초기화
        for (i in attendanceStatusList.indices) {
            attendanceStatusList[i] = false
            val imageView = basketGrid.getChildAt(i) as ImageView
            imageView.setImageResource(R.drawable.ic_basketball_empty)
        }
    }

    // 출석 메시지 업데이트
    private fun updateAttendanceMessage() {
        // attendanceStatus 초기화 여부 확인
        if (::attendanceStatus.isInitialized) {
            // attendanceStatusList가 null이 아닌지 확인
            if (attendanceStatusList.isNotEmpty()) {
                val daysChecked: Int = attendanceStatusList.count { it } // true 상태 개수 확인

                // daysChecked가 유효한 값인지 확인 후 텍스트 설정
                attendanceStatus.text = "$daysChecked 일 연속 출석 중!"
            } else {
                Log.e("Error", "attendanceStatusList가 초기화되지 않았습니다.")
            }
        } else {
            Log.e("Error", "attendanceStatus 뷰가 초기화되지 않았습니다.")
        }
    }

    // 출석 여부 확인
    private fun checkAttendance(date: String) {
        val sharedPreferences = getSharedPreferences("AttendancePrefs", Context.MODE_PRIVATE)
        val isPresent = sharedPreferences.getBoolean(date, false)

        if (isPresent) {
            // 이미 출석된 경우 메시지 표시
            attendanceStatus.text = "이미 출석 체크한 날짜입니다!"
        } else {
            attendanceStatus.text = "미출석"
        }
    }

    // 출석 데이터 저장
    private fun saveAttendance(date: String) {
        val sharedPreferences = getSharedPreferences("AttendancePrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(date, true)
        editor.apply()
    }

    // 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // 메뉴 항목 선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_notification -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_hamburger -> {
                drawerLayout.openDrawer(GravityCompat.END) // 오른쪽 드로어 열기
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // 네비게이션 아이템 처리
    private fun handleNavigationItem(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.account -> {
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }

            R.id.itm_rules -> {
                val intent = Intent(this, RulesActivity::class.java)
                startActivity(intent)
            }

            R.id.itm_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }

            R.id.itm_bookmark -> {
                val intent = Intent(this, BookmarkActivity::class.java)
                startActivity(intent)
            }

            R.id.itm_quiz -> {
                val intent = Intent(this, QuizActivity::class.java)
                startActivity(intent)
            }

            R.id.itm_logout -> {
                val intent = Intent(this, LogoutActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
