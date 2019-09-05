package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

/**
 * Created by 00700mm@gmail.com on 2019-09-02
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "collection_toys")
data class CollectionToy(
        @ManyToOne
        @JoinColumn(name = "collection_id")
        val collection: Collection,

        @ManyToOne
        @JoinColumn(name = "toy_id")
        val toy: Toy,

        // 코인 투입량
        val coinAmountCurrent: Int,

        // 코인 필요량
        val coinAmountRequired: Int
) : BaseEntity() {

    /**
     * 진행률 계산
     */
    fun progressCalculate() {
        TODO("진행률 계산 로직 구현")
    }
}

interface CollectionToyRepository : JpaRepository<CollectionToy, Long>