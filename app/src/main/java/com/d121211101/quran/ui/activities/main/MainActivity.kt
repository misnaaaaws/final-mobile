package com.d121211101.quran.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d121211101.quran.data.models.Data
import com.d121211101.quran.ui.activities.detail.DetailActivity
import com.d121211101.quran.ui.theme.Pink80
import com.d121211101.quran.ui.theme.QuranAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.padding(16.dp),
                            title = {
                                Text(
                                    text = "Quran App",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 32.sp
                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListSurahScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }
}

@Composable
fun ListSurahScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (mainUiState) {
            is MainUiState.Loading -> Text(text = "Loading...", fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            is MainUiState.Error -> Text(text = "Error Found", fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            is MainUiState.Success -> SurahList(
                surahs = mainUiState.surahs,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SurahList(surahs: List<Data>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(surahs){data ->
            SurahItems(surah = data)
        }
    }
}

@Composable
fun SurahItems(surah: Data){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
            .background(color = Pink80)
            .clickable {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("SURAH", surah)
                context.startActivity(intent)
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = surah.englishName.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = surah.name.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .weight(1f)
                )
            }

        }
    }
}
