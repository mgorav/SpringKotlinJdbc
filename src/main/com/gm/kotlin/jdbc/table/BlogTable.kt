package com.gm.kotlin.jdbc.table

import org.jetbrains.exposed.sql.Table

object BlogTable : Table() {
    val id = long("id").autoIncrement().primaryKey()
    val title = varchar("title", Integer.MAX_VALUE)
    val content = varchar("content", Integer.MAX_VALUE)
}