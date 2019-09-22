package com.atto.nimontoy.jpa

import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
fun <T, ID> JpaRepository<T, ID>.findOneOrNull(example: Example<T>): T? = findOne(example).orElse(null)