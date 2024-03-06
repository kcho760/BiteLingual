package com.bitelingual.app

import SERVER_PORT
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        // Route for image upload and food identification
        post("/api/identify") {
            // Make sure the request is a multipart request
            val multipart = call.receiveMultipart()
            var imageName: String? = null
            var imageBytes: ByteArray? = null

            // Process each part of the multipart input
            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        // Get file name and bytes
                        imageName = part.originalFileName as String
                        imageBytes = part.streamProvider().readBytes()
                        // Optionally, save the file to disk or a temporary location
                    }

                    is PartData.BinaryChannelItem -> TODO()
                    is PartData.BinaryItem -> TODO()
                    is PartData.FormItem -> TODO()
                }
                // Don't forget to dispose of the part to avoid memory leaks
                part.dispose()
            }

            if (imageBytes != null) {
                // Here, you'd process the imageBytes with your ML Kit for food identification
                // For demonstration, just respond that the image was received
                call.respondText("Received image: $imageName")
            } else {
                call.respondText("No image received", status = HttpStatusCode.BadRequest)
            }
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
