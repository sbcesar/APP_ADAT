package com.es.aplicacion.error.exception

class ValidationException(message: String) : Exception("Validation error (400) $message") {
}