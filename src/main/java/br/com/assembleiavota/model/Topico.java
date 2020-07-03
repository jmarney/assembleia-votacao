package br.com.assembleiavota.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "tbl_topico")
@AllArgsConstructor
@NoArgsConstructor
public class Topico {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
}
