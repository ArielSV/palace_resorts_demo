package com.example.palace_resorts.base.interfaces

interface AndroidProvider {

    fun showError(message: String)

    fun showMessage(message: String)

    fun showProgressDialog()

    fun dismissProgressDialog()

    fun showLoading(show: Boolean)

    fun backToolbar()
}
