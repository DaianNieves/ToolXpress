package com.example.toolxpress.payments

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.toolxpress.R
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.wallet.contract.TaskResultContracts.GetPaymentDataResult

class CheckoutActivity : ComponentActivity() {

    private val paymentDataLauncher = registerForActivityResult(GetPaymentDataResult()) { taskResult ->
        when (taskResult.status.statusCode) {
            CommonStatusCodes.SUCCESS -> {
                taskResult.result!!.let {
                    Log.i("Google Pay result:", it.toJson())
                    model.setPaymentData(it)
                }
            }
        }
    }

    private val model: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Log para asegurarnos que la actividad se inicia correctamente
        Log.d("CheckoutActivity", "onCreate - Activity started")

        setContent {
            val payState: PaymentUiState by model.paymentUiState.collectAsStateWithLifecycle()
            // Usando un layout simple solo con el botón de Google Pay
            SimpleCheckoutLayout(
                payUiState = payState,
                onGooglePayButtonClick = this::requestPayment,
            )
        }
    }

    private fun requestPayment() {
        try {
            val task = model.getLoadPaymentDataTask(priceCents = 7000L)
            task.addOnCompleteListener(paymentDataLauncher::launch)
        } catch (e: Exception) {
            Log.e("CheckoutActivity", "Error en requestPayment: ${e.message}")
        }
    }
}