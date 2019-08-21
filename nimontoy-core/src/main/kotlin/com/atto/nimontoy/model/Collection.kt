package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.OneToMany

/**
 * Created by 00700mm@gmail.com on 2019-08-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
data class Collection(
        val title: String,
        val season: Season,
        @OneToMany(mappedBy = "collection")
        val toys: MutableSet<Toy> = mutableSetOf()
) : BaseEntity()

data class CollectionBlind(
        val collection: Collection,
        val user: User,
        val toy: Toy,
        val coinAmount: Int,
        @CreatedDate
        val createdDate: LocalDateTime
)

data class CollectionVote(
        val collection: Collection,
        val user: User,
        val toy: Toy,
        val coinAmount: Int,
        @CreatedDate
        val createdDate: LocalDateTime
)
// votes
// collection_id, toy_id
// 총 100코인이 필요한데..
// 50코인을 집어넣으면? 50%


enum class Season {
    SUMMER
}