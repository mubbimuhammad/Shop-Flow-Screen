package com.example.shopflowscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopflowscreen.data.PromotionData
import kotlinx.coroutines.launch


val tangerineFont = FontFamily(
    Font(R.font.tangerine_regular)
)

val darkBackground = Color(0xFF1A1A1A)
val darkCardBackground = Color(0xFF242424)
val darkSecondaryBackground = Color(0xFF333333)
val accentGreen = Color(0xFF8ACE00)
val starYellow = Color(0xFFFFC107)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowScreen() {
    val categories = listOf("Cleanser", "Toner", "Serums", "Moisturizers", "Sets", "Foundation", "Spots Remover")
    val promotions = listOf(
        PromotionData("GET 20% OFF", "Limited time offer", R.drawable.shopflowcard1),
        PromotionData("NEW ARRIVALS", "Check out our latest products", R.drawable.shopflowcard1),
        PromotionData("FREE SHIPPING", "On orders over Rs. 1000", R.drawable.shopflowcard1)
    )

    val pagerState = rememberPagerState(pageCount = { promotions.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = darkBackground,
        topBar = {
            ShopTopAppBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(darkBackground),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            PromotionBanner(promotions[page])
                        }


                        Row(
                            modifier = Modifier
                                .padding(start = Dp(90F))
                                .padding(0.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            repeat(promotions.size) { iteration ->
                                val color = if (pagerState.currentPage == iteration) accentGreen else Color.Black
                                Box(
                                    modifier = Modifier
                                        .width(if (pagerState.currentPage == iteration) 24.dp else 8.dp)
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(color)
                                        .clickable {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(iteration)
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
            }

            item {
                CategorySection(categories)
            }

            item {
                NewProductsSection()
            }

            item {
                ProductList()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Shop",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }

            BadgedBox(
                badge = {
                    Badge(containerColor = accentGreen) { Text("3") }
                }
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }
            }

            BadgedBox(
                badge = {
                    Badge(containerColor = accentGreen) { Text("2") }
                }
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = darkBackground
        )
    )
}

@Composable
fun PromotionBanner(promotion: PromotionData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = promotion.backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Text(
                text = promotion.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = promotion.description,
                color = Color.White,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = accentGreen),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = "SHOP NOW",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CategorySection(categories: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See all",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(darkCardBackground),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.categorysample),
                contentDescription = category,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category,
            color = Color.White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NewProductsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New products",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See all",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.clickable { }
            )
        }

    }
}

@Composable
fun ProductList() {
    val favoriteStates = remember { mutableStateMapOf<String, Boolean>() }

    val toggleFavorite: (String) -> Unit = { name ->
        favoriteStates[name] = !(favoriteStates[name] ?: false)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        listOf("eleucera", "glow", "afterglow").forEach { name ->
            ProductItem(
                name = name,
                description = "French clay and algae-powered cleanser",
                skinType = "Skin Tightness • Dry & Dehydrated Skin",
                price = "355.20",
                originalPrice = "444.00",
                rating = 4.9f,
                reviewCount = 249,
                inStock = true,
                stockCount = 10,
                imageRes = R.drawable.product_image,
                isFavorite = favoriteStates[name] ?: false,
                onToggleFavorite = { toggleFavorite(name) }
            )
        }
    }
}

@Composable
fun ProductItem(
    name: String,
    description: String,
    skinType: String,
    price: String,
    originalPrice: String,
    rating: Float,
    reviewCount: Int,
    inStock: Boolean,
    stockCount: Int,
    imageRes: Int,
    isFavorite: Boolean = false,
    onToggleFavorite: () -> Unit = {},
    onAddToCart: () -> Unit = {}
) {
    val accentGreen = Color(0xFF8DC63F)
    val starYellow = Color(0xFFFFC107)
    val darkBackground = Color.Black.copy(alpha = 0.7f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {

        Image(
            painter = painterResource(id = R.drawable.card_grey_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.85f),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(230.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )


                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(0.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.4f),
                            shape = CircleShape
                        )
                        .size(28.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) Color.Cyan else Color.White
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))


                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(12.dp))
                        .background(darkBackground)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Best seller", color = Color.White, fontSize = 10.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_black_shape),
                    contentDescription = null,
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = name,
                            color = accentGreen,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = tangerineFont
                        )

                        if (inStock) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(accentGreen)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("In stock", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = description, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Light)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = skinType, color = Color.White, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "RS. $price",
                                    color = Color.Cyan,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "RS. $originalPrice",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(5) { index ->
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = null,
                                        tint = if (index < rating.toInt()) starYellow else Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    "$reviewCount reviews",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(accentGreen)
                                .clickable { onAddToCart() },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cart3),
                                contentDescription = "Add to cart",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShopFlowPreview() {
    MaterialTheme {
        ShopFlowScreen()
    }
}