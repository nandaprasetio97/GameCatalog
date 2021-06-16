package com.nandaprasetio.gamecatalog.presentation.activity

import android.os.Bundle
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.activity.BaseActivity
import com.nandaprasetio.gamecatalog.databinding.ActivityDetailGameBinding
import com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment.GameDetailPagingRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity: BaseActivity<ActivityDetailGameBinding>(true) {
    companion object {
        const val ARGUMENT_GAME_ID = "argument.GAME_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.setTitle(R.string.title_game_detail)

        viewBinding?.also {
            val fragmentRecyclerViewBinding = supportFragmentManager.findFragmentById(it.fragmentRecyclerViewGameDetail.id) as GameDetailPagingRecyclerViewFragment
            fragmentRecyclerViewBinding.arguments = Bundle().apply {
                this.putInt(
                    GameDetailPagingRecyclerViewFragment.ARGUMENT_GAME_ID,
                    this@GameDetailActivity.intent.getIntExtra(ARGUMENT_GAME_ID, 0)
                )
            }
        }
    }

    override fun setViewBinding(): ActivityDetailGameBinding {
        return ActivityDetailGameBinding.inflate(this.layoutInflater)
    }
}