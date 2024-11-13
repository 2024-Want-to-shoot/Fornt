package com.example.a2024_want_to_shoot

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout // DrawerLayout 변수 선언
    private lateinit var navigationView: NavigationView // NavigationView 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolvar 설정
       // val mainToolbar = findViewById<Toolbar>(R.id.main_toolbar)
        val mainToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mainToolbar)

        supportActionBar?.setDisplayShowTitleEnabled(true) // 왼쪽에 텍스트를 유지하도록 설정

        // DrawerLayout과 NavigationView 초기화
        drawerLayout = findViewById(R.id.main_drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // 사이드바(NavigationView) 내 메뉴 항목을 클릭했을 때의 동작을 처리
        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItem(menuItem)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // 오른쪽 메뉴를 'Toolbar'에 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when (item.itemId){
            R.id.menu_hamburger -> {
                drawerLayout.openDrawer(GravityCompat.END) //오른쪽에서 Drawer 열기
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleNavigationItem(menuItem: MenuItem){
        when(menuItem.itemId){
            R.id.account ->{
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
            R.id.itm_attendance -> {
                val intent = Intent(this, AttendanceActivity::class.java)
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