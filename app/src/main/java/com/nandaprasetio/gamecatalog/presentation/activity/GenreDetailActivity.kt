package com.nandaprasetio.gamecatalog.presentation.activity

import android.os.Bundle
import com.nandaprasetio.gamecatalog.R
import com.nandaprasetio.gamecatalog.core.presentation.activity.BaseActivity
import com.nandaprasetio.gamecatalog.databinding.ActivityDetailGenreBinding
import com.nandaprasetio.gamecatalog.presentation.fragment.recyclerviewfragment.GenreDetailPagingRecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreDetailActivity: BaseActivity<ActivityDetailGenreBinding>() {
    companion object {
        const val ARGUMENT_GENRE_SLUG = "argument.GENRE_SLUG"
        const val ARGUMENT_GENRE_ID = "argument.GENRE_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.also {
            it.setTitle(R.string.title_genre_detail)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        viewBinding?.also {
            val fragmentRecyclerViewBinding = supportFragmentManager.findFragmentById(it.fragmentRecyclerViewGenreDetail.id) as GenreDetailPagingRecyclerViewFragment
            fragmentRecyclerViewBinding.arguments = Bundle().apply {
                this.putString(
                    GenreDetailPagingRecyclerViewFragment.ARGUMENT_GENRE_SLUG,
                    this@GenreDetailActivity.intent.getStringExtra(ARGUMENT_GENRE_SLUG)
                )
                this.putInt(
                    GenreDetailPagingRecyclerViewFragment.ARGUMENT_GENRE_ID,
                    this@GenreDetailActivity.intent.getIntExtra(ARGUMENT_GENRE_ID, 0)
                )
            }
        }
    }

    override fun setViewBinding(): ActivityDetailGenreBinding {
        return ActivityDetailGenreBinding.inflate(this.layoutInflater)
    }
}