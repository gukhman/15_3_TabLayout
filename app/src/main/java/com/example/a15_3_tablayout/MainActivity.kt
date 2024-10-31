package com.example.a15_3_tablayout

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowInsets(R.id.main)
        setupToolbar(R.id.toolbar, false)

        val pages = listOf(
            Page("Новости", "https://hi-tech.mail.ru/tag/android/"),
            Page("Музыка", "https://music.yandex.ru/"),
            Page("Кино", "https://www.kinopoisk.ru/")
        )

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        viewPager.adapter = ViewPagerAdapter(this, pages)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pages[position].title
        }.attach()
    }
}