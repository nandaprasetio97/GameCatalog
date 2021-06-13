package com.nandaprasetio.gamecatalog.presentation.activity

import android.os.Bundle
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.activity.BaseActivity
import com.nandaprasetio.gamecatalog.databinding.ActivityDetailGameDeveloperBinding
import com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment.GameDeveloperDetailPagingRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDeveloperDetailActivity: BaseActivity<ActivityDetailGameDeveloperBinding>(true) {
    companion object {
        const val ARGUMENT_GAME_DEVELOPER_SLUG = "argument.GAME_DEVELOPER_SLUG"
        const val ARGUMENT_GAME_DEVELOPER_ID = "argument.GAME_DEVELOPER_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.setTitle(R.string.title_game_developer_detail)

        viewBinding?.also {
            val fragmentRecyclerViewBinding = supportFragmentManager.findFragmentById(it.fragmentRecyclerViewGameDeveloperDetail.id) as GameDeveloperDetailPagingRecyclerViewFragment
            fragmentRecyclerViewBinding.arguments = Bundle().apply {
                this.putString(
                    GameDeveloperDetailPagingRecyclerViewFragment.ARGUMENT_GAME_DEVELOPER_SLUG,
                    this@GameDeveloperDetailActivity.intent.getStringExtra(ARGUMENT_GAME_DEVELOPER_SLUG)
                )
                this.putInt(
                    GameDeveloperDetailPagingRecyclerViewFragment.ARGUMENT_GAME_DEVELOPER_ID,
                    this@GameDeveloperDetailActivity.intent.getIntExtra(ARGUMENT_GAME_DEVELOPER_ID, 0)
                )
            }
        }
    }

    override fun setViewBinding(): ActivityDetailGameDeveloperBinding {
        return ActivityDetailGameDeveloperBinding.inflate(this.layoutInflater)
    }
}