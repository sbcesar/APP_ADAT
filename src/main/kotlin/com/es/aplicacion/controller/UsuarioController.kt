package com.es.aplicacion.controller

import com.es.aplicacion.dto.LoginUsuarioDTO
import com.es.aplicacion.dto.UsuarioDTO
import com.es.aplicacion.dto.UsuarioRegisterDTO
import com.es.aplicacion.error.exception.NotFoundException
import com.es.aplicacion.error.exception.UnauthorizedException
import com.es.aplicacion.service.TokenService
import com.es.aplicacion.service.UsuarioService
import com.es.aplicacion.util.Utils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UsuarioController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var tokenService: TokenService
    @Autowired
    private lateinit var usuarioService: UsuarioService

    @GetMapping("/")
    fun getAllUsuarios(): String {
        return "Hola uwu"
    }

    @PostMapping("/register")
    fun insert(
        httpRequest: HttpServletRequest,
        @RequestBody usuarioRegisterDTO: UsuarioRegisterDTO
    ) : ResponseEntity<UsuarioDTO>?{

        Utils.verifyUserData(usuarioRegisterDTO)
        val newUser = usuarioService.insertUser(usuarioRegisterDTO)
        if (newUser == null) {
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        } else {
            return ResponseEntity(null, HttpStatus.CREATED)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario: LoginUsuarioDTO) : ResponseEntity<Any>? {

        val authentication: Authentication
        try {
            authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(usuario.username, usuario.password))
        } catch (e: AuthenticationException) {
            throw UnauthorizedException("Credenciales incorrectas")
        }

        // SI PASAMOS LA AUTENTICACIÓN, SIGNIFICA QUE ESTAMOS BIEN AUTENTICADOS
        // PASAMOS A GENERAR EL TOKEN
        var token = tokenService.generarToken(authentication)

        return ResponseEntity(mapOf("token" to token), HttpStatus.CREATED)
    }

}