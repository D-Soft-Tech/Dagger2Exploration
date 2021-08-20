package com.example.learningdatabinding

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningdatabinding.databinding.HomeFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

const val RC_SIGN_IN = 100


class HomeFragment : Fragment() {
    private var _homeFragmentBinding: HomeFragmentBinding? = null
    private val homeFragmentBinding get() = _homeFragmentBinding!!
    // Google signin client
    lateinit var mGoogleSignInClient: GoogleSignInClient
    // Car component, Dagger tutorial continues
//    @Inject
//    lateinit var car: Car

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)

        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = ProgressDialog.show(requireContext(), "Requesting", "Please wait while we confirm your account", false, false)

        // Setting the value of the variable welcome_message
        homeFragmentBinding.welcomeMessage = "Hi! Adebayo \nWelcome onboard"

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        // Set the dimensions of the sign-in button.
        // Set the dimensions of the sign-in button.
        val signInButton: SignInButton = homeFragmentBinding.signInButton
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        // Setting onClickListener to the sigin with google button
        signInButton.setOnClickListener() {
            signIn()
        }

        // Car object, Dagger Tutorial continues
//        car.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeFragmentBinding = null
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val acct = GoogleSignIn.getLastSignedInAccount(activity)
            if (acct != null) {
                val personName = acct.displayName
                val personGivenName = acct.givenName
                val personFamilyName = acct.familyName
                val personEmail = acct.email
                val personId = acct.id
                val serverAuthCode = acct.serverAuthCode
                val idToken = acct.idToken
                val personPhoto: Uri? = acct.photoUrl

                Log.d("idToken", idToken!!)
                Log.d("serverAuthCode", serverAuthCode!!)

                findNavController().navigate(R.id.action_homeFragment_to_dashBoardFragment)

                Toast.makeText(requireContext(), personEmail.toString(), Toast.LENGTH_SHORT).show()
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("SignInWithGoogleTAG", "signInResult:failed code=" + e.statusCode.toString())
        }
    }
}
