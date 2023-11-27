package com.example.quotecomponentapp

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quotecomponentapp.screen.QuoteDetail
import com.example.quotecomponentapp.screen.QuoteList
import com.example.quotecomponentapp.screen.QuoteListScreen
import com.example.quotecomponentapp.ui.theme.QuoteComponentAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            DataManager.loadAssetsFromFile(applicationContext)
        }

        setContent{
            App()
        }
    }
}
@Composable
fun App(){
    if(DataManager.isDataLoaded.value){
        if(DataManager.currentPage.value == Pages.LISTING){
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPages(it)
            }
        }else{
            DataManager.currentQuote?.let { QuoteDetail(quote = it) }
        }

    }else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text(text = "Loading...",
                style = MaterialTheme.typography.headlineMedium
            )

        }
    }
}

enum class Pages{
    LISTING,
    DETAIL
}