package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.PendudukModel;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
	@Autowired
    private PendudukMapper pendudukMapper;


    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
    }
    
    @Override
	 public void addPenduduk (PendudukModel penduduk)
	 {
		 log.info("add penduduk");
		 pendudukMapper.addPenduduk(penduduk);
	 }
    
    @Override
	 public void updatePenduduk (PendudukModel penduduk)
	 {
		 log.info("update penduduk");
		 pendudukMapper.updatePenduduk(penduduk);
	 }
    
    @Override
	 public void setWafat (PendudukModel penduduk)
	 {
		 log.info("nonkatifkan penduduk");
		 pendudukMapper.setWafat(penduduk.getNik());
	 }
    
    @Override
	public List<PendudukModel> selectPendudukByIdKelurahan(String id_kelurahan) {
		log.info("select penduduk with id_kelurahan {}", id_kelurahan);
		return pendudukMapper.selectPendudukByIdKelurahan(""+id_kelurahan);
	}
}
