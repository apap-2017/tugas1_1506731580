package com.example.service;
import com.example.model.PendudukModel;
import java.util.List;

public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    void addPenduduk(PendudukModel penduduk);
    void updatePenduduk(PendudukModel penduduk);
    void setWafat(PendudukModel penduduk);
	List<PendudukModel> selectPendudukByIdKelurahan(String id_kelurahan);
}
