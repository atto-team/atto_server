package com.atto.nimontoy.jpa

import com.atto.nimontoy.util.now
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.MappedSuperclass

/**
 * Created by 00700mm@gmail.com on 2019-07-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @CreatedDate
    private var createdDate: LocalDateTime? = now()

    @LastModifiedDate
    private var lastModifiedDate: LocalDateTime? = now()
}