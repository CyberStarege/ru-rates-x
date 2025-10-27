package com.cyberstarege.ruratesx.components.card

import androidx.compose.runtime.Composable
import com.rieg.ruratesx.domain.model.WorkshopItem
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text

@Composable
fun WorkshopCard(workshopItem: WorkshopItem) {
    Card {
        Div {
            H4 {
                Text(workshopItem.title)
            }
        }
    }
}

