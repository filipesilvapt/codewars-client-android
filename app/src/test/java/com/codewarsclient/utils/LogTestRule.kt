package com.codewarsclient.utils

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class LogTestRule : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

}