package com.gm.kotlin.jdbc.controller

import com.gm.kotlin.jdbc.domain.Blog
import com.gm.kotlin.jdbc.service.BlogService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BlogController(private val blogService: BlogService) {

    @GetMapping("/blogs")
    fun getAllBlogs(): Collection<Blog> = blogService.all()


    @PostMapping("/blogs")
    fun createNewBlog(@Valid @RequestBody blog: Blog): Blog? {
        blogService.insert(blog)
        return blogService.byId(blog.id!!)
    }


    @GetMapping("/blogs/{id}")
    fun getBlogById(@PathVariable(value = "id") blogId: Long): ResponseEntity<Blog> {

        val blog = blogService.byId(blogId)

        if (blog != null) return ok(blog) else return notFound().build<Blog>()

    }

}