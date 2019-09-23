package com.atto.nimontoy.controller

import com.atto.nimontoy.api.ok
import com.atto.nimontoy.dto.FeedCommentCreateRequest
import com.atto.nimontoy.dto.FeedCreateRequest
import com.atto.nimontoy.dto.FeedResponse
import com.atto.nimontoy.jpa.PageRequest
import com.atto.nimontoy.service.feed.FeedQueryService
import com.atto.nimontoy.service.feed.FeedService
import io.swagger.annotations.ApiResponse
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by 00700mm@gmail.com on 2019-08-15
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@RestController
@RequestMapping("/v1/feeds")
class FeedController(
        private val feedQueryService: FeedQueryService,
        private val feedService: FeedService
) {

    @GetMapping
    fun list(pageRequest: PageRequest) =
            ok(feedQueryService.list())

    @GetMapping("/{id}")
    fun getFeed(@PathVariable id: Long) {

    }

    @PostMapping
    fun createFeed(
            @RequestBody @Valid request: FeedCreateRequest
    ) {
        feedService.create(request)
    }

    @PostMapping("/{id}/like")
    fun feedLike() {

    }

    @PostMapping("/{id}/share")
    fun feedShare() {

    }

    @GetMapping("/{id}/comments")
    fun getFeedComments(@PathVariable id: Long) =
            ok(feedQueryService.commentList(id))

    @PostMapping("/{id}/comments")
    fun createFeedComment(@PathVariable id: Long,
                          @RequestBody @Valid request: FeedCommentCreateRequest
    ) {
        feedService.createComment(id, request)
    }

}