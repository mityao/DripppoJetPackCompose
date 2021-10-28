package com.wyao.dribbbojetpackcompose.domain.use_case.user_login

import com.google.protobuf.InvalidProtocolBufferException
import com.wyao.dribbbojetpackcompose.data.remote.dto.AccessTokenDto
import com.wyao.dribbbojetpackcompose.domain.repository.DribbboRepository
import com.wyao.dribbbojetpackcompose.common.Result
import com.wyao.dribbbojetpackcompose.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(
    private val dribbboRepository: DribbboRepository
) {
    operator fun invoke (): Flow<Result<UserDto>> = flow {
        try {
            emit(Result.Loading<UserDto>())
            val userDto = dribbboRepository.login()
            emit(Result.Success<UserDto>(userDto))
        } catch (e: HttpException) {
            emit(Result.Error<UserDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Result.Error<UserDto>("Couldn't reach server. Check your internet connection."))
        } catch (e: InvalidProtocolBufferException) {
            emit(Result.Error<UserDto>(e.toString()))
        }
    }
}