package com.alves.vetflow.dto.response;

public record RefreshTokenResponse(
        String accessToken,
        String refreshToken
) {
}
