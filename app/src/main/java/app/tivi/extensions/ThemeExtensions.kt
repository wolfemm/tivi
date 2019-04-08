/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.content.res.use

@SuppressLint("Recycle")
fun Context.resolveThemeColor(@AttrRes resId: Int, defaultColor: Int = Color.MAGENTA): Int {
    return obtainStyledAttributes(intArrayOf(resId)).use {
        it.getColor(0, defaultColor)
    }
}

@SuppressLint("Recycle")
fun Context.resolveThemeColorStateList(@AttrRes resId: Int): ColorStateList? {
    return obtainStyledAttributes(intArrayOf(resId)).use {
        it.getColorStateList(0)
    }
}

@SuppressLint("Recycle")
fun Context.resolveThemeReferenceResId(@AttrRes resId: Int): Int {
    return obtainStyledAttributes(intArrayOf(resId)).use {
        it.getResourceIdOrThrow(0)
    }
}