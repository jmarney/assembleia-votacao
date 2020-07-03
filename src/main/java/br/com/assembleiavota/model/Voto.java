package br.com.assembleiavota.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tbl_voto")
@AllArgsConstructor
@NoArgsConstructor
public class Voto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "valido")
    private Boolean voto;

    @Column(name = "id_sessao")
    private Integer idSessao;

    @Column(name = "id_topico")
    private Integer idTopico;

}
