package com.atto.nimontoy.api

interface Pagination {
    val number: Int
    val numberOfElements: Int

    fun isEmpty(): Boolean
    fun isFirst(): Boolean
    fun isLast(): Boolean


    /**
     *   "pagination": {
    "empty": true,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 0,
    "pageable": {
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 0,
    "paged": true,
    "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
    },
    "unpaged": true
    },
    "size": 0,
    "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
    },
    "totalElements": 0,
    "totalPages": 0
     */

}
