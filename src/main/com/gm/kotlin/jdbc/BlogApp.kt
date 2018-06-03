package com.gm.kotlin.jdbc

import com.gm.kotlin.jdbc.controller.BlogController
import com.gm.kotlin.jdbc.domain.Blog
import com.gm.kotlin.jdbc.service.BlogService
import com.google.common.base.Predicates
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.support.beans
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class BlogApp {}

fun main(args: Array<String>) {

    SpringApplicationBuilder()
            .sources(BlogApp::class.java)
            .initializers(beans {
                bean {
                    SpringTransactionManager(ref())
                }
                bean {
                    ApplicationRunner {

                        val blogService = ref<BlogService>()

                        arrayOf(Blog(title = "Sample title 1", content = "Sample content 1"),
                                Blog(title = "Sample title 2", content = "Sample content 1"))
                                .forEach { blogService.insert(it) }

                        blogService.all().forEach {
                            println(blogService.byId(it.id!!))
                        }

                    }
                }
                bean {
                    docklet()
                }
            })
            .run(*args)
}

fun docklet(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(BlogController::class.java!!.getPackage().getName()))
            .paths(Predicates.not(PathSelectors.regex("/error")))
            .build()
            .pathMapping("/")
            .apiInfo(apiInfo())
}

private fun apiInfo(): ApiInfo {

    val description = "Spring BOOT + Kotlin + JDBC"
    return ApiInfoBuilder()
            .title(description)
            .description(description)
            .license("GM")
            .licenseUrl("")
            .version("1.0")
            .build()

}