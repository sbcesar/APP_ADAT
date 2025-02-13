package com.es.aplicacion.util

import com.es.aplicacion.dto.UsuarioRegisterDTO
import com.es.aplicacion.error.exception.ValidationException

object Utils {

    fun verifyUserData(usuarioRegisterDTO: UsuarioRegisterDTO) {
        if (usuarioRegisterDTO.username.isBlank()) {
            throw ValidationException("Username cannot be blank!")
        }

        if (usuarioRegisterDTO.password.isBlank()) {
            throw ValidationException("Password cannot be blank!")
        }

        if (usuarioRegisterDTO.email.isBlank()) {
            throw ValidationException("Email cannot be blank!")
        }

        if (usuarioRegisterDTO.rol == null) {
            throw ValidationException("You need to insert a valid rol!")
        }
    }
}