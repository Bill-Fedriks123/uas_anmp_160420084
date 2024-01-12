package com.example.advnative_waroengujang.view

import android.view.View


interface TextSignInListener{
    fun onTextSignIn(v: View)
}

interface ButtonSignInListener{
    fun onButtonSignIn(v: View)
}


interface ButtonEditProfileListener{
    fun onButtonEditProfile(v: View)
}


interface ButtonLogoutListener {
    fun onButtonLogout(v: View)
}