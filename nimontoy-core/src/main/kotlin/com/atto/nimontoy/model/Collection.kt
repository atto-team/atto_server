package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by 00700mm@gmail.com on 2019-08-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "collections")
data class Collection(
        val season: Season,
        val name: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @ManyToOne
        val creator: User,
        @OneToMany(mappedBy = "collection")
        val collectionToys: List<CollectionToy> = mutableListOf()
) : BaseEntity()

enum class Season {
    SUMMER
}

interface CollectionRepository : JpaRepository<Collection, Long>