package com.cyberstarege.ruratesx

import app.softwork.routingcompose.HashRouter
import app.softwork.routingcompose.Router
import com.cyberstarege.ruratesx.components.MainContent
import com.cyberstarege.ruratesx.di.initKoin
import com.cyberstarege.ruratesx.pages.MainPage
import com.cyberstarege.ruratesx.pages.MainViewModel
import com.cyberstarege.ruratesx.styles.AppStylesheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        initKoin()
        Style(AppStylesheet)
        MainContent {
            HashRouter(initPath = "/") {
                val router = Router.current
                route("/") {
                    MainPage(mainViewModel = MainViewModel())
                }
            }
        }
    }
}