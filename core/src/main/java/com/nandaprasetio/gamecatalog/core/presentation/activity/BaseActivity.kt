package com.nandaprasetio.gamecatalog.core.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    protected var viewBinding: T? = null

    protected abstract fun setViewBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setViewBinding()
        this.setContentView(viewBinding?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}