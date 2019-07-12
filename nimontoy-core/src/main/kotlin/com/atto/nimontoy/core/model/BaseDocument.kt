package com.atto.nimontoy.core.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
abstract class BaseDocument {
    @Id
    var id: String? = null

    @CreatedDate
    var createdDate: LocalDateTime? = LocalDateTime.now()
}