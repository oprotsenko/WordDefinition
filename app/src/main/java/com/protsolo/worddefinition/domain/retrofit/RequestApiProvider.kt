package com.protsolo.worddefinition.domain.retrofit

class RequestApiProvider(
    private val retrofitProvider: RetrofitProvider,
    private val BASE_URL: BaseUrl
) {
    fun getRequestApi(): IRequestApi =
        retrofitProvider.getRetrofit(BASE_URL.getBaseUrl()).create(IRequestApi::class.java)
}