package com.wyao.dribbbojetpackcompose.domain.use_case.get_access_token

import android.util.Log
import com.google.protobuf.InvalidProtocolBufferException
import com.wyao.dribbbojetpackcompose.common.Result
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke (accessCode: String): Flow<Result<AccessTokenDto>> = flow {
        try {
            emit(Result.Loading<AccessTokenDto>())
            val accessTokenDto = authRepository.fetchAccessToken(accessCode)
            emit(Result.Success<AccessTokenDto>(accessTokenDto))
        } catch (e: HttpException) {
            emit(Result.Error<AccessTokenDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Result.Error<AccessTokenDto>("Couldn't reach server. Check your internet connection."))
        } catch (e: InvalidProtocolBufferException) {
            emit(Result.Error<AccessTokenDto>(e.toString()))
        }
    }
}