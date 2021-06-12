package com.nandaprasetio.gamecatalog.presentation.activity

import android.os.Bundle
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.activity.BaseActivity
import com.nandaprasetio.gamecatalog.databinding.ActivityDetailGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity: BaseActivity<ActivityDetailGameBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.also {
            it.setTitle(R.string.title_game_detail)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    override fun setViewBinding(): ActivityDetailGameBinding {
        return ActivityDetailGameBinding.inflate(this.layoutInflater)
    }
}