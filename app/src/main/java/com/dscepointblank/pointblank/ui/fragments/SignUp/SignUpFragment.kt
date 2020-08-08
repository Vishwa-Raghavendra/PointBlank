package com.dscepointblank.pointblank.ui.fragments.SignUp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.ui.activities.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {
    companion object{
        fun newInstance()=
            SignUpFragment()
    }
    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth: FirebaseAuth
    var TAG= "HomeActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window =  activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = Color.parseColor("#009A40")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        auth = FirebaseAuth.getInstance()
        signup_proceed.setOnClickListener() {
            val name=et_name.text.toString()
            val email=et_email.text.toString()
            val password=et_password.text.toString()


            if(name.isEmpty()){
                et_name.error="Name Empty"
                et_name.requestFocus()
            }else if (email.isEmpty()){
                et_email.error="Email Empty"
                et_email.requestFocus()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.error="invalid email"
                et_email.requestFocus()
            }else if (password.isEmpty()){
                et_password.error="Password Empty"
                et_password.requestFocus()
            }else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful){
                            Log.d(TAG,"hello")
                            val intent= Intent(requireContext(),HomeActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            activity?.finish()
                        }else{
                            Toast.makeText(context,"Authentication failed",Toast.LENGTH_SHORT).show()
                            Log.d(TAG,"Authentication failed")
                        }
                    }
                    .addOnFailureListener { task ->
                        Log.d(TAG,"$task")
                    }
            }
        }
    }


}