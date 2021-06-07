package com.br.naia.cadastropessoa.service;

import com.br.naia.cadastropessoa.entity.ContatoEntity;
import com.br.naia.cadastropessoa.exception.EmailInvalidoException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class ContatoService {
    public void validarContatos(List<ContatoEntity> contatoEntities) {
        Assert.notEmpty(contatoEntities, "É obrigatorio pelo menos um contato");
        contatoEntities.forEach(contatoEntity -> {
            Assert.hasLength(contatoEntity.getNome(), "Nome do contato deve estar preenchido");
            Assert.notNull(contatoEntity.getEmail(), "E-mail do contato deve estar preenchido");
            Assert.hasLength(contatoEntity.getTelefone(), "Telefone do contato deve estar preenchido");
            validarEmail(contatoEntity.getEmail());
        });
    }

    private void validarEmail(String email) {
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException ex) {
            throw new EmailInvalidoException();
        }
    }
}
