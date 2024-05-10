
package com.example.startprojeect.domain

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateViewModel:ViewModel() {
    private  var _arrow:MutableLiveData<Boolean> = MutableLiveData()
    private var _cardvisible:MutableLiveData<Boolean> = MutableLiveData()
    private var _hambugerr: MutableLiveData<Boolean> = MutableLiveData()
    private var _shoppingbag: MutableLiveData<Boolean> = MutableLiveData()
    private var _menu: MutableLiveData<Boolean> = MutableLiveData()
    private var _allUpperMenu: MutableLiveData<Boolean> = MutableLiveData()
    private var _text: MutableLiveData<AppCompatTextView> = MutableLiveData()
    private var _heart: MutableLiveData<Boolean> = MutableLiveData()
    private var _textReady: MutableLiveData<AppCompatTextView> = MutableLiveData()


    val arrow: LiveData<Boolean> = _arrow
    val cardvisible:LiveData<Boolean> = _cardvisible
    val hambugerr: LiveData<Boolean> = _hambugerr
    val shoppingbag: LiveData<Boolean> = _shoppingbag
    val menu: LiveData<Boolean> = _menu
    val alluppermenu: LiveData<Boolean> = _allUpperMenu
    val text : LiveData<AppCompatTextView> = _text
    val heart: LiveData<Boolean> = _heart
    val textReady: LiveData<AppCompatTextView> = _textReady
    init {
        _arrow.value = false
        _cardvisible.value = false
        _hambugerr.value = false
        _shoppingbag.value = false
        _menu.value = false
        _allUpperMenu.value = false
        _text.value?.text = "test"
        _heart.value = false
        _textReady.value?.text ="test"
    }
    fun readyText(out_text: String){
        _textReady.value?.text =out_text
    }
    fun setText(out_text: String){
        _text.value?.text = out_text
    }
    fun heartVisibility(yesno: Boolean){
        _heart.value = yesno
    }
    fun upperMenuVIsibility(yesno: Boolean){
        _allUpperMenu.value = yesno
    }
     fun arrowVisibility(yesno: Boolean){
        _arrow.value = yesno
    }
     fun cardVisibility(yesno: Boolean){
        _cardvisible.value = yesno
    }
     fun hamburgerVisibility(yesno: Boolean){
        _hambugerr.value = yesno
    }
     fun shoppingVisibility(yesno: Boolean){
        _shoppingbag.value = yesno
    }
    fun menuVisibility(yesno: Boolean){
        _menu.value = yesno
    }
}