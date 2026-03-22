package br.com.fiap.produtos_ms.utils;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class GitHubUserUtils {

    private GitHubUserUtils() {
    }

    public static String getUsername(OAuth2AuthenticationToken authentication) {
        return campoGithubUserValido(authentication, "login");
    }

    public static String getAvatar(OAuth2AuthenticationToken authentication) {
        return campoGithubUserValido(authentication, "avatar_url");
    }

    private static String campoGithubUserValido(OAuth2AuthenticationToken authentication, String campoDesejado){
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal().getAttribute(campoDesejado) != null){
            return authentication.getPrincipal().getAttribute(campoDesejado);
        }
        else {
            throw new IllegalArgumentException("O campo " + campoDesejado + " não foi fornecido pelo github");
        }
    }
}
