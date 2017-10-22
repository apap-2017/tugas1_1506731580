package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService
{
	@Autowired
    private KeluargaMapper keluargaMapper;


    @Override
    public KeluargaModel selectKeluarga (String nomor_kk)
    {
        log.info ("select keluarga with nomor_kk {}", nomor_kk);
        return keluargaMapper.selectKeluarga (nomor_kk);
    }
    
    @Override
	 public KeluargaModel getKeluarga (String id)
	 {
		 log.info("select keluarga with nkk");
		 return keluargaMapper.getKeluarga(id);
	 }
    
    @Override
	 public void addKeluarga (KeluargaModel keluarga)
	 {
		 log.info("add keluarga");
		 keluargaMapper.addKeluarga(keluarga);
	 }
    
    @Override
	 public void updateKeluarga (KeluargaModel keluarga)
	 {
		 log.info("update keluarga");
		 keluargaMapper.updateKeluarga(keluarga);
	 }
}