package com.example.dao;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlamatMapper {
	@Select("SELECT * FROM kelurahan JOIN kecamatan JOIN kota"
			+ " ON kelurahan.id_kecamatan = kecamatan.id "
			+ " AND kecamatan.id_kota = kota.id"
			)
    @Results(value = {
    		@Result(property="id", column="kelurahan.id")
    })
    List<KelurahanModel> selectKelurahans();
	
	@Select("SELECT * FROM kecamatan")
    List<KecamatanModel> selectKecamatans();
	
	@Select("SELECT * FROM kota")
    List<KotaModel> selectKotas();
	
	
}
