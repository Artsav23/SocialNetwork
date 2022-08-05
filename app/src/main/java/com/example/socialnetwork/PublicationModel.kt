package com.example.socialnetwork

import android.net.Uri
import java.io.Serializable

data class PublicationModel(val imageId: Uri, val comment: String): Serializable
