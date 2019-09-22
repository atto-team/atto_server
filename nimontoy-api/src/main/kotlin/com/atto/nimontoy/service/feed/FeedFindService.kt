package com.atto.nimontoy.service.feed

import com.atto.nimontoy.exception.ResourceNotFoundException
import com.atto.nimontoy.model.FeedRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * Created by 00700mm@gmail.com on 2019-09-22
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@Service
@Transactional
class FeedFindService(
        private val feedRepository: FeedRepository
) {

    fun findById(id: Long) =
            feedRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException()

}