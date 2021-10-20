package com.example.cupcake.modelos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _cantidad = MutableLiveData<Int>(0)
    val cantidad: LiveData<Int> = _cantidad

    private val _sabor = MutableLiveData<String>("")
    val sabor: LiveData<String> = _sabor

    private val _fecha = MutableLiveData<String>("")
    val fecha: LiveData<String> = _fecha

    private val _precio = MutableLiveData<Double>(0.0)
    val precio: LiveData<Double> = _precio

    public fun setCantidad(numeroCupcakes: Int) {
        _cantidad.value = numeroCupcakes
    }

    public fun setSabor(sabor: String) {
        _sabor.value = sabor
    }

    public fun setFecha(fechaPickUp: String) {
        _fecha.value = fechaPickUp
    }

    public fun noHaySaborConfigurado() : Boolean {
        return _sabor.value.isNullOrEmpty()
    }

}