package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.local.demo.Demo
import com.imaginato.homeworkmvvm.data.local.demo.DemoDatabase
import com.imaginato.homeworkmvvm.data.remote.demo.DemoRepository
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class LoginActivityViewModel : BaseViewModel() {

    private val repository: DemoRepository by inject()
    private val database: DemoDatabase by inject()
    private var _retrofitResponseLiveData: MutableLiveData<retrofit2.Response<LoginResponse>> = MutableLiveData()
    private var _loginResponseLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    private var _progress: MutableLiveData<Boolean> = MutableLiveData()

    val progress: LiveData<Boolean>
        get() {
            return _progress
        }

    val resultLiveData: LiveData<LoginResponse>
        get() {
            return _loginResponseLiveData
        }

    /**
     * Login Function
     * */
    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(username, password)
                .onStart {
                    _progress.value = true
                }.catch {
                    _progress.value = false
                }
                .onCompletion {
                }.collect {
                    _progress.value = false
                    _retrofitResponseLiveData.value = it
                    _loginResponseLiveData.value = _retrofitResponseLiveData.value!!.body()
                    if (_loginResponseLiveData.value!!.errorCode == "00") {
//                        database.demoDao.deleteAll()
                        database.demoDao.insertDemo(
                            Demo(
                                _loginResponseLiveData.value!!.data.userId,
                                _retrofitResponseLiveData.value!!.headers()["X-Acc"]!!,
                                _loginResponseLiveData.value!!.data.userId,
                                _loginResponseLiveData.value!!.data.userName,
                                _loginResponseLiveData.value!!.data.isDeleted
                            )
                        )
                    }
                }
        }
    }
}