package com.atto.nimontoy.service.feed

import com.atto.nimontoy.dto.toResponse
import com.atto.nimontoy.model.QFeed.feed
import com.atto.nimontoy.model.QFeedComment.feedComment
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by 00700mm@gmail.com on 2019-09-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Service
@Transactional
class FeedQueryService(
        private val queryFactory: JPAQueryFactory
) {

    fun list() =
            queryFactory.selectFrom(feed)
                    .orderBy(feed.createdDate.desc())
                    .fetch()
                    .map {
                        it.toResponse()
                    }

    fun getOne(id: Long) =
            queryFactory.selectFrom(feed)
                    .where(feed.id.eq(id))
                    .fetchOne()
                    ?.toResponse()

    fun commentList(id: Long) =
            queryFactory.selectFrom(feedComment)
                    .innerJoin(feedComment).on(feedComment.feed.eq(feed))
                    .where(feed.id.eq(id))
                    .fetch()
                    .map {
                        it.toResponse()
                    }

}