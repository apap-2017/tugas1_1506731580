package com.example.service;

import com.example.model.KelurahanModel;
import com.example.model.KecamatanModel;
import com.example.model.KotaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import com.example.dao.AlamatMapper;

@Service
public class AlamatServiceDatabase implements AlamatService {
	@Autowired
	private AlamatMapper alamatMapper;

	@Override
	public List<KelurahanModel> selectKelurahans() {
		return alamatMapper.selectKelurahans();
	}

	@Override
	public List<KecamatanModel> selectKecamatans() {
		return alamatMapper.selectKecamatans();
	}

	@Override
	public List<KotaModel> selectKotas() {
		return alamatMapper.selectKotas();
	}
	
	@Override
	public KecamatanModel selectKecamatanById(int id){
		return alamatMapper.selectKecamatanById(id);
	}
	
	@Override
	public KelurahanModel selectKelurahanById(int id){
		return alamatMapper.selectKelurahanById(id);
	}
	
	@Override
	public KotaModel selectKotaById(int id){
		return alamatMapper.selectKotaById(id);
	}

}