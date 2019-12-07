package wooooooak.com.studyapp.data.model.datasource.local

import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.data.model.response.kin.Kin
import wooooooak.com.studyapp.data.model.response.movie.Movie

interface NaverLocalDataSource {
    suspend fun getBlogList(title: String, startIndex: Int?): List<Blog>

    suspend fun getImageList(title: String, startIndex: Int?): List<Image>

    suspend fun getMovieList(title: String, startIndex: Int?): List<Movie>

    suspend fun getKinList(title: String, startIndex: Int?): List<Kin>
}