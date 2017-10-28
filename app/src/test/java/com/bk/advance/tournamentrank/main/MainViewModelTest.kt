package com.bk.advance.tournamentrank.main

import android.content.Context
import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.*

class MainViewModelTest{

    @Test
    fun shouldDisableNextForDefaultState(){
        val cut = MainViewModel()
        assertFalse(cut.nextEnabled.get())
    }

    @Test
    fun shouldEnableNextForValidInput(){
        val cut = MainViewModel()
        cut.playersCount.set("32")
        assertTrue(cut.nextEnabled.get())
    }

    @Test
    fun shouldChangeStateWhenInputNoLongerValid(){
        val cut = MainViewModel()
        cut.playersCount.set("32")
        assertTrue(cut.nextEnabled.get())
    }

    @Test
    fun shouldNotLaunchActivityIfDataNotValid(){
        val cut = MainViewModel()
        cut.playersCount.set("ee")

        val context = Mockito.mock(Context::class.java)
        cut.onNextClicked(context)

        verify(context, never()).startActivity(any())
    }

    @Test
    fun shouldLaunchActivityIfDataIsValid(){
        val cut = MainViewModel()
        cut.playersCount.set("ee")
        cut.playersCount.set("12")

        val context = Mockito.mock(Context::class.java)
        cut.onNextClicked(context)

        verify(context, atLeastOnce()).startActivity(any())
    }
}
