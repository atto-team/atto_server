package com.atto.nimontoy.core.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Document
data class User(
    val name: String? = null
) : BaseDocument()

interface UserRepository : ReactiveMongoRepository<User, String>