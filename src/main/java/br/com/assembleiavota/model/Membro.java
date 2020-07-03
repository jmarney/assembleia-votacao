package br.com.assembleiavota.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tbl_membro")
@AllArgsConstructor
@NoArgsConstructor
public class Membro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "id_topico")
    private Integer idTopico;

    @Column(name = "cpf")
    private String cpf;
}
