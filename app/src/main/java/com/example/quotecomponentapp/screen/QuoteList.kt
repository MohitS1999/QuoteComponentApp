package com.example.quotecomponentapp.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quotecomponentapp.model.Quote

@Composable
fun QuoteList(data:Array<Quote>,onClick:(quote:Quote)->Unit){
    LazyColumn(content = {
        items(data){
            QuoteListItem(quote = it,onClick)
        }
    })
}