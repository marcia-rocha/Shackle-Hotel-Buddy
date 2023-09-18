package com.example.shacklehotelbuddy.search.ui.search

import DestinationRoutes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shacklehotelbuddy.search.model.SearchQuery
import com.example.shacklehotelbuddy.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.commoncore.formatDate
import com.example.shacklehotelbuddy.search.ui.HorizontalDivider
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchQueryItem(
    recentSearch: SearchQuery,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .height(48.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.apply {
                set("searchQuery", recentSearch)
            }
            navController.navigate(DestinationRoutes.PropertyListScreen.route)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.manage_history),
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${formatDate(recentSearch.checkInDateMillis)} - ${formatDate(recentSearch.checkInDateMillis)}    ${recentSearch.adults} adult, ${recentSearch.children} children",
                style = ShackleHotelBuddyTheme.typography.caption,
                color = ShackleHotelBuddyTheme.colors.grayText,
                maxLines = 1
            )
        }
    }
}