package com.gdc.moviedbcoroutine.ui.now_playing.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gdc.moviedbcoroutine.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = intent.getStringExtra("KEY_NAME")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Toast.makeText(this, "MovieId: ${intent.getIntExtra("KEY_MOVIE_ID", 0)}", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
