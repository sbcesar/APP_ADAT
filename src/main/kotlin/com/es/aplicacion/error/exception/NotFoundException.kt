package com.es.aplicacion.error.exception

class NotFoundException(message: String) : Exception("Not found error (404) -> ($message)") {
}