package com.atto.nimontoy.service.feed

import com.atto.nimontoy.dto.FeedCommentCreateRequest
import com.atto.nimontoy.dto.FeedCreateRequest
import com.atto.nimontoy.model.FeedCommentRepository
import com.atto.nimontoy.model.FeedRepository
import com.atto.nimontoy.model.User
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by 00700mm@gmail.com on 2019-08-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Service
@Transactional
class FeedService(
        private val feedRepository: FeedRepository,
        private val feedFindService: FeedFindService,
        private val feedCommentRepository: FeedCommentRepository
) {

    fun create(request: FeedCreateRequest) {
        feedRepository.save(request.toEntity())
    }

    fun like(id: Long, user: User) {
        feedFindService.findById(id).like(
                user
        )
    }

    fun createComment(id: Long, request: FeedCommentCreateRequest) {
        val comment = request.toEntity()
        feedFindService.findById(id).addComment(
                comment
        )

        feedCommentRepository.save(comment)
    }

}