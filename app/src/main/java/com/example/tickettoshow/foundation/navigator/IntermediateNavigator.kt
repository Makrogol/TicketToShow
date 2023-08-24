package com.example.tickettoshow.foundation.navigator

import com.example.tickettoshow.foundation.tools.ResourceActions
import com.example.tickettoshow.foundation.views.BaseScreen

//Навигатор для работы на стороне вьюмодели

class IntermediateNavigator : Navigator {

    private val targetNavigator = ResourceActions<Navigator>()

    override fun launch(screen: BaseScreen) = targetNavigator {
        it.launch(screen)
    }

    fun setTarget(navigator: Navigator?) {
        targetNavigator.resource = navigator
    }

    fun clear() {
        targetNavigator.clear()
    }

}