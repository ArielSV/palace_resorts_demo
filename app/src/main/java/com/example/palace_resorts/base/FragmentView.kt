package com.example.palace_resorts.base

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.palace_resorts.base.interfaces.AndroidProvider

abstract class FragmentView : Fragment(), AndroidProvider {

    private val baseActivity by lazy {
        requireActivity() as BaseFragmentActivity
    }

    override fun showError(message: String) {
        baseActivity.showError(message)
    }

    override fun showMessage(message: String) {
        baseActivity.showMessage(message)
    }

    override fun dismissProgressDialog() {
        baseActivity.dismissProgressDialog()
    }

    override fun backToolbar() {
        baseActivity.backToolbar()
    }

    override fun startActivity(intent: Intent) {
        baseActivity.startActivity(intent)
    }

    override fun showProgressDialog() {
        baseActivity.showProgressDialog()
    }


    override fun showLoading(show: Boolean) {
        baseActivity.showLoading(show)
    }

    open fun onBackPressed() = Unit
}
