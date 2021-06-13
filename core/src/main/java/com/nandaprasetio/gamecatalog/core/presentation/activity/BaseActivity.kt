package com.nandaprasetio.gamecatalog.core.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>(private val back: Boolean): AppCompatActivity() {
    protected var viewBinding: T? = null

    protected abstract fun setViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.also {
            it.setHomeButtonEnabled(back)
            it.setDisplayHomeAsUpEnabled(back)
        }
        viewBinding = setViewBinding()
        this.setContentView(viewBinding?.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}