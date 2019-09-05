package com.atto.nimontoy.util

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
/* 현재 시간 */
fun now(): LocalDateTime = LocalDateTime.now()

/* 금일 */
fun nowDate(): LocalDate = LocalDate.now()