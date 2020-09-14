package com.hexamass.votingknowledge.ui.contact

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.hexamass.votingknowledge.datasource.DataSource
import com.hexamass.votingknowledge.ext.getResult
import com.hexamass.votingknowledge.model.Contacts
import com.hexamass.votingknowledge.model.Response
import com.hexamass.votingknowledge.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import com.hexamass.votingknowledge.model.Result
import java.lang.Exception

class ContactViewModel @ViewModelInject constructor(dataSource: DataSource) :
    BaseViewModel(dataSource) {

    val contacts: MutableLiveData<Response<Contacts>> = MutableLiveData()

    init {
        getContacts()
    }

    fun getContacts() {
        launch {
            contacts.value = Response.Loading("loading")
            try {
                val result = dataSource.getContacts().getResult()
                when (result) {
                    is Result.Success -> {
                        contacts.value = Response.Done(result.data)
                    }
                    is Result.Error -> {
                        contacts.value = Response.Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                contacts.value = Response.Error(Exception("unknown error"))
            }
        }
    }

}