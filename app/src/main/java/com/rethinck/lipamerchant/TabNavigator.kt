package com.rethinck.lipamerchant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.rethinck.lipamerchant.screen.payment.PaymentScreen
import com.rethinck.lipamerchant.screen.profile.ProfileScreen
import com.rethinck.lipamerchant.screen.transactions.TransactionsScreen

class TabNavigator : Screen {
    @Composable
    override fun Content() {
        TabNavigatorContent()
    }

}

object PayTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Paiement",
            icon = painterResource(id = R.drawable.income)
        )

    @Composable
    override fun Content() {
        Navigator(PaymentScreen())
    }
}

object TransactionsTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions (
            index = 1u,
            title = "Transactions",
            icon = painterResource(id = R.drawable.transactions)
        )

    @Composable
    override fun Content() {
        Navigator(TransactionsScreen())
    }
}

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Compte",
            icon = painterResource(id = R.drawable.user)
        )

    @Composable
    override fun Content() {
        Navigator(ProfileScreen())
    }
}

@Composable
fun TabNavigatorContent() {

    TabNavigator(tab = PayTab) {
        val tabNavigator = LocalTabNavigator.current
        Scaffold (
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(tab = PayTab)
                    TabNavigationItem(tab = TransactionsTab)
                    TabNavigationItem(tab = ProfileTab)
                }
            }
        ) {
            Box(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding())
            ) {
                tabNavigator.current.Content()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(painter = painter, contentDescription = tab.options.title, Modifier.padding(4.dp).size(18.dp), )
            }
        },
        label = {
            Text(text = tab.options.title)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,              // icon color when selected
            // unselectedIconColor = Color.Gray,             // icon color when NOT selected
            // selectedTextColor = Color.White,              // text color when selected
            // unselectedTextColor = Color.Gray,             // text color when NOT selected
            indicatorColor = Color(0xFF001C06)            // background behind selected item
        )
    )
}