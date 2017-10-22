package com.example.service;

import com.example.model.KeluargaModel;

public interface KeluargaService {
    KeluargaModel selectKeluarga (String nomor_kk);
    KeluargaModel getKeluarga(String id);
    void addKeluarga(KeluargaModel keluarga);
    void updateKeluarga(KeluargaModel keluarga);
}
