package com.example.toolxpress.payments

import com.google.android.gms.wallet.WalletConstants

object Constants {

    const val PAYMENTS_ENVIRONMENT = WalletConstants.ENVIRONMENT_TEST

    val SUPPORTED_NETWORKS = listOf(
        "AMEX",
        "DISCOVER",
        "JCB",
        "MASTERCARD",
        "VISA")

    val SUPPORTED_METHODS = listOf(
        "PAN_ONLY",
        "CRYPTOGRAM_3DS")

    const val COUNTRY_CODE = "MX"

    const val CURRENCY_CODE = "MXN"

    val SHIPPING_SUPPORTED_COUNTRIES = listOf("MX", "US", "GB")

    private const val PAYMENT_GATEWAY_TOKENIZATION_NAME = "example"

    val PAYMENT_GATEWAY_TOKENIZATION_PARAMETERS = mapOf(
        "gateway" to PAYMENT_GATEWAY_TOKENIZATION_NAME,
        "gatewayMerchantId" to "exampleGatewayMerchantId"
    )

    const val DIRECT_TOKENIZATION_PUBLIC_KEY = "REPLACE_ME"

    val DIRECT_TOKENIZATION_PARAMETERS = mapOf(
        "protocolVersion" to "ECv1",
        "publicKey" to DIRECT_TOKENIZATION_PUBLIC_KEY
    )
}