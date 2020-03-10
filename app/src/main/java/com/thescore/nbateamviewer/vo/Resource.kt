package com.squareup.assignment.vo

import com.squareup.assignment.vo.Status.ERROR
import com.squareup.assignment.vo.Status.LOADING
import com.squareup.assignment.vo.Status.SUCCESS

/**
 * Created by Kanghee Lee
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}