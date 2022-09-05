package com.example.androiddi.utils

object Keys {

    init {
        System.loadLibrary("keys")
    }
    external fun getPublicKey(): String?
    external fun getPrivateKey(): String?
}