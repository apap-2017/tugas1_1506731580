package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

public interface AlamatService {
	List<KelurahanModel> selectKelurahans();
	List<KecamatanModel> selectKecamatans();
	List<KotaModel> selectKotas();
}
