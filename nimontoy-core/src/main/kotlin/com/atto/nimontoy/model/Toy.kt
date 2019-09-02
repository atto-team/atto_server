package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by 00700mm@gmail.com on 2019-08-13
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "toys")
data class Toy(
        val name: String,
        val description: String,
        val thumbnail: String
) : BaseEntity()

interface ToyRepository : JpaRepository<Toy, Long>