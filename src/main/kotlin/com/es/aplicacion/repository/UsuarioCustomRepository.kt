package com.es.aplicacion.repository

import com.es.aplicacion.model.Usuario

interface UsuarioCustomRepository {
    fun findByCiudad(ciudad: String): List<Usuario>

    fun findByMunicipio(municipio: String): List<Usuario>

    fun findByActivity(estado: Boolean): List<String>
}
