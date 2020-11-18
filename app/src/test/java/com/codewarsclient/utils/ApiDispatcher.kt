package com.codewarsclient.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


class ApiDispatcher : Dispatcher() {

    @Throws(InterruptedException::class)
    override fun dispatch(request: RecordedRequest): MockResponse {
        when (request.path) {
            "/v1/users/wichu" -> return MockResponse().setResponseCode(200)
                .setBody(FileAccessUtil.readFileIntoString("member_wichu.json"))
            "/v1/users/AlexIsHappy" -> return MockResponse().setResponseCode(200)
                .setBody(FileAccessUtil.readFileIntoString("member_AlexIsHappy.json"))
            "/v1/users/MemberDoesNotExist" -> return MockResponse().setResponseCode(404)
            "/v1/users/g964" -> return MockResponse()
        }
        return MockResponse().setResponseCode(404)
    }

}