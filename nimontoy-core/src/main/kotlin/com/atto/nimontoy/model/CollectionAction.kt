package com.atto.nimontoy.model

import com.atto.nimontoy.jpa.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

/**
 * 해당 Collection의 모든 Action(트랜잭션성) 데이터
 *
 * Collection의 Action은 Coin이 있어야만 이루어진다.
 *
 * 1. 코인 투입 (Insert Coin)
 * 2. 투표 (Vote)
 * 3. 차후 추가될 무언가..
 *
 * Created by 00700mm@gmail.com on 2019-09-02
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Entity
@Table(name = "collection_actions")
data class CollectionAction(
        @Enumerated(EnumType.STRING)
        val type: CollectionActionType,
        @OneToOne
        @JoinColumn(name = "collection_id")
        val collection: Collection,
        @OneToOne
        @JoinColumn(name = "collection_toy_id")
        val collectionToy: CollectionToy,
        @OneToOne
        @JoinColumn(name = "user_id")
        val user: User,
        val coinAmount: Int
) : BaseEntity()

enum class CollectionActionType {
    INSERT_COIN,
    VOTE
}

interface CollectionActionRepository : JpaRepository<CollectionAction, Long>