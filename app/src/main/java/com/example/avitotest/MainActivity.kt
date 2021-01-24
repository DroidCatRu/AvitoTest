package com.example.avitotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.avitotest.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

    val toolbar: Toolbar = findViewById(R.id.avito_toolbar)
    setSupportActionBar(toolbar)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.fragment_container, MainFragment.newInstance())
          .commitNow()
    }
  }
}