/*
 * Copyright 2018 Google, Inc.
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

package app.tivi.calls

import app.tivi.data.daos.FollowedShowsDao
import app.tivi.util.AppCoroutineDispatchers
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class SyncShowWatchedEpisodesCall @Inject constructor(
    private val syncer: TraktEpisodeWatchSyncer,
    private val followedShowsDao: FollowedShowsDao,
    private val dispatchers: AppCoroutineDispatchers
) : Call<Long> {
    override suspend fun doWork(showId: Long) {
        val followedEntry = withContext(dispatchers.database) {
            followedShowsDao.entryWithShowId(showId)
        } ?: throw IllegalArgumentException("Followed entry with id: $showId does not exist")
        val show = followedEntry.show!!

        syncer.sync(show.id!!)
    }
}