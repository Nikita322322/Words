package com.itechart.vpaveldm.words

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.itechart.vpaveldm.words.uiLayer.login.ILoginFragmentListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ILoginFragmentListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navigation, navController)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
            navController.navigate(R.id.action_wordFragment_to_loginFragment)
        } else {
            // Call this method 'cause you don't need display login fragment
            destroy()
        }
    }

    override fun create() {
        navigation.visibility = View.GONE
    }

    override fun destroy() {
        navigation.visibility = View.VISIBLE
    }
}
