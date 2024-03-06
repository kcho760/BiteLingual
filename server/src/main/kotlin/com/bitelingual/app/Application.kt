package com.bitelingual.app

import Greeting
import SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.logging.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        // Route for image upload and food identification
        post("/api/identify") {
            // Placeholder: Extract image from request and use ML Kit for identification
            call.respondText("Food identified: [Food Name Here] hi olesya")
        }

        // Route for translating food names
        get("/api/translate/{foodName}") {
            val foodName = call.parameters["foodName"]
            // Placeholder: Translate foodName using Google Translate API
            call.respondText("Translated food name: [Translated Name Here]")
        }

        // Route for fetching user history
        get("/api/history") {
            // Placeholder: Retrieve and return user's history from database
            call.respondText("User history: [Details Here]")
        }

        // Route for adding an item to favorites
        post("/api/favorites") {
            // Placeholder: Extract food item details from request and add to favorites
            call.respondText("Food item added to favorites")
        }
    }
}
