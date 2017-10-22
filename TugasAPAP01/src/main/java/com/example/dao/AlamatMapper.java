package com.example.dao;

import java.util.List;

import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

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
	
	@Select("SELECT * FROM kecamatan where id = #{id}")
	KecamatanModel selectKecamatanById(@Param("id") int id);
	
	@Select("SELECT * FROM kelurahan where id = #{id}")
	KelurahanModel selectKelurahanById(@Param("id") int id);
	
	@Select("SELECT * FROM kota where id = #{id}")
	KotaModel selectKotaById(@Param("id") int id);
	
	
	
}
