package com.atto.nimontoy.jpa

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Created by 00700mm@gmail.com on 2019-08-07
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@ConfigurationProperties(prefix = "datasource")
class DataSourceProperties {
    lateinit var url: String
    lateinit var db: String
    lateinit var options: String
    lateinit var username: String
    lateinit var password: String
}