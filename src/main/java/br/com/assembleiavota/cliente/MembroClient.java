package br.com.cooperativism.client;

import br.com.cooperativism.dto.UserInfoDto;
import br.com.cooperativism.enums.UserStatusEnum;
import br.com.cooperativism.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class UserClient {

  private Logger logger = LoggerFactory.getLogger(UserClient.class);

  @Value("${api.user}")
  private String userUrl;

  public Mono<Boolean> isCpfValid(final String cpf) {
    logger.info("Verificando cpf do Associado: {}", cpf);

    return getWebClient()
        .get()
        .uri("/".concat(cpf))
        .accept(APPLICATION_JSON)
        .retrieve()
        .bodyToMono(UserInfoDto.class)
        .map(u -> {
          logger.info("Cpf verificado com sucesso - {} - status: {}", cpf, u.getStatus());
          if (UserStatusEnum.ABLE_TO_VOTE.equals(u.getStatus())) {
            return true;
          }
          throw new BusinessException("CPF informado inválido: ".concat(cpf));
        })
        .onErrorResume(err -> {
          logger.error("Erro ao verificar CPF do Associado");
          return Mono.just(false);
        });
  }

  private WebClient getWebClient() {
    return WebClient.create(userUrl);
  }

}
