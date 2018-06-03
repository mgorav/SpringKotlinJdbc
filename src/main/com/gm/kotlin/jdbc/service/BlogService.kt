package com.gm.kotlin.jdbc.service

import com.gm.kotlin.jdbc.domain.Blog

interface BlogService {
    fun byId(id: Long): Blog?
    fun all(): Collection<Blog>
    fun insert(c: Blog)
}
