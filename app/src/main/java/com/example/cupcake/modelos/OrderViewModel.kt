package com.example.cupcake.modelos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRECIO_POR_CUPCAKE = 2.00
private const val PRECIO_MISMO_DIA_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    val opcionesFecha = getOpcionesPickUp()

    private val _cantidad = MutableLiveData<Int>()
    val cantidad: LiveData<Int> = _cantidad

    private val _sabor = MutableLiveData<String>()
    val sabor: LiveData<String> = _sabor

    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    private val _precio = MutableLiveData<Double>()
    val precio: LiveData<String> = Transformations.map(_precio) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }

    public fun setCantidad(numeroCupcakes: Int) {
        _cantidad.value = numeroCupcakes
        actualizarPrecio()
    }

    public fun setSabor(sabor: String) {
        _sabor.value = sabor
    }

    public fun setFecha(fechaPickUp: String) {
        _fecha.value = fechaPickUp
        actualizarPrecio()
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

    private fun actualizarPrecio() {
        var precioCalculado = (cantidad.value ?: 0) * PRECIO_POR_CUPCAKE

        // Verificar si el usuario seleccionó el envío para el mismo dia
        if (opcionesFecha[0] == _fecha.value) {
            precioCalculado += PRECIO_MISMO_DIA_PICKUP
        }
        _precio.value = precioCalculado
    }

}