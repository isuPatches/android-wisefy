package com.isupatches.wisefysample.ui.remove

import com.isupatches.wisefysample.TestRxSchedulersProvider

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify

import org.junit.After
import org.junit.Before
import org.junit.Test

class RemovePresenterTest {

    private val view = mock<RemoveNetworkMvp.View>()
    private val model = mock<RemoveNetworkMvp.Model>()

    private val presenter = RemoveNetworkPresenter(model, TestRxSchedulersProvider())

    companion object {
        private const val NETWORK = "Test SSID"
    }

    @Before fun setUp() {
        presenter.attachView(view)
    }

    @After fun tearDown() {
        presenter.detachView()
    }

    @Test fun removeNetwork() {
        presenter.removeNetwork(NETWORK)
        verify(model, times(1)).removeNetwork(eq(NETWORK), any())
    }
}