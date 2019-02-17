package kr.co.connect.boostcamp.livewhere.ui.main

import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.connect.boostcamp.livewhere.data.entity.RecentSearchEntity
import kr.co.connect.boostcamp.livewhere.ui.main.adapter.AutoCompleteRecyclerViewAdapter
import kr.co.connect.boostcamp.livewhere.ui.main.adapter.RecentSearchRecyclerViewAdapter

@BindingAdapter("setRecentRecyclerViewItems")
fun setRecentRecyclerViewItems(recyclerView: RecyclerView, itemList: List<RecentSearchEntity>?) {
    if (!itemList.isNullOrEmpty()) {
        (recyclerView.adapter as RecentSearchRecyclerViewAdapter).setData(itemList)
    } else {
        // TODO 데이터 정보 없음 처리.
    }
}

@BindingAdapter("setAutoCompleteRecyclerViewItems")
fun setAutoCompleteRecyclerViewItems(recyclerView: RecyclerView, itemList: List<String>?) {
    if(!itemList.isNullOrEmpty()) {
        (recyclerView.adapter as AutoCompleteRecyclerViewAdapter).setData(itemList)
    } else {
        //TODO: 데이터 정보 없음 처리.
    }
}

@BindingAdapter("searchDone")
fun finishEntering(editText: EditText, viewModel: HomeViewModel) {
    editText.setOnKeyListener { _, keyCode, event ->
        if((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            //After Enter Key
            viewModel.onClickAutoComplete(editText.text.toString())
            return@setOnKeyListener true
        } else {
            return@setOnKeyListener false
        }
    }
}

@BindingAdapter("autoComplete")
fun autoComplete (editText: EditText, viewModel: HomeViewModel) {
    editText.doOnTextChanged { text, _, _, _ ->
        viewModel.getKakaoApi(text.toString())
    }
}