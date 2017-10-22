package com.example.service;
import com.example.model.PendudukModel;

public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    void addPenduduk(PendudukModel penduduk);
    void updatePenduduk(PendudukModel penduduk);
    void setWafat(PendudukModel penduduk);
}
