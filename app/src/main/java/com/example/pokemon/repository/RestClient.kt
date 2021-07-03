package com.example.pokemon.repository

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

public class RestClient {
    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit!!.create(serviceClass)
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(HeaderInterceptor())
                .connectionSpecs(connectionSpecs) //Secuity
                .build()
    }//CREDSYSTEM usa essa!

    //Secuity
    private val connectionSpecs: List<ConnectionSpec>
        private get() {
            //Secuity
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_0)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,  //CREDSYSTEM usa essa!
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
                    .build()
            return listOf(spec)
        }

    private inner class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val builder = original.newBuilder()
                    .header("User-Agent", "android")
            val request = builder.method(original.method(), original.body()).build()
            return chain.proceed(request)
        }
    }


    inner class ConnectivityException : IOException //RuntimeException
    {
        constructor() {}
        constructor(message: String?) : super(message) {}
        constructor(cause: Throwable?) : super(cause) {}
        constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    }

    companion object {
        private val BASE_URL = "https://pokeapi.co/api/"
        private var retrofit: Retrofit? = null
    }

    init {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(initClient())
                    .build()
        }
    }
}