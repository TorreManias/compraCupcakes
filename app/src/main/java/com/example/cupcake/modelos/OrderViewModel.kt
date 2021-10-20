package com.example.cupcake.modelos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {

    val opcionesFecha = getOpcionesPickUp()

    private val _cantidad = MutableLiveData<Int>()
    val cantidad: LiveData<Int> = _cantidad

    private val _sabor = MutableLiveData<String>()
    val sabor: LiveData<String> = _sabor

    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    private val _precio = MutableLiveData<Double>()
    val precio: LiveData<Double> = _precio

    init {
        resetOrder()
    }

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

    private fun getOpcionesPickUp(): List<String> {
        val options = mutableListOf<String>()
        val formato = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendario = Calendar.getInstance()

        // Crear la lista de fechas
        repeat(4) {
            options.add(formato.format(calendario.time))
            calendario.add(Calendar.DATE, 1)
        }

        return options

    }

    fun resetOrder() {
        _cantidad.value = 0
        _sabor.value = ""
        _fecha.value = opcionesFecha[0]
        _precio.value = 0.0
    }

}