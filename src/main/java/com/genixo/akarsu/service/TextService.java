package com.genixo.akarsu.service;

import com.genixo.akarsu.domain.Text;
import com.genixo.akarsu.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TextService {
    final TextRepository repository;

    public List<Text> findAll() {
        return repository.findAll();
    }
    public Text findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Text add(Text text) {
        return repository.saveAndFlush(text);
    }
    public Text update(Text text) {
        return repository.saveAndFlush(text);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    /*
 metinler
    metinlerEkle
    metinlerGuncelle
    metinlerPasif
    metinlerSil
    metinlerTek
    metinlerTumu
     */
}
