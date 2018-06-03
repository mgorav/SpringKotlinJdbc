package com.gm.kotlin.jdbc.domain

import org.jetbrains.exposed.sql.Table


data class Blog(val id: Long? = null, val title: String? = null, val content: String? = null)