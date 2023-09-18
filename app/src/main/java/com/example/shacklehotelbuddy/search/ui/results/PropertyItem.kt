package com.example.shacklehotelbuddy.search.ui.results


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.data.repository.model.Property
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyItem(
    property: Property
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        GlideImage(
            model = property.imgUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = property.name,
                    style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = property.location,
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText,
                    maxLines = 10,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${property.price} night",
                    style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = property.rating.toString(),
                    style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                )
            }
        }
    }
}
