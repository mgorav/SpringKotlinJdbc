package com.gm.kotlin.jdbc.service.impl

import com.gm.kotlin.jdbc.domain.Blog
import com.gm.kotlin.jdbc.service.BlogService
import com.gm.kotlin.jdbc.table.BlogTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate

@Service
@Transactional
class ExposedBlogService(private val tt: TransactionTemplate) : BlogService, InitializingBean {

    override fun afterPropertiesSet() {
        tt.execute {
            SchemaUtils.create(BlogTable)
        }
    }

    override fun byId(id: Long): Blog? =
            BlogTable
                    .select {
                        BlogTable.id.eq(id)
                    }
                    .map {
                        Blog(id = it[BlogTable.id], title = it[BlogTable.title], content = it[BlogTable.content])
                    }
                    .singleOrNull()

    override fun all(): Collection<Blog> =
            BlogTable
                    .selectAll()
                    .map {
                        Blog(id = it[BlogTable.id], title = it[BlogTable.title], content = it[BlogTable.content])
                    }


    override fun insert(c: Blog) {
        BlogTable.insert {
            it[BlogTable.title] = c.title!!
            it[BlogTable.content] = c.content!!
        }
    }
}
