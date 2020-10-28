package com.ecommerce.dmart.ui.login.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ecommerce.dmart.R
import com.ecommerce.dmart.databinding.ActivityLoginBinding
import com.ecommerce.dmart.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding ? = null
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
}