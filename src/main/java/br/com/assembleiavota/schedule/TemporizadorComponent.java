package br.com.assembleiavota.schedule;

import br.com.assembleiavota.dto.SessaoDto;
import br.com.assembleiavota.service.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemporizadorComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporizadorComponent.class);
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private SessaoService sessaoService;

    @Scheduled(cron = "5 * * * * *", zone = TIME_ZONE)
    private void duracao() {
        LOGGER.debug("Iniciando contagem...");
        List<SessaoDto> listSessoes = sessaoService.buscarSessoesEmAndamento();
        LOGGER.debug("Existem {} sessoes abertas", listSessoes.size());
        listSessoes.forEach(sessoesDto -> {
            LOGGER.debug("A sessao {} foi encerrada", sessoesDto.getId());
            if (sessoesDto.getAtiva()) {
                sessaoService.finalizarSessao(sessoesDto);
            }
        });
    }

}
