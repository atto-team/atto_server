package com.atto.nimontoy.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass


/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
/** Slf4j Logger 를 가져옵니다 */
fun loggerOf(name: String): Logger = LoggerFactory.getLogger(name)

/** Slf4j Logger 를 가져옵니다 */
fun loggerOf(clazz: KClass<*>): Logger = LoggerFactory.getLogger(clazz.java)

/** Slf4j Logger 를 가져옵니다 */
fun loggerOf(clazz: Class<*>): Logger = LoggerFactory.getLogger(clazz)
