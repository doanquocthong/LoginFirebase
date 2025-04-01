package gaur.himanshu.login

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.credentials.CredentialManager
import androidx.credentials.CredentialOption
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.example.loginfirebase.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleSignInUtils {
    companion object {
        private const val TAG = "GoogleSignInUtils"

        fun doGoogleSignIn(
            context: Context,
            scope: CoroutineScope,
            launcher: ManagedActivityResultLauncher<Intent, ActivityResult>?,
            login: () -> Unit
        ) {
            val credentialManager = CredentialManager.create(context)

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(getCredentialOptions(context))
                .build()

            scope.launch {
                try {
                    Log.d(TAG, "Starting Google sign-in request...")
                    val result = credentialManager.getCredential(context, request)

                    when (result.credential) {
                        is CustomCredential -> {
                            if (result.credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                                val googleIdTokenCredential =
                                    GoogleIdTokenCredential.createFrom(result.credential.data)
                                val googleTokenId = googleIdTokenCredential.idToken
                                Log.d(TAG, "Received Google ID Token: $googleTokenId")

                                val authCredential =
                                    GoogleAuthProvider.getCredential(googleTokenId, null)
                                val user =
                                    Firebase.auth.signInWithCredential(authCredential).await().user

                                user?.let {
                                    Log.d(TAG, "User signed in: ${it.uid}")
                                    if (!it.isAnonymous) {
                                        login.invoke()
                                    }
                                } ?: Log.e(TAG, "User is null after signing in")
                            }
                        }

                        else -> Log.e(TAG, "Unexpected credential type: ${result.credential.type}")
                    }
                } catch (e: NoCredentialException) {
                    Log.w(TAG, "No credentials found, launching sign-in intent.")
                    launcher?.launch(getIntent())
                } catch (e: GetCredentialException) {
                    Log.e(TAG, "Error while retrieving credentials: ${e.message}", e)
                }
            }
        }

        fun signOut(context: Context, onComplete: () -> Unit = {}) {
            Log.d(TAG, "Signing out from Firebase...")
            Firebase.auth.signOut()

            val googleSignInClient = com.google.android.gms.auth.api.identity.Identity.getSignInClient(context)

            googleSignInClient.signOut().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Successfully signed out from Google Sign-In.")
                } else {
                    Log.e(TAG, "Failed to sign out from Google Sign-In.", task.exception)
                }
                onComplete.invoke()
            }
        }

        private fun getIntent(): Intent {
            return Intent(Settings.ACTION_ADD_ACCOUNT).apply {
                putExtra(Settings.EXTRA_ACCOUNT_TYPES, arrayOf("com.google"))
            }
        }

        private fun getCredentialOptions(context: Context): CredentialOption {
            return GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setAutoSelectEnabled(false)
                .setServerClientId(context.getString(R.string.google_cloud_server_client_id))
                .build()
        }
    }
}
