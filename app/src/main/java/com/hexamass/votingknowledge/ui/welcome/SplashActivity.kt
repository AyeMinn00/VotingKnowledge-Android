package com.hexamass.votingknowledge.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (dataSource.getLanguageId() != "")
            startActivity(MainActivity.start(this))
        else startActivity(WelcomeActivity.start(this))

    }

}