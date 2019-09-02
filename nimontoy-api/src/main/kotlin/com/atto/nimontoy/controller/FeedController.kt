package com.atto.nimontoy.controller

import com.atto.nimontoy.api.ApiResponse
import com.atto.nimontoy.api.BaseResponse
import com.atto.nimontoy.api.Type
import com.atto.nimontoy.dto.FeedCreateRequest
import com.atto.nimontoy.dto.FeedResponse
import com.atto.nimontoy.jpa.PageRequest
import com.atto.nimontoy.model.Feed
import com.atto.nimontoy.model.FeedCommentRepository
import com.atto.nimontoy.model.FeedRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

/**
 * Created by 00700mm@gmail.com on 2019-08-15
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@RestController
@RequestMapping("/v1/feeds")
class FeedController(
        private val feedRepository: FeedRepository,
        private val feedCommentRepository: FeedCommentRepository
) {

    @GetMapping
    fun list(pageRequest: PageRequest) =
            feedRepository.findAll(pageRequest.of())

    @GetMapping("/{id}")
    fun getFeed(@PathVariable id: Long) =
            feedRepository.findById(id)
                    .map {
                        ApiResponse(
                                data = BaseResponse(
                                        id = id,
                                        type = Type.FEED_LIST,
                                        scheme = UriComponentsBuilder
                                                .fromUriString("/v1/feeds/{id}")
                                                .buildAndExpand(id)
                                                .toUriString(),
                                        attributes = FeedResponse.of(it)
                                )
                        )
                    }

    @PostMapping
    fun createFeed(
            @RequestBody @Valid feedCreateRequest: FeedCreateRequest
    ) =
            feedRepository.save(
                    Feed(
                            title = feedCreateRequest.title,
                            contents = feedCreateRequest.contents
                    )
            )

}