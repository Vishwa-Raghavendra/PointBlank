package com.dscepointblank.pointblank.ui.fragments.Login

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
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dscepointblank.pointblank.R
import com.dscepointblank.pointblank.ui.activities.HomeActivity
import com.dscepointblank.pointblank.ui.activities.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {
    companion object{
        fun newInstance() =LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var mauth:FirebaseAuth
    var TAG="LOGIN"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window =  activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = Color.parseColor("#212121")

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        val text = "<font color=#00C853>Point</font> <font color=#fafafa>Blank</font>"
        view.loginPBTV.text = HtmlCompat.fromHtml(text,HtmlCompat.FROM_HTML_MODE_LEGACY)


        view.tv_signUpLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment2)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(LoginViewModel::class.java)

        mauth=FirebaseAuth.getInstance()


        login_proceed.setOnClickListener {
            val email = et_loginEmail.text.toString()
            val password= et_loginPassword.text.toString()
            if(email.isEmpty()){
                et_loginEmail.error="Email Empty"
                et_loginEmail.requestFocus()
            }else if (password.isEmpty()){
                et_loginPassword.error="Password Emapty"
                et_loginPassword.requestFocus()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_loginEmail.error="Invalid Email"
                et_loginEmail.requestFocus()
            }
            else{
                mauth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener() { task ->
                       if(task.isSuccessful){
                           Log.d(TAG,"login successful")
                           val intent= Intent(requireContext(),HomeActivity::class.java)
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                           startActivity(intent)
                           activity?.finish()
                           Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()
                       }

                    }
            }
        }
        tv_signUpLogin.setOnClickListener {
            val intent=Intent(requireContext(),SignUpActivity::class.java)
            startActivity(intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity?.finish()
        }


    }
}